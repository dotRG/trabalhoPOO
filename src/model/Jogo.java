package model;

import java.io.Serializable;
import java.util.Objects;

public class Jogo implements Serializable {
    private static final long serialVersionUID = 3L;

    private String nome;
    private int duracaoMinutos;
    private String observacoes;
    private Diretor diretor;
    private Produtora produtora;
    private String estilo;
    public Jogo(String nome, int duracaoMinutos, String observacoes,
                Diretor diretor, Produtora produtora, String estilo) {
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
        this.observacoes = observacoes;
        this.diretor = diretor;
        this.produtora = produtora;
        this.estilo = estilo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Produtora getProdutora() {
        return produtora;
    }

    public void setProdutora(Produtora produtora) {
        this.produtora = produtora;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    // Identidade por chave de negócio: o título do jogo. Permite reconhecer o mesmo
    // jogo entre grafos de objetos distintos (ex.: jogos.dat vs vendas.dat).
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return Objects.equals(nome, jogo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Jogo: " + nome + " | Estilo: " + estilo + " | Diretor: " + (diretor != null ? diretor.getNome() : "N/A");
    }
}
