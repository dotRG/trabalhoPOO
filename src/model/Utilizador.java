package model;

import java.io.Serializable;

public class Utilizador implements Serializable {
    private static final long serialVersionUID = 8L;

    public enum Tipo { ADMIN, CLIENTE }

    private String username;
    private String passwordHash;
    private Tipo tipo;

    public Utilizador(String username, String passwordHash, Tipo tipo) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Utilizador: " + username + " | Tipo: " + tipo;
    }
}
