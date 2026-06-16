package ui;

import controller.LojaController;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuCliente {
    private final Scanner sc;
    private final LojaController ctrl;

    public MenuCliente(Scanner sc, LojaController ctrl) {
        this.sc = sc;
        this.ctrl = ctrl;
    }

    public void mostrar() {
        boolean executar = true;
        while (executar) {
            System.out.println("\n===== ZONA CLIENTE =====");
            System.out.println("1. Pesquisar Jogo por Nome");
            System.out.println("2. Pesquisar Jogo por Diretor");
            System.out.println("3. Pesquisar Jogo por Estilo");
            System.out.println("4. Ver Produtos que contêm um Jogo");
            System.out.println("5. Ver outros Jogos do mesmo Diretor");
            System.out.println("6. Ver outros Jogos do mesmo Estilo");
            System.out.println("7. Ver Produtos comprados pelos mesmos Clientes");
            System.out.println("8. Ver Diretores de uma Produtora");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            String op = sc.nextLine();
            switch (op) {
                case "1":
                    pesquisarJogoNome();
                    break;
                case "2":
                    pesquisarJogoDiretor();
                    break;
                case "3":
                    pesquisarJogoEstilo();
                    break;
                case "4":
                    verProdutosComJogo();
                    break;
                case "5":
                    verJogosMesmoDiretor();
                    break;
                case "6":
                    verJogosMesmoEstilo();
                    break;
                case "7":
                    verProdutosCompradosPorMesmosClientes();
                    break;
                case "8":
                    verDiretoresProdutora();
                    break;
                case "0":
                    executar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void pesquisarJogoNome() {
        System.out.print("Nome do jogo: ");
        String nome = sc.nextLine();
        List<Jogo> resultados = ctrl.pesquisarJogosPorNome(nome);
        for (Jogo j : resultados) System.out.println(j);
        perguntarExportar("jogos_por_nome.txt", toStringList(resultados));
    }

    private void pesquisarJogoDiretor() {
        System.out.print("Nome do diretor: ");
        String nome = sc.nextLine();
        List<Jogo> resultados = ctrl.pesquisarJogosPorDiretor(nome);
        for (Jogo j : resultados) System.out.println(j);
        perguntarExportar("jogos_por_diretor.txt", toStringList(resultados));
    }

    private void pesquisarJogoEstilo() {
        System.out.print("Estilo: ");
        String estilo = sc.nextLine();
        List<Jogo> resultados = ctrl.pesquisarJogosPorEstilo(estilo);
        for (Jogo j : resultados) System.out.println(j);
        perguntarExportar("jogos_por_estilo.txt", toStringList(resultados));
    }

    private void verProdutosComJogo() {
        Jogo j = selecionarJogo();
        if (j == null) return;
        List<Produto> resultados = ctrl.produtosQueContemJogo(j);
        for (Produto p : resultados) System.out.println(p);
        perguntarExportar("produtos_com_jogo.txt", toStringList(resultados));
    }

    private void verJogosMesmoDiretor() {
        Jogo j = selecionarJogo();
        if (j == null || j.getDiretor() == null) return;
        List<Jogo> resultados = ctrl.pesquisarJogosPorDiretor(j.getDiretor().getNome());
        for (Jogo g : resultados) System.out.println(g);
        perguntarExportar("jogos_mesmo_diretor.txt", toStringList(resultados));
    }

    private void verJogosMesmoEstilo() {
        Jogo j = selecionarJogo();
        if (j == null) return;
        List<Jogo> resultados = ctrl.pesquisarJogosPorEstilo(j.getEstilo());
        for (Jogo g : resultados) System.out.println(g);
        perguntarExportar("jogos_mesmo_estilo.txt", toStringList(resultados));
    }

    private void verProdutosCompradosPorMesmosClientes() {
        Produto p = selecionarProduto();
        if (p == null) return;
        List<Produto> resultados = ctrl.produtosCompradosPorClientesQueCompraram(p);
        for (Produto pr : resultados) System.out.println(pr);
        perguntarExportar("produtos_mesmos_clientes.txt", toStringList(resultados));
    }

    private void verDiretoresProdutora() {
        System.out.print("Nome da produtora: ");
        String nome = sc.nextLine();
        List<Diretor> resultados = ctrl.listarDiretoresDaProdutora(nome);
        for (Diretor d : resultados) System.out.println(d);
        perguntarExportar("diretores_produtora.txt", toStringList(resultados));
    }

    private void perguntarExportar(String nomeFicheiro, List<String> linhas) {
        System.out.print("Exportar resultados para ficheiro? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            ctrl.exportarParaFicheiro(nomeFicheiro, linhas);
            System.out.println("Exportado para data/" + nomeFicheiro);
        }
    }

    private List<String> toStringList(List<?> lista) {
        List<String> result = new ArrayList<>();
        for (Object o : lista) result.add(o.toString());
        return result;
    }

    private Jogo selecionarJogo() {
        List<Jogo> lista = ctrl.listarJogos();
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        System.out.print("Escolher jogo (índice): ");
        int idx = Integer.parseInt(sc.nextLine());
        return (idx >= 0 && idx < lista.size()) ? lista.get(idx) : null;
    }

    private Produto selecionarProduto() {
        List<Produto> lista = ctrl.listarProdutos();
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        System.out.print("Escolher produto (índice): ");
        int idx = Integer.parseInt(sc.nextLine());
        return (idx >= 0 && idx < lista.size()) ? lista.get(idx) : null;
    }
}
