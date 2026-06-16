package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Venda implements Serializable {
    private static final long serialVersionUID = 5L;

    private String empregado;
    private Produto produto;
    private int quantidade;
    private double custoTotal;
    private LocalDateTime dataVenda;
    private Promocao promocao;
    private ClienteEspecial cliente;

    public Venda(String empregado, Produto produto, int quantidade,
                 double custoTotal, LocalDateTime dataVenda, Promocao promocao, ClienteEspecial cliente) {
        this.empregado = empregado;
        this.produto = produto;
        this.quantidade = quantidade;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    @Override
    public String toString() {
        String clienteNome = cliente != null ? cliente.getNome() : "Anónimo";
        String promoNome = promocao != null ? promocao.getNome() : "Sem promoção";
        return "Venda [" + dataVenda + "] " + quantidade + "x " + produto.getFormato()
                + " por " + custoTotal + "€ | Cliente: " + clienteNome + " | Promo: " + promoNome;
    }
}
