package ui;

import controller.LojaController;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
            List<Diretor> lista = ctrl.listarDiretores();
            switch (op) {
                case "1":
                    for (Diretor d : lista) System.out.println(d);
                    break;
                case "2": {
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Data nascimento (aaaa-mm-dd): "); LocalDate dn = LocalDate.parse(sc.nextLine());
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Página web: "); String web = sc.nextLine();
                    System.out.print("Morada: "); String morada = sc.nextLine();
                    System.out.print("Morada clube de fãs: "); String clube = sc.nextLine();
                    System.out.print("Observações: "); String obs = sc.nextLine();
                    ctrl.adicionarDiretor(new Diretor(nome, dn, email, web, morada, clube, obs));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a editar: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < lista.size()) {
                        Diretor d = lista.get(idx);
                        System.out.print("Novo nome (Enter para manter '" + d.getNome() + "'): ");
                        String nome = sc.nextLine(); if (!nome.isEmpty()) d.setNome(nome);
                        System.out.print("Nova data (Enter para manter '" + d.getDataNascimento() + "'): ");
                        String data = sc.nextLine(); if (!data.isEmpty()) d.setDataNascimento(LocalDate.parse(data));
                        System.out.print("Novo email (Enter para manter): ");
                        String email = sc.nextLine(); if (!email.isEmpty()) d.setEmail(email);
                        System.out.print("Nova web (Enter para manter): ");
                        String web = sc.nextLine(); if (!web.isEmpty()) d.setPaginaWeb(web);
                        System.out.print("Nova morada (Enter para manter): ");
                        String morada = sc.nextLine(); if (!morada.isEmpty()) d.setMorada(morada);
                        System.out.print("Nova morada clube (Enter para manter): ");
                        String clube = sc.nextLine(); if (!clube.isEmpty()) d.setMoradaClubeFas(clube);
                        System.out.print("Novas observações (Enter para manter): ");
                        String obs = sc.nextLine(); if (!obs.isEmpty()) d.setObservacoes(obs);
                        ctrl.atualizarDiretor(idx, d);
                        System.out.println("Atualizado.");
                    }
                    break;
                }
                case "4": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
            List<Produtora> lista = ctrl.listarProdutoras();
            switch (op) {
                case "1":
                    for (Produtora p : lista) System.out.println(p);
                    break;
                case "2": {
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Morada: "); String morada = sc.nextLine();
                    System.out.print("Página web: "); String web = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Estilo principal: "); String estilo = sc.nextLine();
                    System.out.print("Observações: "); String obs = sc.nextLine();
                    ctrl.adicionarProdutora(new Produtora(nome, morada, web, email, estilo, obs));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a editar: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < lista.size()) {
                        Produtora p = lista.get(idx);
                        System.out.print("Novo nome (Enter para manter): "); String v = sc.nextLine(); if (!v.isEmpty()) p.setNome(v);
                        System.out.print("Nova morada (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setMorada(v);
                        System.out.print("Nova web (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setPaginaWeb(v);
                        System.out.print("Novo email (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setEmail(v);
                        System.out.print("Novo estilo (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setEstiloPrincipal(v);
                        System.out.print("Novas observações (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setObservacoes(v);
                        ctrl.atualizarProdutora(idx, p);
                        System.out.println("Atualizado.");
                    }
                    break;
                }
                case "4": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
            List<Jogo> lista = ctrl.listarJogos();
            switch (op) {
                case "1":
                    for (Jogo j : lista) System.out.println(j);
                    break;
                case "2": {
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Duração (minutos): "); int dur = Integer.parseInt(sc.nextLine());
                    System.out.print("Observações: "); String obs = sc.nextLine();
                    System.out.print("Estilo: "); String estilo = sc.nextLine();
                    Diretor d = selecionarDiretor();
                    Produtora p = selecionarProdutora();
                    ctrl.adicionarJogo(new Jogo(nome, dur, obs, d, p, estilo));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a editar: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < lista.size()) {
                        Jogo j = lista.get(idx);
                        System.out.print("Novo nome (Enter para manter): "); String v = sc.nextLine(); if (!v.isEmpty()) j.setNome(v);
                        System.out.print("Nova duração (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) j.setDuracaoMinutos(Integer.parseInt(v));
                        System.out.print("Novas observações (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) j.setObservacoes(v);
                        System.out.print("Novo estilo (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) j.setEstilo(v);
                        System.out.print("Alterar diretor? (s/n): ");
                        if (sc.nextLine().equalsIgnoreCase("s")) j.setDiretor(selecionarDiretor());
                        System.out.print("Alterar produtora? (s/n): ");
                        if (sc.nextLine().equalsIgnoreCase("s")) j.setProdutora(selecionarProdutora());
                        ctrl.atualizarJogo(idx, j);
                        System.out.println("Atualizado.");
                    }
                    break;
                }
                case "4": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
            List<Produto> lista = ctrl.listarProdutos();
            switch (op) {
                case "1":
                    for (Produto p : lista) System.out.println(p);
                    break;
                case "2": {
                    System.out.print("Formato: "); String formato = sc.nextLine();
                    System.out.print("Plataforma: "); String plataforma = sc.nextLine();
                    System.out.print("Preço custo: "); double pc = Double.parseDouble(sc.nextLine());
                    System.out.print("Preço venda: "); double pv = Double.parseDouble(sc.nextLine());
                    System.out.print("Stock: "); int stock = Integer.parseInt(sc.nextLine());
                    Produto prod = new Produto(formato, plataforma, pc, pv, stock);
                    boolean addJogo = true;
                    while (addJogo) {
                        Jogo j = selecionarJogo();
                        if (j != null) prod.adicionarJogo(j);
                        System.out.print("Adicionar outro jogo? (s/n): ");
                        addJogo = sc.nextLine().equalsIgnoreCase("s");
                    }
                    gerirLocalizacoesProduto(prod, true);
                    ctrl.adicionarProduto(prod);
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a editar: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < lista.size()) {
                        Produto p = lista.get(idx);
                        System.out.print("Novo formato (Enter para manter): "); String v = sc.nextLine(); if (!v.isEmpty()) p.setFormato(v);
                        System.out.print("Nova plataforma (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setPlataforma(v);
                        System.out.print("Gerir localizações? (s/n): ");
                        if (sc.nextLine().equalsIgnoreCase("s")) gerirLocalizacoesProduto(p, false);
                        System.out.print("Novo preço custo (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setPrecoCusto(Double.parseDouble(v));
                        System.out.print("Novo preço venda (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setPrecoVenda(Double.parseDouble(v));
                        System.out.print("Novo stock (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setStock(Integer.parseInt(v));
                        ctrl.atualizarProduto(idx, p);
                        System.out.println("Atualizado.");
                    }
                    break;
                }
                case "4": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
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
                    if (nova != null) {
                        ctrl.adicionarLocalizacao(nova);
                        System.out.println("Adicionado.");
                    }
                    break;
                }
                case "3": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i).identificacao());
                    System.out.print("Índice a editar: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < lista.size()) {
                        Localizacao editada = lerLocalizacao(lista.get(idx));
                        if (editada != null) {
                            ctrl.atualizarLocalizacao(idx, editada);
                            System.out.println("Atualizado.");
                        }
                    }
                    break;
                }
                case "4": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i).identificacao());
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
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
        System.out.print("Tipo (1=Área, 2=Prateleira): ");
        String t = sc.nextLine();
        Localizacao.Tipo tipo;
        if (t.equals("1")) {
            tipo = Localizacao.Tipo.AREA;
        } else if (t.equals("2")) {
            tipo = Localizacao.Tipo.PRATELEIRA;
        } else if (base != null) {
            tipo = base.getTipo();
        } else {
            System.out.println("Tipo inválido.");
            return null;
        }

        System.out.print("Nome" + (base != null ? " (Enter para manter '" + base.getNome() + "')" : "") + ": ");
        String nome = sc.nextLine();
        if (nome.isEmpty() && base != null) nome = base.getNome();

        System.out.print("Capacidade (nº de jogos)" + (base != null ? " (Enter para manter '" + base.getCapacidade() + "')" : "") + ": ");
        String cap = sc.nextLine();
        int capacidade = (cap.isEmpty() && base != null) ? base.getCapacidade() : Integer.parseInt(cap);

        int numeroPrateleira = base != null ? base.getNumeroPrateleira() : 0;
        if (tipo == Localizacao.Tipo.PRATELEIRA) {
            System.out.print("Número da prateleira" + (base != null ? " (Enter para manter '" + base.getNumeroPrateleira() + "')" : "") + ": ");
            String np = sc.nextLine();
            if (!np.isEmpty()) numeroPrateleira = Integer.parseInt(np);
        }

        System.out.print("Descrição" + (base != null ? " (Enter para manter)" : "") + ": ");
        String descricao = sc.nextLine();
        if (descricao.isEmpty() && base != null) descricao = base.getDescricao();

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
            System.out.print("Opção: ");
            String op = sc.nextLine();
            List<ClienteEspecial> lista = ctrl.listarClientes();
            switch (op) {
                case "1":
                    for (ClienteEspecial c : lista) System.out.println(c);
                    break;
                case "2": {
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Contacto: "); String contacto = sc.nextLine();
                    System.out.print("Email: "); String email = sc.nextLine();
                    System.out.print("Morada: "); String morada = sc.nextLine();
                    ctrl.adicionarCliente(new ClienteEspecial(nome, contacto, email, morada));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a editar: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < lista.size()) {
                        ClienteEspecial c = lista.get(idx);
                        System.out.print("Novo nome (Enter para manter): "); String v = sc.nextLine(); if (!v.isEmpty()) c.setNome(v);
                        System.out.print("Novo contacto (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) c.setContacto(v);
                        System.out.print("Novo email (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) c.setEmail(v);
                        System.out.print("Nova morada (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) c.setMorada(v);
                        ctrl.atualizarCliente(idx, c);
                        System.out.println("Atualizado.");
                    }
                    break;
                }
                case "4": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
            List<Promocao> lista = ctrl.listarPromocoes();
            switch (op) {
                case "1":
                    for (Promocao p : lista) System.out.println(p);
                    break;
                case "2": {
                    System.out.print("Nome: "); String nome = sc.nextLine();
                    System.out.print("Desconto (%): "); double desc = Double.parseDouble(sc.nextLine());
                    System.out.print("Data início (aaaa-mm-dd): "); LocalDate di = LocalDate.parse(sc.nextLine());
                    System.out.print("Data fim (aaaa-mm-dd): "); LocalDate df = LocalDate.parse(sc.nextLine());
                    System.out.print("Observações: "); String obs = sc.nextLine();
                    ctrl.adicionarPromocao(new Promocao(nome, desc, di, df, obs));
                    System.out.println("Adicionado.");
                    break;
                }
                case "3": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a editar: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < lista.size()) {
                        Promocao p = lista.get(idx);
                        System.out.print("Novo nome (Enter para manter): "); String v = sc.nextLine(); if (!v.isEmpty()) p.setNome(v);
                        System.out.print("Novo desconto (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setPercentagemDesconto(Double.parseDouble(v));
                        System.out.print("Nova data início (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setDataInicio(LocalDate.parse(v));
                        System.out.print("Nova data fim (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setDataFim(LocalDate.parse(v));
                        System.out.print("Novas observações (Enter para manter): "); v = sc.nextLine(); if (!v.isEmpty()) p.setObservacoes(v);
                        ctrl.atualizarPromocao(idx, p);
                        System.out.println("Atualizado.");
                    }
                    break;
                }
                case "4": {
                    for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
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
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(sc.nextLine());
        System.out.print("Nome do empregado: ");
        String empregado = sc.nextLine();
        System.out.print("Cliente especial? (s/n): ");
        ClienteEspecial cli = sc.nextLine().equalsIgnoreCase("s") ? selecionarCliente() : null;
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
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
        System.out.print("Nova password: ");
        String nova = sc.nextLine();
        System.out.print("Confirmar password: ");
        String conf = sc.nextLine();
        if (nova.equals(conf)) {
            login.alterarPassword(nova);
            System.out.println("Password alterada.");
        } else {
            System.out.println("Passwords não coincidem.");
        }
    }

    // Seletores auxiliares
    private Diretor selecionarDiretor() {
        List<Diretor> lista = ctrl.listarDiretores();
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        System.out.print("Escolher diretor (índice): ");
        int idx = Integer.parseInt(sc.nextLine());
        return (idx >= 0 && idx < lista.size()) ? lista.get(idx) : null;
    }

    private Produtora selecionarProdutora() {
        List<Produtora> lista = ctrl.listarProdutoras();
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        System.out.print("Escolher produtora (índice): ");
        int idx = Integer.parseInt(sc.nextLine());
        return (idx >= 0 && idx < lista.size()) ? lista.get(idx) : null;
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
            System.out.print("Opção: ");
            String op = sc.nextLine();
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
                    System.out.print("Índice a remover: ");
                    int idx = Integer.parseInt(sc.nextLine());
                    if (idx >= 0 && idx < atuais.size()) {
                        prod.removerLocalizacao(atuais.get(idx));
                        System.out.println("Localização removida.");
                    }
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

    // Escolhe uma localização validando que cabem nJogos. Devolve null se inválida ou sem espaço.
    private Localizacao selecionarLocalizacao(int nJogos, Produto produtoAtual) {
        List<Localizacao> lista = ctrl.listarLocalizacoes();
        if (lista.isEmpty()) {
            System.out.println("(Não existem localizações criadas.)");
            return null;
        }
        for (int i = 0; i < lista.size(); i++) {
            Localizacao l = lista.get(i);
            System.out.println(i + ". " + l.identificacao() + " | Livre: " + ctrl.espacoLivre(l));
        }
        System.out.print("Escolher localização (índice, outro valor = cancelar): ");
        int idx = Integer.parseInt(sc.nextLine());
        if (idx < 0 || idx >= lista.size()) {
            return null;
        }
        Localizacao escolhida = lista.get(idx);
        if (!ctrl.cabe(escolhida, nJogos, produtoAtual)) {
            int ocupado = ctrl.espacoOcupado(escolhida);
            if (produtoAtual != null && produtoAtual.getLocalizacoes().contains(escolhida)) {
                ocupado -= produtoAtual.getJogos().size();
            }
            System.out.println("Sem espaço: livre " + (escolhida.getCapacidade() - ocupado)
                    + ", precisa " + nJogos + ".");
            return null;
        }
        return escolhida;
    }

    private ClienteEspecial selecionarCliente() {
        List<ClienteEspecial> lista = ctrl.listarClientes();
        for (int i = 0; i < lista.size(); i++) System.out.println(i + ". " + lista.get(i));
        System.out.print("Escolher cliente (índice): ");
        int idx = Integer.parseInt(sc.nextLine());
        return (idx >= 0 && idx < lista.size()) ? lista.get(idx) : null;
    }
}
