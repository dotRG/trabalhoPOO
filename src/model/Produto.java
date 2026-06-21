package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Produto implements Serializable {
    private static final long serialVersionUID = 4L;

    private List<Jogo> jogos;
    private String formato;
    private String plataforma;
    private List<Localizacao> localizacoes;
    private double precoCusto;
    private double precoVenda;
    private int stock;
    private boolean disponivel;

    public Produto(String formato, String plataforma,
                   double precoCusto, double precoVenda, int stock) {
        this.jogos = new ArrayList<>();
        this.localizacoes = new ArrayList<>();
        this.formato = formato;
        this.plataforma = plataforma;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.stock = stock;
        this.disponivel = stock > 0;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void adicionarJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }

    public void removerJogo(Jogo jogo) {
        this.jogos.remove(jogo);
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public List<Localizacao> getLocalizacoes() {
        return localizacoes;
    }

    public void adicionarLocalizacao(Localizacao l) {
        if (!localizacoes.contains(l)) {
            localizacoes.add(l);
        }
    }

    public void removerLocalizacao(Localizacao l) {
        localizacoes.remove(l);
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        this.disponivel = stock > 0;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    // Nomes dos jogos ordenados — usado como parte da identidade do produto.
    private List<String> nomesJogosOrdenados() {
        List<String> nomes = new ArrayList<>();
        for (Jogo j : jogos) nomes.add(j.getNome());
        Collections.sort(nomes);
        return nomes;
    }

    // Dois produtos são iguais se tiverem o mesmo formato, plataforma e o mesmo
    // conjunto de jogos (comparados por nome). Isto permite reconhecer "o mesmo
    // produto" mesmo quando os objetos vêm de ficheiros diferentes (produtos.dat
    // vs vendas.dat), onde após desserialização as referências já não coincidem.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(formato, produto.formato)
                && Objects.equals(plataforma, produto.plataforma)
                && nomesJogosOrdenados().equals(produto.nomesJogosOrdenados());
    }

    @Override
    public int hashCode() {
        return Objects.hash(formato, plataforma, nomesJogosOrdenados());
    }

    @Override
    public String toString() {
        List<String> nomes = new ArrayList<>();
        for (Localizacao l : localizacoes) nomes.add(l.getNome());
        String local = nomes.isEmpty() ? "Sem localização" : String.join(", ", nomes);
        return "Produto [" + formato + " | " + plataforma + "] " + jogos
                + " | Stock: " + stock + " | " + precoVenda + "€ | Local: " + local;
    }
}
