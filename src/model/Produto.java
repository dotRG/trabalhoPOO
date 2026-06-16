package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Produto implements Serializable {
    private static final long serialVersionUID = 4L;

    private List<Jogo> jogos;
    private String formato;
    private String plataforma;
    private String localizacao;
    private double precoCusto;
    private double precoVenda;
    private int stock;
    private boolean disponivel;

    public Produto(String formato, String plataforma, String localizacao,
                   double precoCusto, double precoVenda, int stock) {
        this.jogos = new ArrayList<>();
        this.formato = formato;
        this.plataforma = plataforma;
        this.localizacao = localizacao;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
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

    @Override
    public String toString() {
        return "Produto [" + formato + " | " + plataforma + "] " + jogos + " | Stock: " + stock + " | " + precoVenda + "€";
    }
}
