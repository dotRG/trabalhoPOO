package controller;

import gestao.*;
import model.*;
import persistence.TextFileManager;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LojaController {

    private static final String DATA_DIR = "data/";

    private final GereDiretor gereDiretor;
    private final GereProdutora gereProdutora;
    private final GereJogo gereJogo;
    private final GereProduto gereProduto;
    private final GereCliente gereCliente;
    private final GerePromocao gerePromocao;
    private final GereVenda gereVenda;
    private final GereLocalizacao gereLocalizacao;
    private final TextFileManager tfmVendas;

    public LojaController() {
        gereDiretor = new GereDiretor(DATA_DIR + "diretores.dat");
        gereProdutora = new GereProdutora(DATA_DIR + "produtoras.dat");
        gereJogo = new GereJogo(DATA_DIR + "jogos.dat");
        gereProduto = new GereProduto(DATA_DIR + "produtos.dat");
        gereCliente = new GereCliente(DATA_DIR + "clientes.dat");
        gerePromocao = new GerePromocao(DATA_DIR + "promocoes.dat");
        gereVenda = new GereVenda(DATA_DIR + "vendas.dat");
        gereLocalizacao = new GereLocalizacao(DATA_DIR + "localizacoes.dat");
        tfmVendas = new TextFileManager(DATA_DIR + "vendas.txt");
    }

    public void guardarDados() {
        gereDiretor.guardar();
        gereProdutora.guardar();
        gereJogo.guardar();
        gereProduto.guardar();
        gereCliente.guardar();
        gerePromocao.guardar();
        gereVenda.guardar();
        gereLocalizacao.guardar();
    }

    // ===================== CRUD DIRETORES =====================
    public void adicionarDiretor(Diretor d) {
        gereDiretor.adicionar(d);
    }

    public List<Diretor> listarDiretores() {
        return gereDiretor.listar();
    }

    public void atualizarDiretor(int index, Diretor d) {
        gereDiretor.atualizar(index, d);
    }

    public void removerDiretor(int index) {
        gereDiretor.remover(index);
    }

    // ===================== CRUD PRODUTORAS =====================
    public void adicionarProdutora(Produtora p) {
        gereProdutora.adicionar(p);
    }

    public List<Produtora> listarProdutoras() {
        return gereProdutora.listar();
    }

    public void atualizarProdutora(int index, Produtora p) {
        gereProdutora.atualizar(index, p);
    }

    public void removerProdutora(int index) {
        gereProdutora.remover(index);
    }

    // ===================== CRUD JOGOS =====================
    public void adicionarJogo(Jogo j) {
        gereJogo.adicionar(j);
    }

    public List<Jogo> listarJogos() {
        return gereJogo.listar();
    }

    public void atualizarJogo(int index, Jogo j) {
        gereJogo.atualizar(index, j);
    }

    public void removerJogo(int index) {
        gereJogo.remover(index);
    }

    // ===================== CRUD PRODUTOS =====================
    public void adicionarProduto(Produto p) {
        gereProduto.adicionar(p);
    }

    public List<Produto> listarProdutos() {
        return gereProduto.listar();
    }

    public void atualizarProduto(int index, Produto p) {
        gereProduto.atualizar(index, p);
    }

    public void removerProduto(int index) {
        gereProduto.remover(index);
    }

    // ===================== CRUD CLIENTES ESPECIAIS =====================
    public void adicionarCliente(ClienteEspecial c) {
        gereCliente.adicionar(c);
    }

    public List<ClienteEspecial> listarClientes() {
        return gereCliente.listar();
    }

    public void atualizarCliente(int index, ClienteEspecial c) {
        gereCliente.atualizar(index, c);
    }

    public void removerCliente(int index) {
        gereCliente.remover(index);
    }

    // ===================== CRUD PROMOÇÕES =====================
    public void adicionarPromocao(Promocao p) {
        gerePromocao.adicionar(p);
    }

    public List<Promocao> listarPromocoes() {
        return gerePromocao.listar();
    }

    public void atualizarPromocao(int index, Promocao p) {
        gerePromocao.atualizar(index, p);
    }

    public void removerPromocao(int index) {
        gerePromocao.remover(index);
    }

    // ===================== CRUD LOCALIZAÇÕES =====================
    public void adicionarLocalizacao(Localizacao l) {
        gereLocalizacao.adicionar(l);
    }

    public List<Localizacao> listarLocalizacoes() {
        return gereLocalizacao.listar();
    }

    public void atualizarLocalizacao(int index, Localizacao l) {
        gereLocalizacao.atualizar(index, l);
    }

    public void removerLocalizacao(int index) {
        gereLocalizacao.remover(index);
    }

    // ===================== OCUPAÇÃO DE LOCALIZAÇÕES =====================
    public int espacoOcupado(Localizacao loc) {
        return gereProduto.listar().stream()
                .filter(p -> p.getLocalizacoes().contains(loc))
                .mapToInt(p -> p.getJogos().size())
                .sum();
    }

    public int espacoLivre(Localizacao loc) {
        return loc.getCapacidade() - espacoOcupado(loc);
    }

    // Lista derivada: os produtos que estão nesta localização (calculada a partir
    // dos produtos, sem guardar a relação inversa na própria Localizacao).
    public List<Produto> produtosEmLocalizacao(Localizacao loc) {
        return gereProduto.listar().stream()
                .filter(p -> p.getLocalizacoes().contains(loc))
                .collect(Collectors.toList());
    }

    // produtoAtual permite ignorar a contribuição do próprio produto se já estiver nessa localização
    public boolean cabe(Localizacao loc, int nJogos, Produto produtoAtual) {
        int ocupado = espacoOcupado(loc);
        if (produtoAtual != null && produtoAtual.getLocalizacoes().contains(loc)) {
            ocupado -= produtoAtual.getJogos().size();
        }
        return ocupado + nJogos <= loc.getCapacidade();
    }

    // ===================== PESQUISAS CLIENTE =====================
    public List<Jogo> pesquisarJogosPorNome(String nome) {
        return gereJogo.listar().stream()
                .filter(j -> j.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Jogo> pesquisarJogosPorDiretor(String nomeDiretor) {
        return gereJogo.listar().stream()
                .filter(j -> j.getDiretor() != null && j.getDiretor().getNome().toLowerCase().contains(nomeDiretor.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Jogo> pesquisarJogosPorEstilo(String estilo) {
        return gereJogo.listar().stream()
                .filter(j -> j.getEstilo().toLowerCase().contains(estilo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Diretor> pesquisarDiretoresPorNome(String nome) {
        return gereDiretor.listar().stream()
                .filter(d -> d.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Diretor> listarDiretoresDaProdutora(String nomeProdutora) {
        return gereJogo.listar().stream()
                .filter(j -> j.getProdutora() != null && j.getProdutora().getNome().equalsIgnoreCase(nomeProdutora))
                .map(Jogo::getDiretor)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Produto> produtosQueContemJogo(Jogo jogo) {
        return gereProduto.listar().stream()
                .filter(p -> p.getJogos().stream().anyMatch(j -> j.getNome().equalsIgnoreCase(jogo.getNome())))
                .collect(Collectors.toList());
    }

    public List<Produto> produtosCompradosPorClientesQueCompraram(Produto produto) {
        List<Venda> vendas = gereVenda.listar();
        Set<ClienteEspecial> compradores = vendas.stream()
                .filter(v -> v.getCliente() != null
                        && v.getItens().stream().anyMatch(it -> it.getProduto().equals(produto)))
                .map(Venda::getCliente)
                .collect(Collectors.toSet());

        return vendas.stream()
                .filter(v -> compradores.contains(v.getCliente()))
                .flatMap(v -> v.getItens().stream())
                .map(ItemVenda::getProduto)
                .distinct()
                .collect(Collectors.toList());
    }

    // ===================== VENDAS =====================
    // Uma venda pode conter vários produtos (linhas). Devolve false se faltar stock
    // para algum produto (validado pela soma das quantidades por produto).
    public boolean registarVenda(String empregado, List<ItemVenda> itens, ClienteEspecial cliente) {
        if (itens == null || itens.isEmpty()) {
            return false;
        }

        // Validar stock: somar as quantidades pedidas por produto (usa Produto.equals)
        Map<Produto, Integer> pedidoPorProduto = new HashMap<>();
        for (ItemVenda it : itens) {
            pedidoPorProduto.merge(it.getProduto(), it.getQuantidade(), Integer::sum);
        }
        for (Map.Entry<Produto, Integer> e : pedidoPorProduto.entrySet()) {
            if (e.getKey().getStock() < e.getValue()) {
                return false;
            }
        }

        // Total bruto (soma dos subtotais das linhas)
        double precoFinal = 0;
        for (ItemVenda it : itens) {
            precoFinal += it.getSubtotal();
        }

        // Aplicar a primeira promoção ativa ao total da venda
        Promocao ativa = null;
        for (Promocao p : gerePromocao.listar()) {
            if (p.estaAtiva()) {
                ativa = p;
                precoFinal = p.aplicarDesconto(precoFinal);
                break;
            }
        }

        // Baixar stock de cada produto
        for (Map.Entry<Produto, Integer> e : pedidoPorProduto.entrySet()) {
            e.getKey().setStock(e.getKey().getStock() - e.getValue());
        }
        gereProduto.guardar();

        Venda v = new Venda(empregado, itens, precoFinal, LocalDateTime.now(), ativa, cliente);
        gereVenda.adicionar(v);

        if (cliente != null) {
            cliente.adicionarCompra(v);
            gereCliente.guardar();
        }

        // Guardar em ficheiro de texto
        StringBuilder itensStr = new StringBuilder();
        for (int i = 0; i < itens.size(); i++) {
            if (i > 0) itensStr.append("; ");
            itensStr.append(itens.get(i).getProduto().getFormato())
                    .append(" x").append(itens.get(i).getQuantidade());
        }
        String linha = String.format("%s | Empregado: %s | Itens: %s | Total: %.2f€ | Cliente: %s | Promo: %s",
                v.getDataVenda(), empregado, itensStr, precoFinal,
                cliente != null ? cliente.getNome() : "Anónimo",
                ativa != null ? ativa.getNome() : "Nenhuma");
        tfmVendas.escreverLinha(linha, true);

        return true;
    }

    public List<Venda> listarVendas() {
        return gereVenda.listar();
    }

    // ===================== ESTATÍSTICAS =====================
    public List<Map.Entry<Jogo, Long>> jogosMaisVendidos() {
        return gereVenda.listar().stream()
                .flatMap(v -> v.getItens().stream())
                .flatMap(it -> it.getProduto().getJogos().stream())
                .collect(Collectors.groupingBy(j -> j, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, Long>> estilosMaisProcurados() {
        return gereVenda.listar().stream()
                .flatMap(v -> v.getItens().stream())
                .flatMap(it -> it.getProduto().getJogos().stream())
                .map(Jogo::getEstilo)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Diretor, Long>> diretoresMaisVendidos() {
        return gereVenda.listar().stream()
                .flatMap(v -> v.getItens().stream())
                .flatMap(it -> it.getProduto().getJogos().stream())
                .map(Jogo::getDiretor)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<Diretor, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }

    public List<Map.Entry<Produtora, Long>> produtorasMaisVendidas() {
        return gereVenda.listar().stream()
                .flatMap(v -> v.getItens().stream())
                .flatMap(it -> it.getProduto().getJogos().stream())
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
        return gerePromocao.listar().stream().filter(Promocao::estaAtiva).collect(Collectors.toList());
    }
}
