package model;

import java.io.Serializable;

public class Produtora implements Serializable {
    private static final long serialVersionUID = 2L;

    private String nome;
    private String morada;
    private String paginaWeb;
    private String email;
    private String estiloPrincipal;
    private String observacoes;

    public Produtora(String nome, String morada, String paginaWeb, String email,
                     String estiloPrincipal, String observacoes) {
        this.nome = nome;
        this.morada = morada;
        this.paginaWeb = paginaWeb;
        this.email = email;
        this.estiloPrincipal = estiloPrincipal;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstiloPrincipal() {
        return estiloPrincipal;
    }

    public void setEstiloPrincipal(String estiloPrincipal) {
        this.estiloPrincipal = estiloPrincipal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Produtora: " + nome + " | Estilo: " + estiloPrincipal + " | Morada: " + morada;
    }
}
