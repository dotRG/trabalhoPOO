package model;

import java.io.Serializable;
import java.util.Objects;

public class Localizacao implements Serializable {
    private static final long serialVersionUID = 9L;

    public enum Tipo { AREA, PRATELEIRA }

    private Tipo tipo;
    private String nome;
    private int capacidade;
    private int numeroPrateleira;
    private String descricao;

    public Localizacao(Tipo tipo, String nome, int capacidade, int numeroPrateleira, String descricao) {
        this.tipo = tipo;
        this.nome = nome;
        this.capacidade = capacidade;
        this.numeroPrateleira = numeroPrateleira;
        this.descricao = descricao;
    }

    public String identificacao() {
        switch (tipo) {
            case AREA:
                return "Área: " + nome + " (cap. " + capacidade + ")";
            case PRATELEIRA:
                return "Prateleira " + numeroPrateleira + " — " + nome + " (cap. " + capacidade + ")";
            default:
                return nome;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getNumeroPrateleira() {
        return numeroPrateleira;
    }

    public void setNumeroPrateleira(int numeroPrateleira) {
        this.numeroPrateleira = numeroPrateleira;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Localizacao that = (Localizacao) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return identificacao();
    }
}
