package tools;

import model.*;
import persistence.FileManager;
import persistence.TextFileManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Seeder de dados ficticios. NAO faz parte da aplicacao entregue.
 * Acrescenta (append) dados coerentes aos ficheiros .dat/.txt existentes,
 * usando as proprias classes do projecto para garantir serializacao compativel.
 *
 * Uso: java -cp <out> tools.SeedData "<caminho absoluto da pasta data>"
 */
public class SeedData {

    public static void main(String[] args) {
        String dir = (args.length > 0 ? args[0] : "data");
        if (!dir.endsWith("/") && !dir.endsWith("\\")) dir = dir + "/";

        // ---------- Managers ----------
        FileManager<Diretor> fmDir = new FileManager<>(dir + "diretores.dat");
        FileManager<Produtora> fmProd = new FileManager<>(dir + "produtoras.dat");
        FileManager<Jogo> fmJogo = new FileManager<>(dir + "jogos.dat");
        FileManager<Localizacao> fmLoc = new FileManager<>(dir + "localizacoes.dat");
        FileManager<Produto> fmProduto = new FileManager<>(dir + "produtos.dat");
        FileManager<ClienteEspecial> fmCli = new FileManager<>(dir + "clientes.dat");
        FileManager<Promocao> fmPromo = new FileManager<>(dir + "promocoes.dat");
        FileManager<Venda> fmVenda = new FileManager<>(dir + "vendas.dat");
        TextFileManager tfmVendas = new TextFileManager(dir + "vendas.txt");

        // ---------- Carregar listas existentes (append, nao substitui) ----------
        List<Diretor> diretores = fmDir.ler();
        List<Produtora> produtoras = fmProd.ler();
        List<Jogo> jogos = fmJogo.ler();
        List<Localizacao> locs = fmLoc.ler();
        List<Produto> produtos = fmProduto.ler();
        List<ClienteEspecial> clientes = fmCli.ler();
        List<Promocao> promocoes = fmPromo.ler();
        List<Venda> vendas = fmVenda.ler();

        // ---------- DIRETORES ----------
        Diretor dKojima = new Diretor("Hideo Kojima", LocalDate.of(1963, 8, 24),
                "hideo@kojimaprod.jp", "kojimaproductions.jp", "Tóquio, Japão",
                "Clube Kojima — Tóquio", "Criador da série Metal Gear");
        Diretor dMiyamoto = new Diretor("Shigeru Miyamoto", LocalDate.of(1952, 11, 16),
                "shigeru@nintendo.jp", "nintendo.com", "Quioto, Japão",
                "Clube Nintendo — Quioto", "Criador de Mario e Zelda");
        Diretor dMiyazaki = new Diretor("Hidetaka Miyazaki", LocalDate.of(1974, 1, 1),
                "miyazaki@fromsoftware.jp", "fromsoftware.jp", "Tóquio, Japão",
                "Clube Soulsborne", "Diretor da FromSoftware");
        diretores.add(dKojima);
        diretores.add(dMiyamoto);
        diretores.add(dMiyazaki);

        // ---------- PRODUTORAS ----------
        Produtora pKonami = new Produtora("Konami", "Tóquio, Japão", "konami.com",
                "info@konami.com", "Aventura", "Editora de Metal Gear");
        Produtora pNintendo = new Produtora("Nintendo", "Quioto, Japão", "nintendo.com",
                "info@nintendo.com", "Plataformas", "Editora de Mario e Zelda");
        Produtora pFrom = new Produtora("FromSoftware", "Tóquio, Japão", "fromsoftware.jp",
                "info@fromsoftware.jp", "RPG", "Editora de Dark Souls e Elden Ring");
        produtoras.add(pKonami);
        produtoras.add(pNintendo);
        produtoras.add(pFrom);

        // ---------- JOGOS ----------
        Jogo jMgs = new Jogo("Metal Gear Solid", 900, "Clássico de stealth", dKojima, pKonami, "Aventura");
        Jogo jDeath = new Jogo("Death Stranding", 2400, "Mundo aberto", dKojima, pKonami, "Ação");
        Jogo jZelda = new Jogo("The Legend of Zelda", 1800, "Aventura épica", dMiyamoto, pNintendo, "Aventura");
        Jogo jMario = new Jogo("Super Mario Bros", 600, "Plataformas clássico", dMiyamoto, pNintendo, "Plataformas");
        Jogo jElden = new Jogo("Elden Ring", 3000, "Mundo aberto souls", dMiyazaki, pFrom, "RPG");
        Jogo jDark = new Jogo("Dark Souls", 2700, "Souls original", dMiyazaki, pFrom, "RPG");
        jogos.add(jMgs);
        jogos.add(jDeath);
        jogos.add(jZelda);
        jogos.add(jMario);
        jogos.add(jElden);
        jogos.add(jDark);

        // ---------- LOCALIZACOES ----------
        Localizacao locMontra = new Localizacao(Localizacao.Tipo.AREA, "Montra Clássicos", 50, 0,
                "Zona de destaque à entrada");
        Localizacao locRetro = new Localizacao(Localizacao.Tipo.PRATELEIRA, "Retro RPG", 30, 12,
                "Prateleira dedicada a RPGs");
        locs.add(locMontra);
        locs.add(locRetro);

        // ---------- PRODUTOS ----------
        Produto prodNes = new Produto("Cartucho", "NES", 8.50, 24.99, 7);
        prodNes.adicionarJogo(jMario);
        prodNes.adicionarJogo(jZelda);
        prodNes.adicionarLocalizacao(locMontra);

        Produto prodPs5 = new Produto("Blu-ray", "PS5", 35.00, 59.99, 12);
        prodPs5.adicionarJogo(jElden);
        prodPs5.adicionarLocalizacao(locRetro);

        Produto prodPs2 = new Produto("DVD", "PS2", 12.00, 29.99, 4);
        prodPs2.adicionarJogo(jMgs);
        prodPs2.adicionarLocalizacao(locMontra);

        Produto prodPc = new Produto("CD", "PC", 10.00, 19.99, 9);
        prodPc.adicionarJogo(jDark);
        prodPc.adicionarLocalizacao(locRetro);

        produtos.add(prodNes);
        produtos.add(prodPs5);
        produtos.add(prodPs2);
        produtos.add(prodPc);

        // ---------- CLIENTES ESPECIAIS ----------
        ClienteEspecial cAna = new ClienteEspecial("Ana Sousa", "912345678",
                "ana.sousa@email.com", "Rua das Flores 12, Coimbra");
        ClienteEspecial cBruno = new ClienteEspecial("Bruno Lima", "936547890",
                "bruno.lima@email.com", "Av. Central 45, Lisboa");
        clientes.add(cAna);
        clientes.add(cBruno);

        // ---------- PROMOCOES ----------
        Promocao promoVerao = new Promocao("Saldos de Verão", 20.0,
                LocalDate.of(2026, 6, 1), LocalDate.of(2026, 8, 31), "Desconto de verão");
        Promocao promoNatal = new Promocao("Promo Natal", 15.0,
                LocalDate.of(2025, 12, 1), LocalDate.of(2025, 12, 31), "Já terminada");
        promocoes.add(promoVerao);
        promocoes.add(promoNatal);

        // ---------- VENDAS ----------
        // (data | empregado | produto | qtd | promo? | cliente?)
        registar(vendas, tfmVendas, "Carlos", prodNes, 2,
                LocalDateTime.of(2026, 6, 5, 10, 30), promoVerao, cAna);
        registar(vendas, tfmVendas, "Carlos", prodPs5, 1,
                LocalDateTime.of(2026, 6, 6, 16, 15), promoVerao, cBruno);
        registar(vendas, tfmVendas, "Sofia", prodPs2, 3,
                LocalDateTime.of(2026, 6, 7, 11, 0), null, null);
        registar(vendas, tfmVendas, "Sofia", prodPc, 1,
                LocalDateTime.of(2026, 6, 8, 9, 45), promoVerao, cAna);
        registar(vendas, tfmVendas, "Carlos", prodNes, 1,
                LocalDateTime.of(2026, 6, 9, 18, 20), null, cBruno);

        // Baixar stock de acordo com as vendas registadas
        prodNes.setStock(prodNes.getStock() - 3);
        prodPs5.setStock(prodPs5.getStock() - 1);
        prodPs2.setStock(prodPs2.getStock() - 3);
        prodPc.setStock(prodPc.getStock() - 1);

        // ---------- GRAVAR TUDO ----------
        fmDir.escrever(diretores);
        fmProd.escrever(produtoras);
        fmJogo.escrever(jogos);
        fmLoc.escrever(locs);
        fmProduto.escrever(produtos);
        fmCli.escrever(clientes);     // já com historicoCompras preenchido
        fmPromo.escrever(promocoes);
        fmVenda.escrever(vendas);

        System.out.println("Seed concluido.");
        System.out.println("Diretores: " + diretores.size() + " | Produtoras: " + produtoras.size()
                + " | Jogos: " + jogos.size() + " | Localizacoes: " + locs.size()
                + " | Produtos: " + produtos.size() + " | Clientes: " + clientes.size()
                + " | Promocoes: " + promocoes.size() + " | Vendas: " + vendas.size());
    }

    private static void registar(List<Venda> vendas, TextFileManager tfm, String empregado,
                                 Produto produto, int qtd, LocalDateTime data,
                                 Promocao promo, ClienteEspecial cliente) {
        double total = produto.getPrecoVenda() * qtd;
        if (promo != null) total = promo.aplicarDesconto(total);

        Venda v = new Venda(empregado, produto, qtd, total, data, promo, cliente);
        vendas.add(v);
        if (cliente != null) cliente.adicionarCompra(v);

        String linha = String.format("%s | Empregado: %s | Produto: %s | Qtd: %d | Total: %.2f€ | Cliente: %s | Promo: %s",
                v.getDataVenda(), empregado, produto.getFormato(), qtd, total,
                cliente != null ? cliente.getNome() : "Anónimo",
                promo != null ? promo.getNome() : "Nenhuma");
        tfm.escreverLinha(linha, true);
    }
}
