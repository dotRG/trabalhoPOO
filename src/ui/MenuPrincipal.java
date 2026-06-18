package ui;

import controller.LojaController;
import utils.Input;

import java.util.Scanner;

public class MenuPrincipal {
    private final Scanner sc;
    private final LojaController ctrl;
    private final LoginManager login;
    private final MenuAdmin menuAdmin;
    private final MenuCliente menuCliente;

    public MenuPrincipal() {
        this.sc = new Scanner(System.in);
        this.ctrl = new LojaController();
        this.login = new LoginManager();
        this.menuAdmin = new MenuAdmin(sc, ctrl);
        this.menuCliente = new MenuCliente(sc, ctrl);

        // Criar password default se não existir
        if (!login.existePassword()) {
            login.definirPasswordDefault("admin123");
            System.out.println("Password de admin criada por defeito: admin123");
        }
    }

    public void iniciar() {
        boolean executar = true;
        while (executar) {
            System.out.println("\n===== LOJA DE JOGOS =====");
            System.out.println("1. Zona Administrador");
            System.out.println("2. Zona Cliente");
            System.out.println("0. Sair");
            String op = Input.lerOpcional(sc, "Opção: ");

            switch (op) {
                case "1":
                    zonaAdmin();
                    break;
                case "2":
                    menuCliente.mostrar();
                    break;
                case "0":
                    ctrl.guardarDados();
                    System.out.println("Dados guardados. Adeus!");
                    executar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void zonaAdmin() {
        String pass = Input.lerObrigatorio(sc, "Password de administrador: ");
        if (login.autenticarAdmin(pass)) {
            System.out.println("Login efetuado com sucesso.");
            menuAdmin.mostrar();
        } else {
            System.out.println("Password incorreta.");
        }
    }

    public static void main(String[] args) {
        new MenuPrincipal().iniciar();
    }
}
