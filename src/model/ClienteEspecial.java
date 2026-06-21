package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteEspecial implements Serializable {
    private static final long serialVersionUID = 6L;

    private String nome;
    private String contacto;
    private String email;
    private String morada;
    private List<Venda> historicoCompras;

    public ClienteEspecial(String nome, String contacto, String email, String morada) {
        this.nome = nome;
        this.contacto = contacto;
        this.email = email;
        this.morada = morada;
        this.historicoCompras = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public List<Venda> getHistoricoCompras() {
        return historicoCompras;
    }

    public void adicionarCompra(Venda venda) {
        this.historicoCompras.add(venda);
    }

    // Um cliente especial é identificado pelo nome e email (não é anónimo).
    // Permite reconhecer o mesmo cliente entre grafos de objetos distintos.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEspecial that = (ClienteEspecial) o;
        return Objects.equals(nome, that.nome) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email);
    }

    @Override
    public String toString() {
        return "ClienteEspecial: " + nome + " | Contacto: " + contacto + " | Email: " + email;
    }
}
