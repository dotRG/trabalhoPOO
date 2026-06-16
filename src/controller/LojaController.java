package controller;

import model.*;
import persistence.FileManager;
import persistence.TextFileManager;
import utils.CryptoUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LojaController {

    private static final String DATA_DIR = "data/";

    private final FileManager<Diretor> fmDiretores;
    private final FileManager<Produtora> fmProdutoras;
    private final FileManager<Jogo> fmJogos;
    private final FileManager<Produto> fmProdutos;
    private final FileManager<Venda> fmVendas;
    private final FileManager<ClienteEspecial> fmClientes;
    private final FileManager<Promocao> fmPromocoes;
    private final TextFileManager tfmVendas;

    private List<Diretor> diretores;
    private List<Produtora> produtoras;
    private List<Jogo> jogos;
    private List<Produto> produtos;
    private List<Venda> vendas;
    private List<ClienteEspecial> clientes;
    private List<Promocao> promocoes;

    public LojaController() {
        fmDiretores = new FileManager<>(DATA_DIR + "diretores.dat");
        fmProdutoras = new FileManager<>(DATA_DIR + "produtoras.dat");
        fmJogos = new FileManager<>(DATA_DIR + "jogos.dat");
        fmProdutos = new FileManager<>(DATA_DIR + "produtos.dat");
        fmVendas = new FileManager<>(DATA_DIR + "vendas.dat");
        fmClientes = new FileManager<>(DATA_DIR + "clientes.dat");
        fmPromocoes = new FileManager<>(DATA_DIR + "promocoes.dat");
        tfmVendas = new TextFileManager(DATA_DIR + "vendas.txt");

        carregarDados();
    }

    public void carregarDados() {
        diretores = fmDiretores.ler();
        produtoras = fmProdutoras.ler();
        jogos = fmJogos.ler();
        produtos = fmProdutos.ler();
        vendas = fmVendas.ler();
        clientes = fmClientes.ler();
        promocoes = fmPromocoes.ler();
    }

    public void guardarDados() {
        fmDiretores.escrever(diretores);
        fmProdutoras.escrever(produtoras);
        fmJogos.escrever(jogos);
        fmProdutos.escrever(produtos);
        fmVendas.escrever(vendas);
        fmClientes.escrever(clientes);
        fmPromocoes.escrever(promocoes);
    }

    // ===================== CRUD DIRETORES =====================
    public void adicionarDiretor(Diretor d) {
        diretores.add(d);
        guardarDados();
    }

    public List<Diretor> listarDiretores() {
        return new ArrayList<>(diretores);
    }

    public void atualizarDiretor(int index, Diretor d) {
        if (index >= 0 && index < diretores.size()) {
            diretores.set(index, d);
            guardarDados();
        }
    }

    public void removerDiretor(int index) {
        if (index >= 0 && index < diretores.size()) {
            diretores.remove(index);
            guardarDados();
        }
    }

    // ===================== CRUD PRODUTORAS =====================
    public void adicionarProdutora(Produtora p) {
        produtoras.add(p);
        guardarDados();
    }

    public List<Produtora> listarProdutoras() {
        return new ArrayList<>(produtoras);
    }

    public void atualizarProdutora(int index, Produtora p) {
        if (index >= 0 && index < produtoras.size()) {
            produtoras.set(index, p);
            guardarDados();
        }
    }

    public void removerProdutora(int index) {
        if (index >= 0 && index < produtoras.size()) {
            produtoras.remove(index);
            guardarDados();
        }
    }

    // ===================== CRUD JOGOS =====================
    public void adicionarJogo(Jogo j) {
        jogos.add(j);
        guardarDados();
    }

    public List<Jogo> listarJogos() {
        return new ArrayList<>(jogos);
    }

    public void atualizarJogo(int index, Jogo j) {
        if (index >= 0 && index < jogos.size()) {
            jogos.set(index, j);
            guardarDados();
        }
    }

    public void removerJogo(int index) {
        if (index >= 0 && index < jogos.size()) {
            jogos.remove(index);
            guardarDados();
        }
    }

    // ===================== CRUD PRODUTOS =====================
    public void adicionarProduto(Produto p) {
        produtos.add(p);
        guardarDados();
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public void atualizarProduto(int index, Produto p) {
        if (index >= 0 && index < produtos.size()) {
            produtos.set(index, p);
            guardarDados();
        }
    }

    public void removerProduto(int index) {
        if (index >= 0 && index < produtos.size()) {
            produtos.remove(index);
            guardarDados();
        }
    }

    // ===================== CRUD CLIENTES ESPECIAIS =====================
    public void adicionarCliente(ClienteEspecial c) {
        clientes.add(c);
        guardarDados();
    }

    public List<ClienteEspecial> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public void atualizarCliente(int index, ClienteEspecial c) {
        if (index >= 0 && index < clientes.size()) {
            clientes.set(index, c);
            guardarDados();
        }
    }

    public void removerCliente(int index) {
        if (index >= 0 && index < clientes.size()) {
            clientes.remove(index);
            guardarDados();
        }
    }

    // ===================== CRUD PROMOÇÕES =====================
    public void adicionarPromocao(Promocao p) {
        promocoes.add(p);
        guardarDados();
    }

    public List<Promocao> listarPromocoes() {
        return new ArrayList<>(promocoes);
    }

    public void atualizarPromocao(int index, Promocao p) {
        if (index >= 0 && index < promocoes.size()) {
            promocoes.set(index, p);
            guardarDados();
        }
    }

    public void removerPromocao(int index) {
        if (index >= 0 && index < promocoes.size()) {
            promocoes.remove(index);
            guardarDados();
        }
    }

    // ===================== PESQUISAS CLIENTE =====================
    public List<Jogo> pesquisarJogosPorNome(String nome) {
        return jogos.stream()
                .filter(j -> j.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Jogo> pesquisarJogosPorDiretor(String nomeDiretor) {
        return jogos.stream()
                .filter(j -> j.getDiretor() != null && j.getDiretor().getNome().toLowerCase().contains(nomeDiretor.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Jogo> pesquisarJogosPorEstilo(String estilo) {
        return jogos.stream()
                .filter(j -> j.getEstilo().toLowerCase().contains(estilo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Diretor> pesquisarDiretoresPorNome(String nome) {
        return diretores.stream()
                .filter(d -> d.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Diretor> listarDiretoresDaProdutora(String nomeProdutora) {
        return jogos.stream()
                .filter(j -> j.getProdutora() != null && j.getProdutora().getNome().equalsIgnoreCase(nomeProdutora))
                .map(Jogo::getDiretor)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Produto> produtosQueContemJogo(Jogo jogo) {
        return produtos.stream()
                .filter(p -> p.getJogos().stream().anyMatch(j -> j.getNome().equalsIgnoreCase(jogo.getNome())))
                .collect(Collectors.toList());
    }

    public List<Produto> produtosCompradosPorClientesQueCompraram(Produto produto) {
        Set<ClienteEspecial> compradores = vendas.stream()
                .filter(v -> v.getProduto().equals(produto) && v.getCliente() != null)
                .map(Venda::getCliente)
                .collect(Collectors.toSet());

        return vendas.stream()
                .filter(v -> compradores.contains(v.getCliente()))
                .map(Venda::getProduto)
                .distinct()
                .collect(Collectors.toList());
    }

    // ===================== VENDAS =====================
    public boolean registarVenda(String empregado, Produto produto, int quantidade, ClienteEspecial cliente) {
        if (produto.getStock() < quantidade) {
            return false;
        }

        double precoFinal = produto.getPrecoVenda() * quantidade;
        Promocao ativa = null;
        for (Promocao p : promocoes) {
            if (p.estaAtiva()) {
                ativa = p;
                precoFinal = p.aplicarDesconto(precoFinal);
                break;
            }
        }

        produto.setStock(produto.getStock() - quantidade);
        Venda v = new Venda(empregado, produto, quantidade, precoFinal, LocalDateTime.now(), ativa, cliente);
        vendas.add(v);

        if (cliente != null) {
            cliente.adicionarCompra(v);
        }

        // Guardar em ficheiro de texto
        String linha = String.format("%s | Empregado: %s | Produto: %s | Qtd: %d | Total: %.2f€ | Cliente: %s | Promo: %s",
                v.getDataVenda(), empregado, produto.getFormato(), quantidade, precoFinal,
                cliente != null ? cliente.getNome() : "Anónimo",
                ativa != null ? ativa.getNome() : "Nenhuma");
        tfmVendas.escreverLinha(linha, true);

        guardarDados();
        return true;
    }

    public List<Venda> listarVendas() {
        return new ArrayList<>(vendas);
    }

    // ===================== ESTATÍSTICAS =====================
    public List<Map.Entry<Jogo, Long>> jogosMaisVendidos() {
        return vendas.stream()
                .flatMap(v -> v.getProduto().getJogos().stream())
                .collect(Collectors.groupingBy(j -> j, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Long>> estilosMaisProcurados() {
        return vendas.stream()
                .flatMap(v -> v.getProduto().getJogos().stream())
                .map(Jogo::getEstilo)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Diretor, Long>> diretoresMaisVendidos() {
        return vendas.stream()
                .flatMap(v -> v.getProduto().getJogos().stream())
                .map(Jogo::getDiretor)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Diretor, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Produtora, Long>> produtorasMaisVendidas() {
        return vendas.stream()
                .flatMap(v -> v.getProduto().getJogos().stream())
                .map(Jogo::getProdutora)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Produtora, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    // ===================== EXPORTAR =====================
    public void exportarParaFicheiro(String nomeFicheiro, List<String> linhas) {
        TextFileManager tfm = new TextFileManager(DATA_DIR + nomeFicheiro);
        tfm.escreverLinhas(linhas, false);
    }

    public List<Promocao> promocoesAtivas() {
        return promocoes.stream().filter(Promocao::estaAtiva).collect(Collectors.toList());
    }
}
