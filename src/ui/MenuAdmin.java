package ui;

import controller.LojaController;
import model.*;
import utils.Input;

import java.time.LocalDate;
import java.util.*;

public class MenuAdmin {
    private final Scanner sc;
    private final LojaController ctrl;
    private final LoginManager login;

    public MenuAdmin(Scanner sc, LojaController ctrl) {
        this.sc = sc;
        this.ctrl = ctrl;
        this.login = new LoginManager();
    }

    public void mostrar() {
        boolean executar = true;
        while (executar) {
            System.out.println("\n===== ZONA ADMINISTRADOR =====");
            System.out.println("1. Gerir Diretores");
            System.out.println("2. Gerir Produtoras");
            System.out.println("3. Gerir Jogos");
            System.out.println("4. Gerir Produtos");
            System.out.println("5. Gerir Localizações");
            System.out.println("6. Gerir Clientes Especiais");
            System.out.println("7. Gerir Promoções");
            System.out.println("8. Registar Venda");
            System.out.println("9. Estatísticas");
            System.out.println("10. Alterar Password");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            switch (op) {
                case "1":
                    menuDiretores();
                    break;
                case "2":
                    menuProdutoras();
                    break;
                case "3":
                    menuJogos();
                    break;
                case "4":
                    menuProdutos();
                    break;
                case "5":
                    menuLocalizacoes();
                    break;
                case "6":
                    menuClientes();
                    break;
                case "7":
                    menuPromocoes();
                    break;
                case "8":
                    registarVenda();
                    break;
                case "9":
                    menuEstatisticas();
                    break;
                case "10":
                    alterarPassword();
                    break;
                case "0":
                    executar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuDiretores() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Diretores ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            List<Diretor> lista = ctrl.listarDiretores();
            switch (op) {
                case "1":
                    for (Diretor d : lista) System.out.println(d);
                    break;
                case "2": {
                    String nome = Input.lerObrigatorio(sc, "Nome: ");
                    LocalDate dn = Input.lerData(sc, "Data nascimento (aaaa-mm-dd): ");
                    String email = Input.lerOpcional(sc, "Email: ");
                    String web = Input.lerOpcional(sc, "Página web: ");
                    String morada = Input.lerOpcional(sc, "Morada: ");
                    String clube = Input.lerOpcional(sc, "Morada clube de fãs: ");
                    String obs = Input.lerOpcional(sc, "Observações: ");
                    ctrl.adicionarDiretor(new Diretor(nome, dn, email, web, morada, clube, obs));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    if (lista.isEmpty()) { System.out.println("(Não há diretores.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a editar: ", lista.size());
                    Diretor d = lista.get(idx);
                    String nome = Input.lerOpcional(sc, "Novo nome (Enter para manter '" + d.getNome() + "'): "); if (!nome.isEmpty()) d.setNome(nome);
                    LocalDate data = Input.lerDataOpcional(sc, "Nova data (Enter para manter '" + d.getDataNascimento() + "'): "); if (data != null) d.setDataNascimento(data);
                    String email = Input.lerOpcional(sc, "Novo email (Enter para manter): "); if (!email.isEmpty()) d.setEmail(email);
                    String web = Input.lerOpcional(sc, "Nova web (Enter para manter): "); if (!web.isEmpty()) d.setPaginaWeb(web);
                    String morada = Input.lerOpcional(sc, "Nova morada (Enter para manter): "); if (!morada.isEmpty()) d.setMorada(morada);
                    String clube = Input.lerOpcional(sc, "Nova morada clube (Enter para manter): "); if (!clube.isEmpty()) d.setMoradaClubeFas(clube);
                    String obs = Input.lerOpcional(sc, "Novas observações (Enter para manter): "); if (!obs.isEmpty()) d.setObservacoes(obs);
                    ctrl.atualizarDiretor(idx, d);
                    System.out.println("Atualizado.");
                    break;
                }
                case "4": {
                    if (lista.isEmpty()) { System.out.println("(Não há diretores.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a remover: ", lista.size());
                    ctrl.removerDiretor(idx);
                    System.out.println("Removido.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuProdutoras() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Produtoras ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            List<Produtora> lista = ctrl.listarProdutoras();
            switch (op) {
                case "1":
                    for (Produtora p : lista) System.out.println(p);
                    break;
                case "2": {
                    String nome = Input.lerObrigatorio(sc, "Nome: ");
                    String morada = Input.lerOpcional(sc, "Morada: ");
                    String web = Input.lerOpcional(sc, "Página web: ");
                    String email = Input.lerOpcional(sc, "Email: ");
                    String estilo = Input.lerOpcional(sc, "Estilo principal: ");
                    String obs = Input.lerOpcional(sc, "Observações: ");
                    ctrl.adicionarProdutora(new Produtora(nome, morada, web, email, estilo, obs));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    if (lista.isEmpty()) { System.out.println("(Não há produtoras.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a editar: ", lista.size());
                    Produtora p = lista.get(idx);
                    String v;
                    v = Input.lerOpcional(sc, "Novo nome (Enter para manter): "); if (!v.isEmpty()) p.setNome(v);
                    v = Input.lerOpcional(sc, "Nova morada (Enter para manter): "); if (!v.isEmpty()) p.setMorada(v);
                    v = Input.lerOpcional(sc, "Nova web (Enter para manter): "); if (!v.isEmpty()) p.setPaginaWeb(v);
                    v = Input.lerOpcional(sc, "Novo email (Enter para manter): "); if (!v.isEmpty()) p.setEmail(v);
                    v = Input.lerOpcional(sc, "Novo estilo (Enter para manter): "); if (!v.isEmpty()) p.setEstiloPrincipal(v);
                    v = Input.lerOpcional(sc, "Novas observações (Enter para manter): "); if (!v.isEmpty()) p.setObservacoes(v);
                    ctrl.atualizarProdutora(idx, p);
                    System.out.println("Atualizado.");
                    break;
                }
                case "4": {
                    if (lista.isEmpty()) { System.out.println("(Não há produtoras.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a remover: ", lista.size());
                    ctrl.removerProdutora(idx);
                    System.out.println("Removido.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuJogos() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Jogos ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            List<Jogo> lista = ctrl.listarJogos();
            switch (op) {
                case "1":
                    for (Jogo j : lista) System.out.println(j);
                    break;
                case "2": {
                    String nome = Input.lerObrigatorio(sc, "Nome: ");
                    int dur = Input.lerInt(sc, "Duração (minutos): ", 0, Integer.MAX_VALUE);
                    String obs = Input.lerOpcional(sc, "Observações: ");
                    String estilo = Input.lerOpcional(sc, "Estilo: ");
                    Diretor d = selecionarDiretor();
                    Produtora p = selecionarProdutora();
                    ctrl.adicionarJogo(new Jogo(nome, dur, obs, d, p, estilo));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    if (lista.isEmpty()) { System.out.println("(Não há jogos.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a editar: ", lista.size());
                    Jogo j = lista.get(idx);
                    String v;
                    v = Input.lerOpcional(sc, "Novo nome (Enter para manter): "); if (!v.isEmpty()) j.setNome(v);
                    Integer durNova = Input.lerIntOpcional(sc, "Nova duração (Enter para manter): ", 0, Integer.MAX_VALUE); if (durNova != null) j.setDuracaoMinutos(durNova);
                    v = Input.lerOpcional(sc, "Novas observações (Enter para manter): "); if (!v.isEmpty()) j.setObservacoes(v);
                    v = Input.lerOpcional(sc, "Novo estilo (Enter para manter): "); if (!v.isEmpty()) j.setEstilo(v);
                    if (Input.lerSimNao(sc, "Alterar diretor? (s/n): ")) j.setDiretor(selecionarDiretor());
                    if (Input.lerSimNao(sc, "Alterar produtora? (s/n): ")) j.setProdutora(selecionarProdutora());
                    ctrl.atualizarJogo(idx, j);
                    System.out.println("Atualizado.");
                    break;
                }
                case "4": {
                    if (lista.isEmpty()) { System.out.println("(Não há jogos.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a remover: ", lista.size());
                    ctrl.removerJogo(idx);
                    System.out.println("Removido.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuProdutos() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Produtos ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            List<Produto> lista = ctrl.listarProdutos();
            switch (op) {
                case "1":
                    for (Produto p : lista) System.out.println(p);
                    break;
                case "2": {
                    String formato = Input.lerObrigatorio(sc, "Formato: ");
                    String plataforma = Input.lerOpcional(sc, "Plataforma: ");
                    double pc = Input.lerDouble(sc, "Preço custo: ", 0, Double.MAX_VALUE);
                    double pv = Input.lerDouble(sc, "Preço venda: ", 0, Double.MAX_VALUE);
                    int stock = Input.lerInt(sc, "Stock: ", 0, Integer.MAX_VALUE);
                    Produto prod = new Produto(formato, plataforma, pc, pv, stock);
                    boolean addJogo = true;
                    while (addJogo) {
                        Jogo j = selecionarJogo();
                        if (j != null) prod.adicionarJogo(j);
                        addJogo = Input.lerSimNao(sc, "Adicionar outro jogo? (s/n): ");
                    }
                    gerirLocalizacoesProduto(prod, true);
                    ctrl.adicionarProduto(prod);
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    if (lista.isEmpty()) { System.out.println("(Não há produtos.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a editar: ", lista.size());
                    Produto p = lista.get(idx);
                    String v;
                    v = Input.lerOpcional(sc, "Novo formato (Enter para manter): "); if (!v.isEmpty()) p.setFormato(v);
                    v = Input.lerOpcional(sc, "Nova plataforma (Enter para manter): "); if (!v.isEmpty()) p.setPlataforma(v);
                    if (Input.lerSimNao(sc, "Gerir localizações? (s/n): ")) gerirLocalizacoesProduto(p, false);
                    Double pcN = Input.lerDoubleOpcional(sc, "Novo preço custo (Enter para manter): ", 0, Double.MAX_VALUE); if (pcN != null) p.setPrecoCusto(pcN);
                    Double pvN = Input.lerDoubleOpcional(sc, "Novo preço venda (Enter para manter): ", 0, Double.MAX_VALUE); if (pvN != null) p.setPrecoVenda(pvN);
                    Integer stN = Input.lerIntOpcional(sc, "Novo stock (Enter para manter): ", 0, Integer.MAX_VALUE); if (stN != null) p.setStock(stN);
                    ctrl.atualizarProduto(idx, p);
                    System.out.println("Atualizado.");
                    break;
                }
                case "4": {
                    if (lista.isEmpty()) { System.out.println("(Não há produtos.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a remover: ", lista.size());
                    ctrl.removerProduto(idx);
                    System.out.println("Removido.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuLocalizacoes() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Localizações ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            List<Localizacao> lista = ctrl.listarLocalizacoes();
            switch (op) {
                case "1":
                    for (Localizacao l : lista) {
                        System.out.println(l.identificacao()
                                + (l.getDescricao() != null && !l.getDescricao().isEmpty() ? " | " + l.getDescricao() : "")
                                + " | Ocupado: " + ctrl.espacoOcupado(l) + "/" + l.getCapacidade());
                    }
                    break;
                case "2": {
                    Localizacao nova = lerLocalizacao(null);
                    ctrl.adicionarLocalizacao(nova);
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    if (lista.isEmpty()) { System.out.println("(Não há localizações.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i).identificacao());
                    int idx = Input.lerIndice(sc, "Índice a editar: ", lista.size());
                    Localizacao editada = lerLocalizacao(lista.get(idx));
                    ctrl.atualizarLocalizacao(idx, editada);
                    System.out.println("Atualizado.");
                    break;
                }
                case "4": {
                    if (lista.isEmpty()) { System.out.println("(Não há localizações.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i).identificacao());
                    int idx = Input.lerIndice(sc, "Índice a remover: ", lista.size());
                    ctrl.removerLocalizacao(idx);
                    System.out.println("Removido.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Lê os dados de uma localização. Se 'base' != null, usa os seus valores como defaults (edição).
    private Localizacao lerLocalizacao(Localizacao base) {
        Localizacao.Tipo tipo;
        if (base == null) {
            int t = Input.lerInt(sc, "Tipo (1=Área, 2=Prateleira): ", 1, 2);
            tipo = (t == 1) ? Localizacao.Tipo.AREA : Localizacao.Tipo.PRATELEIRA;
        } else {
            Integer t = Input.lerIntOpcional(sc, "Tipo (1=Área, 2=Prateleira, Enter para manter): ", 1, 2);
            tipo = (t == null) ? base.getTipo() : (t == 1 ? Localizacao.Tipo.AREA : Localizacao.Tipo.PRATELEIRA);
        }

        String nome;
        if (base == null) {
            nome = Input.lerObrigatorio(sc, "Nome: ");
        } else {
            String n = Input.lerOpcional(sc, "Nome (Enter para manter '" + base.getNome() + "'): ");
            nome = n.isEmpty() ? base.getNome() : n;
        }

        int capacidade;
        if (base == null) {
            capacidade = Input.lerInt(sc, "Capacidade (nº de jogos): ", 0, Integer.MAX_VALUE);
        } else {
            Integer c = Input.lerIntOpcional(sc, "Capacidade (Enter para manter '" + base.getCapacidade() + "'): ", 0, Integer.MAX_VALUE);
            capacidade = (c == null) ? base.getCapacidade() : c;
        }

        int numeroPrateleira = base != null ? base.getNumeroPrateleira() : 0;
        if (tipo == Localizacao.Tipo.PRATELEIRA) {
            if (base == null) {
                numeroPrateleira = Input.lerInt(sc, "Número da prateleira: ", 0, Integer.MAX_VALUE);
            } else {
                Integer np = Input.lerIntOpcional(sc, "Número da prateleira (Enter para manter '" + base.getNumeroPrateleira() + "'): ", 0, Integer.MAX_VALUE);
                if (np != null) numeroPrateleira = np;
            }
        }

        String descricao;
        if (base == null) {
            descricao = Input.lerOpcional(sc, "Descrição: ");
        } else {
            String dsc = Input.lerOpcional(sc, "Descrição (Enter para manter): ");
            descricao = dsc.isEmpty() ? base.getDescricao() : dsc;
        }

        return new Localizacao(tipo, nome, capacidade, numeroPrateleira, descricao);
    }

    private void menuClientes() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Clientes Especiais ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            List<ClienteEspecial> lista = ctrl.listarClientes();
            switch (op) {
                case "1":
                    for (ClienteEspecial c : lista) System.out.println(c);
                    break;
                case "2": {
                    String nome = Input.lerObrigatorio(sc, "Nome: ");
                    String contacto = Input.lerOpcional(sc, "Contacto: ");
                    String email = Input.lerOpcional(sc, "Email: ");
                    String morada = Input.lerOpcional(sc, "Morada: ");
                    ctrl.adicionarCliente(new ClienteEspecial(nome, contacto, email, morada));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    if (lista.isEmpty()) { System.out.println("(Não há clientes.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a editar: ", lista.size());
                    ClienteEspecial c = lista.get(idx);
                    String v;
                    v = Input.lerOpcional(sc, "Novo nome (Enter para manter): "); if (!v.isEmpty()) c.setNome(v);
                    v = Input.lerOpcional(sc, "Novo contacto (Enter para manter): "); if (!v.isEmpty()) c.setContacto(v);
                    v = Input.lerOpcional(sc, "Novo email (Enter para manter): "); if (!v.isEmpty()) c.setEmail(v);
                    v = Input.lerOpcional(sc, "Nova morada (Enter para manter): "); if (!v.isEmpty()) c.setMorada(v);
                    ctrl.atualizarCliente(idx, c);
                    System.out.println("Atualizado.");
                    break;
                }
                case "4": {
                    if (lista.isEmpty()) { System.out.println("(Não há clientes.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a remover: ", lista.size());
                    ctrl.removerCliente(idx);
                    System.out.println("Removido.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void menuPromocoes() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Promoções ---");
            System.out.println("1. Listar");
            System.out.println("2. Adicionar");
            System.out.println("3. Editar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            List<Promocao> lista = ctrl.listarPromocoes();
            switch (op) {
                case "1":
                    for (Promocao p : lista) System.out.println(p);
                    break;
                case "2": {
                    String nome = Input.lerObrigatorio(sc, "Nome: ");
                    double desc = Input.lerDouble(sc, "Desconto (%): ", 0, 100);
                    LocalDate di = Input.lerData(sc, "Data início (aaaa-mm-dd): ");
                    LocalDate df = Input.lerData(sc, "Data fim (aaaa-mm-dd): ", di);
                    String obs = Input.lerOpcional(sc, "Observações: ");
                    ctrl.adicionarPromocao(new Promocao(nome, desc, di, df, obs));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    if (lista.isEmpty()) { System.out.println("(Não há promoções.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a editar: ", lista.size());
                    Promocao p = lista.get(idx);
                    String v = Input.lerOpcional(sc, "Novo nome (Enter para manter): "); if (!v.isEmpty()) p.setNome(v);
                    Double descN = Input.lerDoubleOpcional(sc, "Novo desconto (Enter para manter): ", 0, 100); if (descN != null) p.setPercentagemDesconto(descN);
                    LocalDate diN = Input.lerDataOpcional(sc, "Nova data início (Enter para manter): "); if (diN != null) p.setDataInicio(diN);
                    LocalDate dfN = Input.lerDataOpcional(sc, "Nova data fim (Enter para manter): ");
                    while (dfN != null && dfN.isBefore(p.getDataInicio())) {
                        System.out.println("A data de fim não pode ser anterior ao início (" + p.getDataInicio() + ").");
                        dfN = Input.lerDataOpcional(sc, "Nova data fim (Enter para manter): ");
                    }
                    if (dfN != null) p.setDataFim(dfN);
                    v = Input.lerOpcional(sc, "Novas observações (Enter para manter): "); if (!v.isEmpty()) p.setObservacoes(v);
                    ctrl.atualizarPromocao(idx, p);
                    System.out.println("Atualizado.");
                    break;
                }
                case "4": {
                    if (lista.isEmpty()) { System.out.println("(Não há promoções.)"); break; }
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    int idx = Input.lerIndice(sc, "Índice a remover: ", lista.size());
                    ctrl.removerPromocao(idx);
                    System.out.println("Removido.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void registarVenda() {
        System.out.println("\n--- Registar Venda ---");
        Produto prod = selecionarProduto();
        if (prod == null) return;
        int qtd = Input.lerInt(sc, "Quantidade: ", 1, Integer.MAX_VALUE);
        String empregado = Input.lerObrigatorio(sc, "Nome do empregado: ");
        ClienteEspecial cli = Input.lerSimNao(sc, "Cliente especial? (s/n): ") ? selecionarCliente() : null;
        if (ctrl.registarVenda(empregado, prod, qtd, cli)) {
            System.out.println("Venda registada com sucesso.");
        } else {
            System.out.println("Stock insuficiente.");
        }
    }

    private void menuEstatisticas() {
        boolean exec = true;
        while (exec) {
            System.out.println("\n--- Estatísticas ---");
            System.out.println("1. Jogos mais vendidos");
            System.out.println("2. Estilos mais procurados");
            System.out.println("3. Diretores mais vendidos");
            System.out.println("4. Produtoras mais vendidas");
            System.out.println("5. Todas as vendas");
            System.out.println("0. Voltar");
            String op = Input.lerOpcional(sc, "Opção: ");
            switch (op) {
                case "1":
                    for (Map.Entry<Jogo, Long> e : ctrl.jogosMaisVendidos()) {
                        System.out.println(e.getKey().getNome() + ": " + e.getValue());
                    }
                    break;
                case "2":
                    for (Map.Entry<String, Long> e : ctrl.estilosMaisProcurados()) {
                        System.out.println(e.getKey() + ": " + e.getValue());
                    }
                    break;
                case "3":
                    for (Map.Entry<Diretor, Long> e : ctrl.diretoresMaisVendidos()) {
                        System.out.println(e.getKey().getNome() + ": " + e.getValue());
                    }
                    break;
                case "4":
                    for (Map.Entry<Produtora, Long> e : ctrl.produtorasMaisVendidas()) {
                        System.out.println(e.getKey().getNome() + ": " + e.getValue());
                    }
                    break;
                case "5":
                    for (Venda v : ctrl.listarVendas()) System.out.println(v);
                    break;
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void alterarPassword() {
        String nova = Input.lerObrigatorio(sc, "Nova password: ");
        String conf = Input.lerObrigatorio(sc, "Confirmar password: ");
        if (nova.equals(conf)) {
            login.alterarPassword(nova);
            System.out.println("Password alterada.");
        } else {
            System.out.println("Passwords não coincidem.");
        }
    }

    // Seletores auxiliares (devolvem null se não houver itens ou se o utilizador escolher -1)
    private Diretor selecionarDiretor() {
        List<Diretor> lista = ctrl.listarDiretores();
        if (lista.isEmpty()) { System.out.println("(Não há diretores.)"); return null; }
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        int idx = Input.lerInt(sc, "Escolher diretor (índice, -1 = nenhum): ", -1, lista.size() - 1);
        return idx < 0 ? null : lista.get(idx);
    }

    private Produtora selecionarProdutora() {
        List<Produtora> lista = ctrl.listarProdutoras();
        if (lista.isEmpty()) { System.out.println("(Não há produtoras.)"); return null; }
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        int idx = Input.lerInt(sc, "Escolher produtora (índice, -1 = nenhum): ", -1, lista.size() - 1);
        return idx < 0 ? null : lista.get(idx);
    }

    private Jogo selecionarJogo() {
        List<Jogo> lista = ctrl.listarJogos();
        if (lista.isEmpty()) { System.out.println("(Não há jogos.)"); return null; }
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        int idx = Input.lerInt(sc, "Escolher jogo (índice, -1 = nenhum): ", -1, lista.size() - 1);
        return idx < 0 ? null : lista.get(idx);
    }

    private Produto selecionarProduto() {
        List<Produto> lista = ctrl.listarProdutos();
        if (lista.isEmpty()) { System.out.println("(Não há produtos.)"); return null; }
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        int idx = Input.lerInt(sc, "Escolher produto (índice, -1 = cancelar): ", -1, lista.size() - 1);
        return idx < 0 ? null : lista.get(idx);
    }

    private ClienteEspecial selecionarCliente() {
        List<ClienteEspecial> lista = ctrl.listarClientes();
        if (lista.isEmpty()) { System.out.println("(Não há clientes.)"); return null; }
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        int idx = Input.lerInt(sc, "Escolher cliente (índice, -1 = nenhum): ", -1, lista.size() - 1);
        return idx < 0 ? null : lista.get(idx);
    }

    // Gere a lista de localizações de um produto (adicionar/remover). novo=true se o produto ainda não foi guardado.
    private void gerirLocalizacoesProduto(Produto prod, boolean novo) {
        boolean exec = true;
        while (exec) {
            System.out.println("\nLocalizações do produto:");
            if (prod.getLocalizacoes().isEmpty()) {
                System.out.println(" (nenhuma)");
            } else {
                for (Localizacao l : prod.getLocalizacoes()) System.out.println(" - " + l.identificacao());
            }
            System.out.println("1. Adicionar localização");
            System.out.println("2. Remover localização");
            System.out.println("0. Terminar");
            String op = Input.lerOpcional(sc, "Opção: ");
            switch (op) {
                case "1": {
                    Localizacao l = selecionarLocalizacao(prod.getJogos().size(), novo ? null : prod);
                    if (l != null) {
                        if (prod.getLocalizacoes().contains(l)) {
                            System.out.println("O produto já está nessa localização.");
                        } else {
                            prod.adicionarLocalizacao(l);
                            System.out.println("Localização adicionada.");
                        }
                    }
                    break;
                }
                case "2": {
                    List<Localizacao> atuais = new ArrayList<>(prod.getLocalizacoes());
                    if (atuais.isEmpty()) {
                        System.out.println("Sem localizações para remover.");
                        break;
                    }
                    for (int i = 0; i < atuais.size(); i++) System.out.println(i + ". " + atuais.get(i).identificacao());
                    int idx = Input.lerIndice(sc, "Índice a remover: ", atuais.size());
                    prod.removerLocalizacao(atuais.get(idx));
                    System.out.println("Localização removida.");
                    break;
                }
                case "0":
                    exec = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Escolhe uma localização validando que cabem nJogos. Volta a pedir se não couber; -1 cancela.
    private Localizacao selecionarLocalizacao(int nJogos, Produto produtoAtual) {
        List<Localizacao> lista = ctrl.listarLocalizacoes();
        if (lista.isEmpty()) {
            System.out.println("(Não existem localizações criadas.)");
            return null;
        }
        while (true) {
            System.out.println("-1. Cancelar");
            for (int i = 0; i < lista.size(); i++) {
                Localizacao l = lista.get(i);
                System.out.println(i + ". " + l.identificacao() + " | Livre: " + ctrl.espacoLivre(l));
            }
            int idx = Input.lerInt(sc, "Escolher localização (índice): ", -1, lista.size() - 1);
            if (idx < 0) {
                return null;
            }
            Localizacao escolhida = lista.get(idx);
            if (ctrl.cabe(escolhida, nJogos, produtoAtual)) {
                return escolhida;
            }
            int ocupado = ctrl.espacoOcupado(escolhida);
            if (produtoAtual != null && produtoAtual.getLocalizacoes().contains(escolhida)) {
                ocupado -= produtoAtual.getJogos().size();
            }
            System.out.println("Sem espaço nessa localização: livre " + (escolhida.getCapacidade() - ocupado)
                    + ", precisa " + nJogos + ". Escolhe outra ou -1 para cancelar.");
        }
    }
}
