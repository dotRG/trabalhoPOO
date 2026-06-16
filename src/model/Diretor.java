package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Diretor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private String paginaWeb;
    private String morada;
    private String moradaClubeFas;
    private String observacoes;

    public Diretor(String nome, LocalDate dataNascimento, String email, String paginaWeb,
                   String morada, String moradaClubeFas, String observacoes) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.paginaWeb = paginaWeb;
        this.morada = morada;
        this.moradaClubeFas = moradaClubeFas;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getMoradaClubeFas() {
        return moradaClubeFas;
    }

    public void setMoradaClubeFas(String moradaClubeFas) {
        this.moradaClubeFas = moradaClubeFas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Diretor: " + nome + " | Nascimento: " + dataNascimento + " | Email: " + email;
    }
}
