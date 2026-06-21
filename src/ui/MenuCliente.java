package ui;

import controller.LojaController;
import model.*;
import utils.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            String op = Input.lerOpcional(sc, "Opção: ");
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
        String nome = Input.lerObrigatorio(sc, "Nome do jogo: ");
        List<Jogo> resultados = ctrl.pesquisarJogosPorNome(nome);
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum jogo encontrado.)");
            return;
        }
        for (Jogo j : resultados) System.out.println(j);
        perguntarExportar("jogos_por_nome.txt", toStringList(resultados));
    }

    private void pesquisarJogoDiretor() {
        String nome = Input.lerObrigatorio(sc, "Nome do diretor: ");
        List<Jogo> resultados = ctrl.pesquisarJogosPorDiretor(nome);
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum jogo encontrado.)");
            return;
        }
        for (Jogo j : resultados) System.out.println(j);
        perguntarExportar("jogos_por_diretor.txt", toStringList(resultados));
    }

    private void pesquisarJogoEstilo() {
        String estilo = Input.lerObrigatorio(sc, "Estilo: ");
        List<Jogo> resultados = ctrl.pesquisarJogosPorEstilo(estilo);
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum jogo encontrado.)");
            return;
        }
        for (Jogo j : resultados) System.out.println(j);
        perguntarExportar("jogos_por_estilo.txt", toStringList(resultados));
    }

    private void verProdutosComJogo() {
        Jogo j = selecionarJogo();
        if (j == null) return;
        List<Produto> resultados = ctrl.produtosQueContemJogo(j);
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum produto encontrado.)");
            return;
        }
        for (Produto p : resultados) System.out.println(p);
        perguntarExportar("produtos_com_jogo.txt", toStringList(resultados));
    }

    private void verJogosMesmoDiretor() {
        Jogo j = selecionarJogo();
        if (j == null || j.getDiretor() == null) return;
        List<Jogo> resultados = ctrl.pesquisarJogosPorDiretor(j.getDiretor().getNome());
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum jogo encontrado.)");
            return;
        }
        for (Jogo g : resultados) System.out.println(g);
        perguntarExportar("jogos_mesmo_diretor.txt", toStringList(resultados));
    }

    private void verJogosMesmoEstilo() {
        Jogo j = selecionarJogo();
        if (j == null) return;
        List<Jogo> resultados = ctrl.pesquisarJogosPorEstilo(j.getEstilo());
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum jogo encontrado.)");
            return;
        }
        for (Jogo g : resultados) System.out.println(g);
        perguntarExportar("jogos_mesmo_estilo.txt", toStringList(resultados));
    }

    private void verProdutosCompradosPorMesmosClientes() {
        Produto p = selecionarProduto();
        if (p == null) return;
        List<Produto> resultados = ctrl.produtosCompradosPorClientesQueCompraram(p);
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum produto encontrado.)");
            return;
        }
        for (Produto pr : resultados) System.out.println(pr);
        perguntarExportar("produtos_mesmos_clientes.txt", toStringList(resultados));
    }

    private void verDiretoresProdutora() {
        String nome = Input.lerObrigatorio(sc, "Nome da produtora: ");
        List<Diretor> resultados = ctrl.listarDiretoresDaProdutora(nome);
        if (resultados.isEmpty()) {
            System.out.println("(Nenhum diretor encontrado.)");
            return;
        }
        for (Diretor d : resultados) System.out.println(d);
        perguntarExportar("diretores_produtora.txt", toStringList(resultados));
    }

    private void perguntarExportar(String nomeFicheiro, List<String> linhas) {
        if (Input.lerSimNao(sc, "Exportar resultados para ficheiro? (s/n): ")) {
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
        if (lista.isEmpty()) { System.out.println("(Não há jogos.)"); return null; }
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        int idx = Input.lerInt(sc, "Escolher jogo (índice, -1 = cancelar): ", -1, lista.size() - 1);
        return idx < 0 ? null : lista.get(idx);
    }

    private Produto selecionarProduto() {
        List<Produto> lista = ctrl.listarProdutos();
        if (lista.isEmpty()) { System.out.println("(Não há produtos.)"); return null; }
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        int idx = Input.lerInt(sc, "Escolher produto (índice, -1 = cancelar): ", -1, lista.size() - 1);
        return idx < 0 ? null : lista.get(idx);
    }
}
