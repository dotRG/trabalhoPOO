package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable {
    private static final long serialVersionUID = 5L;

    private String empregado;
    private List<ItemVenda> itens;
    private double custoTotal;
    private LocalDateTime dataVenda;
    private Promocao promocao;
    private ClienteEspecial cliente;

    public Venda(String empregado, List<ItemVenda> itens, double custoTotal,
                 LocalDateTime dataVenda, Promocao promocao, ClienteEspecial cliente) {
        this.empregado = empregado;
        this.itens = new ArrayList<>(itens);
        this.custoTotal = custoTotal;
        this.dataVenda = dataVenda;
        this.promocao = promocao;
        this.cliente = cliente;
    }

    public String getEmpregado() {
        return empregado;
    }

    public void setEmpregado(String empregado) {
        this.empregado = empregado;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    // Quantidade total de unidades vendidas (soma das linhas).
    public int getQuantidadeTotal() {
        int total = 0;
        for (ItemVenda i : itens) total += i.getQuantidade();
        return total;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public ClienteEspecial getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEspecial cliente) {
        this.cliente = cliente;
    }

    // Compatibilidade: lê vendas antigas (com 'produto' + 'quantidade') e converte-as
    // numa venda de uma só linha. Mantém a leitura das vendas novas (com 'itens').
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField campos = in.readFields();
        this.empregado = (String) campos.get("empregado", null);
        this.custoTotal = campos.get("custoTotal", 0.0);
        this.dataVenda = (LocalDateTime) campos.get("dataVenda", null);
        this.promocao = (Promocao) campos.get("promocao", null);
        this.cliente = (ClienteEspecial) campos.get("cliente", null);

        @SuppressWarnings("unchecked")
        List<ItemVenda> itensLidos = (List<ItemVenda>) campos.get("itens", null);
        if (itensLidos != null) {
            this.itens = itensLidos;                 // formato novo
        } else {
            this.itens = new ArrayList<>();          // formato antigo: 1 produto -> 1 linha
            Produto p = (Produto) campos.get("produto", null);
            int q = campos.get("quantidade", 0);
            if (p != null) this.itens.add(new ItemVenda(p, q));
        }
    }

    @Override
    public String toString() {
        String clienteNome = cliente != null ? cliente.getNome() : "Anónimo";
        String promoNome = promocao != null ? promocao.getNome() : "Sem promoção";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < itens.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(itens.get(i));
        }
        return "Venda [" + dataVenda + "] " + sb + " | Total: " + custoTotal
                + "€ | Cliente: " + clienteNome + " | Promo: " + promoNome;
    }
}
