<!--
  RELATÓRIO FINAL — "Loja de Jogos" (POO 2025/26)
  Redigido em Markdown; será transposto para o modelo relatorioPOO.docx no fim.
  Estrutura segue docs/plano_relatorio.md.
-->

# Loja de Jogos — Relatório

> **Capa (a preencher no Word):** Escola Superior de Tecnologia e Gestão · CTeSP-TPSI ·
> Programação Orientada a Objectos · 2025/26 · título "Loja de Jogos" · data · nome(s) e
> número(s) do(s) aluno(s).

---

## 1. Lista de figuras
*(a gerar quando os diagramas estiverem prontos — ver `plano_relatorio.md`)*

---

## 2. Introdução

Este relatório descreve a aplicação **Loja de Jogos**, desenvolvida em Java no âmbito da
unidade curricular de Programação Orientada a Objectos. O dono de uma loja que vende
produtos atuais, clássicos e vintage pretendia um programa que permitisse aos clientes
consultar informação sobre os produtos (por título, diretor e outros detalhes) e que, ao
mesmo tempo, processasse as vendas e mantivesse a contabilidade em dia.

A aplicação é de consola e está dividida em duas zonas: uma **zona de administrador**,
protegida por palavra-passe, onde se gere todo o catálogo (jogos, diretores, produtoras,
produtos, localizações, clientes e promoções), se registam vendas e se consultam
estatísticas; e uma **zona de cliente**, de acesso livre, dedicada a pesquisas sobre os
jogos e produtos. Todos os dados persistem entre sessões, sendo guardados em **ficheiros
de objetos** (`.dat`) e em **ficheiros de texto** (`.txt`).

---

## 3. Análise de requisitos

Do enunciado foram levantados 13 requisitos (R1–R13). A tabela seguinte resume cada um e
indica onde é cumprido no código.

| Req | Descrição (resumo) | Onde é cumprido |
|-----|--------------------|-----------------|
| **R1** | A loja vende produtos (CD, DVD, Blu-ray, cartuchos, etc.). | `model.Produto` (campo `formato`) |
| **R2** | Cada produto é composto por 1+ jogos; um jogo pode estar em vários produtos; versões diferentes são jogos diferentes. | `Produto.jogos : List<Jogo>`; cada `Jogo` é uma entidade própria |
| **R3** | Cada jogo tem diretor, produtora e estilo. | `Jogo` (`diretor`, `produtora`, `estilo`) |
| **R4** | Os produtos têm formato e plataforma (PS4, XBOX, PC, …). | `Produto` (`formato`, `plataforma`) |
| **R5** | Localização na loja (área ou prateleira); pode haver produtos sem existência física; guardar preço de custo e de venda. | `model.Localizacao` (enum `Tipo`); `Produto.localizacoes` (pode ficar vazia); `precoCusto`/`precoVenda` |
| **R6** | Diretores: nome, data de nascimento, email, página web, morada, morada de clube de fãs, observações — em **ficheiros de objetos**. | `model.Diretor`; `persistence.FileManager`; `data/diretores.dat` |
| **R7** | Produtoras: nome, morada, página web, email, estilo principal, observações — em ficheiros de objetos. Cliente vê jogos de um diretor, jogos de um produto e que diretores trabalham para uma produtora. | `model.Produtora`; `data/produtoras.dat`; `LojaController.listarDiretoresDaProdutora` |
| **R8** | Pesquisar jogos por nome e ver os produtos que os contêm; outros jogos do mesmo diretor/estilo; outros produtos adquiridos por clientes que compraram o produto pesquisado. | `LojaController.pesquisarJogosPor*`, `produtosQueContemJogo`, `produtosCompradosPorClientesQueCompraram` |
| **R9** | Empregados inserem e atualizam dados; apagamentos permitidos para corrigir enganos. | CRUD em `gestao.Gere*` e `ui.MenuAdmin` |
| **R10** | Registar vendas e diminuir o stock; guardar empregado, o que foi vendido, quantidade, custo e data — em **ficheiro de texto**. | `LojaController.registarVenda`, `model.Venda`, `data/vendas.txt` |
| **R11** | Clientes especiais (não anónimos) com registo das suas compras. | `model.ClienteEspecial` (`historicoCompras`) |
| **R12** | Zona de administrador e de cliente; password pré-definida num ficheiro de texto, encriptada após o primeiro início de sessão. | `ui.LoginManager`, `data/pass.txt`, `utils.CryptoUtils` (SHA-256) |
| **R13** | Saber jogos mais vendidos, estilos mais procurados, produtoras/diretores mais vendidos; gerir promoções e saber se um produto foi vendido no âmbito de uma promoção. | Estatísticas no `LojaController`; `model.Promocao`; `Venda.promocao` |

Os requisitos podem agrupar-se em cinco famílias: **gestão de entidades** (R1–R7, R9),
**pesquisas** (R7, R8), **vendas e stock** (R10, R11), **segurança/acesso** (R12) e
**estatísticas e promoções** (R13).

---

## 4. Estrutura do sistema — Arquitetura em camadas

A aplicação está organizada segundo uma **arquitetura em camadas**, em que cada pacote tem
uma responsabilidade única e as dependências fluem num só sentido:

```
ui  →  controller  →  gestao  →  persistence
                  (model é transversal a todas as camadas)
```

| Pacote | Camada | Responsabilidade |
|--------|--------|------------------|
| `ui` | Apresentação | Menus de consola; lê o input e imprime resultados (`MenuPrincipal`, `MenuAdmin`, `MenuCliente`, `LoginManager`) |
| `controller` | Negócio / Serviço | `LojaController` — fachada que concentra regras de negócio: venda, estatísticas, pesquisas e cálculo de capacidade |
| `gestao` | Serviço / Repositório | Classes `Gere*` — CRUD em memória de cada entidade e delegação da persistência |
| `persistence` | Acesso a dados | `FileManager<T>` (serialização de objetos) e `TextFileManager` (ficheiros de texto) |
| `model` | Domínio | Entidades de negócio (`Jogo`, `Diretor`, `Produtora`, `Produto`, `Venda`, `ClienteEspecial`, `Promocao`, `Localizacao`, `Utilizador`) |
| `utils` | Transversal | `Input` (validação de entradas) e `CryptoUtils` (encriptação SHA-256) |

Optou-se por esta organização (e não pelo padrão MVC) porque descreve com rigor o que o
código efetivamente faz: não existe uma *View* a observar o *Model*, e a classe
`LojaController` desempenha o papel de **fachada de negócio** e não de controlador no
sentido do MVC. Ainda assim, o nome do pacote `controller` e o papel coordenador do
`LojaController` inspiram-se nessa ideia de mediação entre a apresentação e os dados.

A separação tem vantagens claras: a camada `ui` desconhece como os dados são guardados; a
classe `FileManager<T>` é **genérica** e serve qualquer tipo serializável, evitando
repetição; e toda a validação de input está centralizada em `utils.Input`.

---

## 5. Trabalho realizado

### 5.1 Modelo de domínio

As entidades de negócio estão no pacote `model` e são todas `Serializable` (com
`serialVersionUID` próprio), o que permite guardá-las em ficheiros de objetos.

| Entidade | Atributos principais | Relações |
|----------|----------------------|----------|
| `Jogo` | nome, duração (min.), observações, estilo | → `Diretor` (0..1), → `Produtora` (0..1) |
| `Diretor` | nome, data de nascimento, email, página web, morada, morada do clube de fãs, observações | — |
| `Produtora` | nome, morada, página web, email, estilo principal, observações | — |
| `Produto` | formato, plataforma, preço de custo, preço de venda, stock, disponível | → `Jogo` (1..*), → `Localizacao` (0..*) |
| `Localizacao` | tipo (ÁREA/PRATELEIRA), nome, capacidade, nº de prateleira, descrição | — |
| `Venda` | empregado, quantidade, custo total, data/hora | → `Produto` (1), → `Promocao` (0..1), → `ClienteEspecial` (0..1) |
| `ClienteEspecial` | nome, contacto, email, morada | → `Venda` (0..*) — histórico de compras |
| `Promocao` | nome, % de desconto, data início, data fim, observações | — |
| `Utilizador` | username, hash da password, tipo (ADMIN/CLIENTE) | — |

Destacam-se duas regras do domínio que o enunciado impõe: um **produto é composto por um ou
mais jogos** e o **mesmo jogo pode pertencer a vários produtos** (R2), modelado por
`Produto.jogos : List<Jogo>`; e a **localização pode ser uma área ou uma prateleira** (R5),
resolvida com o enum `Localizacao.Tipo`. Algumas entidades têm lógica própria: `Promocao`
sabe dizer se `estaAtiva()` numa data e como `aplicarDesconto()` a um preço.

### 5.2 Persistência

A persistência está isolada no pacote `persistence` e usa dois mecanismos:

- **Ficheiros de objetos (`.dat`)** — a classe **genérica** `FileManager<T>` serializa a
  lista completa de uma entidade com `ObjectOutputStream` e lê-a com `ObjectInputStream`. O
  método `ler()` devolve uma lista vazia se o ficheiro não existir ou estiver vazio
  (tratamento de `EOFException`), o que torna o primeiro arranque robusto. Cada entidade tem
  o seu ficheiro (`diretores.dat`, `jogos.dat`, …). Cumpre R6 e R7.
- **Ficheiros de texto (`.txt`)** — `TextFileManager` lê/escreve linhas de texto. É usado
  para o registo legível das vendas (`vendas.txt`, R10), para a palavra-passe (`pass.txt`,
  R12) e para a exportação de resultados das pesquisas do cliente.

Cada classe de `gestao` (`Gere*`) carrega a sua lista no arranque e **reescreve o ficheiro
após cada modificação**, garantindo que os dados persistem entre sessões.

### 5.3 Algoritmos principais

#### 5.3.1 Autenticação e encriptação da palavra-passe (R12)

No primeiro arranque é criada uma password por defeito (`admin123`) em texto simples. No
primeiro login bem-sucedido, ela é substituída pelo seu **resumo SHA-256 em Base64**; daí
em diante a verificação é feita por comparação de *hashes*. A deteção de "ainda em texto
simples" usa o comprimento (um hash SHA-256 em Base64 tem 44 caracteres).

**Pseudocódigo:**
```
FUNÇÃO autenticarAdmin(password):
    SE NÃO existePassword(): RETORNAR falso
    conteudo ← ler(pass.txt)
    SE comprimento(conteudo) < 44 ENTÃO          // ainda em texto simples
        SE conteudo == password ENTÃO
            escrever(pass.txt, SHA256_Base64(password))   // encripta no 1.º login
            RETORNAR verdadeiro
        SENÃO RETORNAR falso
    SENÃO                                          // já encriptada
        RETORNAR SHA256_Base64(password) == conteudo
```

> **[Figura 3 — Fluxograma da autenticação e encriptação da palavra-passe]**
> *(placeholder — diagrama a inserir no Word, baseado no pseudocódigo acima)*

#### 5.3.2 Registo de venda (R10, R13)

Valida o stock, aplica uma promoção ativa (se existir), diminui o stock, cria a venda,
associa-a ao cliente especial (se houver) e regista-a no ficheiro de texto.

**Pseudocódigo:**
```
FUNÇÃO registarVenda(empregado, produto, quantidade, cliente):
    SE produto.stock < quantidade: RETORNAR falso          // sem stock
    precoFinal ← produto.precoVenda × quantidade
    promoAtiva ← NULO
    PARA CADA p EM promoções:
        SE p.estaAtiva():
            promoAtiva ← p
            precoFinal ← p.aplicarDesconto(precoFinal)
            PARAR                                            // 1.ª promoção ativa
    produto.stock ← produto.stock − quantidade; guardar(produtos)
    venda ← nova Venda(empregado, produto, quantidade, precoFinal, agora, promoAtiva, cliente)
    adicionar(venda)
    SE cliente ≠ NULO: cliente.adicionarCompra(venda); guardar(clientes)
    escreverLinha(vendas.txt, formatar(venda))               // ficheiro de texto (R10)
    RETORNAR verdadeiro
```

> **[Figura 4 — Fluxograma do registo de venda]**
> *(placeholder — diagrama a inserir no Word, baseado no pseudocódigo acima)*

#### 5.3.3 Estatísticas — jogos mais vendidos (R13)

Percorre todas as vendas, conta as ocorrências de cada jogo e devolve a lista ordenada por
contagem decrescente. O mesmo padrão serve as restantes estatísticas, mudando apenas o
critério de agrupamento: por **estilo** (`estilosMaisProcurados`), por **diretor**
(`diretoresMaisVendidos`) e por **produtora** (`produtorasMaisVendidas`).

**Pseudocódigo:**
```
FUNÇÃO jogosMaisVendidos():
    contador ← mapa vazio <Jogo, inteiro>
    PARA CADA venda EM vendas:
        PARA CADA jogo EM venda.produto.jogos:
            contador[jogo] ← contador[jogo] + 1
    RETORNAR contador ORDENADO POR valor DESCENDENTE
```

> **[Figura 5 — Fluxograma das estatísticas (jogos mais vendidos)]**
> *(placeholder — diagrama a inserir no Word, baseado no pseudocódigo acima)*

> Implementação: em Java, usa-se a *Stream API* (`flatMap` para obter os jogos de cada
> venda e `Collectors.groupingBy(..., counting())` para a contagem).

### 5.4 Ponto de vista do utilizador

A aplicação arranca no **menu principal** com três opções: *Zona Administrador* (pede
password), *Zona Cliente* e *Sair* (que guarda os dados).

- **Zona de administrador** — gere Diretores, Produtoras, Jogos, Produtos, Localizações,
  Clientes Especiais e Promoções (cada um com **Listar / Adicionar / Editar / Remover**),
  regista vendas, consulta estatísticas e altera a password.
- **Zona de cliente** — oferece oito pesquisas (jogo por nome, por diretor, por estilo;
  produtos que contêm um jogo; outros jogos do mesmo diretor/estilo; produtos comprados
  pelos mesmos clientes; diretores de uma produtora) e permite **exportar os resultados
  para um ficheiro de texto**.

O detalhe de utilização (ecrãs e passos) consta do **manual do utilizador**, em anexo.

### 5.5 Ponto de vista do programador

- **Arquitetura em camadas** (secção 4): a `ui` desconhece a persistência; o
  `LojaController` é a fachada de negócio.
- **Reutilização por genéricos**: `FileManager<T>` serve qualquer entidade serializável; as
  classes `Gere*` partilham o mesmo padrão de CRUD.
- **Validação centralizada**: `utils.Input` concentra a leitura validada (obrigatório vs
  opcional, inteiros/decimais com intervalo, datas ISO, sim/não), repetindo o pedido até a
  entrada ser válida.
- **Identidade por chave de negócio**: `equals()`/`hashCode()` em `Produto`
  (formato + plataforma + nomes dos jogos), `ClienteEspecial` (nome + email) e `Localizacao`
  (nome) — ver justificação em 5.6.

### 5.6 Decisões e pressupostos

Conforme pedido no enunciado, documentam-se as opções tomadas e os desvios:

1. **Identidade por chave de negócio.** Como cada entidade é guardada no seu próprio
   ficheiro, um `Produto` lido de `produtos.dat` e o "mesmo" produto referido numa `Venda`
   lida de `vendas.dat` são objetos diferentes em memória. Sem `equals()`/`hashCode()`
   próprios, a comparação seria por referência e a pesquisa de R8 ("produtos comprados pelos
   mesmos clientes") devolvia sempre vazio. Definiu-se, por isso, igualdade por chave de
   negócio.
2. **Promoção "uma de cada vez".** Numa venda aplica-se a **primeira promoção ativa** da
   lista a qualquer produto; as promoções não estão associadas a produtos específicos.
   Simplificação assumida.
3. **Estatísticas por ocorrência.** As contagens de "mais vendidos" contam **uma ocorrência
   por venda**, não ponderando a quantidade vendida. (Melhoria futura — ver secção 6.)
4. **Segurança da password.** Usa-se SHA-256 em Base64 **sem salt** e deteta-se o estado
   "ainda em texto simples" pelo comprimento (< 44 caracteres). Adequado ao âmbito académico.
5. **Produtos sem existência física.** A lista de localizações de um produto pode ficar
   vazia, permitindo produtos que existem no programa mas não na loja (R5).
6. **Versões de um jogo.** Versões diferentes do mesmo jogo são entidades `Jogo` distintas
   (R2).
7. **Autenticação.** A classe `Utilizador` está prevista no modelo, mas a autenticação
   efetiva do administrador é feita via `pass.txt`/`LoginManager`.

---

## 6. Conclusões e trabalho futuro

O projeto cumpre os 13 requisitos do enunciado. Foi desenvolvida uma aplicação de consola em
Java que permite gerir todo o catálogo da loja (jogos, diretores, produtoras, produtos,
localizações, clientes especiais e promoções) através de operações de listagem, inserção,
edição e remoção; registar vendas com diminuição automática de stock e aplicação de
promoções; consultar estatísticas de vendas; e disponibilizar ao cliente um conjunto de
pesquisas com exportação para ficheiro de texto. O acesso de administrador é protegido por
palavra-passe encriptada e todos os dados persistem entre sessões.

Em termos de **como** foi feito, adotou-se uma **arquitetura em camadas** que separa a
apresentação, o negócio, a gestão das entidades e a persistência, apoiada na serialização de
objetos (`.dat`) e em ficheiros de texto (`.txt`). A reutilização foi conseguida com uma
classe de persistência **genérica** (`FileManager<T>`) e com a centralização da validação de
entradas em `utils.Input`.

As principais **dificuldades** foram a modelação das relações entre entidades e, sobretudo,
a **identidade dos objetos guardados em ficheiros distintos**: como cada entidade é
serializada no seu próprio ficheiro, o "mesmo" produto ou cliente surge como objetos
diferentes em memória após leitura. Isto foi resolvido com a definição de
`equals()`/`hashCode()` por **chave de negócio**, sem a qual a pesquisa de produtos por
clientes comuns (R8) não funcionava entre sessões.

Reconhecem-se algumas **limitações** assumidas (secção 5.6): a promoção é aplicada de forma
global (a primeira ativa) e não está ligada a produtos específicos, e as estatísticas de
"mais vendidos" contam ocorrências por venda sem ponderar a quantidade.

Como **trabalho futuro** identificam-se: (i) unificar as oito classes `Gere*` numa única
classe genérica, reduzindo duplicação; (ii) associar promoções a produtos ou categorias;
(iii) ponderar as estatísticas pela quantidade vendida; (iv) permitir *desabilitar* produtos
em vez de os remover; e (v) evoluir a interface de consola para uma interface gráfica.

## 7. Referências

[1] Enunciado do Trabalho Prático — *Programação Orientada a Objectos*, CTeSP-TPSI,
    Escola Superior de Tecnologia e Gestão, ano letivo 2025/26.
[2] Oracle, *Java Platform, Standard Edition — API Specification*: `java.io.Serializable`,
    `java.io.ObjectOutputStream`/`ObjectInputStream`. https://docs.oracle.com/en/java/javase/
[3] Oracle, *Java SE API*: `java.security.MessageDigest` (algoritmo SHA-256).
[4] Oracle, *Java SE API*: `java.util.stream` (Stream API) e `java.util.stream.Collectors`.

## 8. Bibliografia

- BLOCH, Joshua. *Effective Java*, 3.ª ed. Addison-Wesley, 2018.
- Oracle. *The Java Tutorials*. https://docs.oracle.com/javase/tutorial/
- Material de apoio das aulas de Programação Orientada a Objectos (2025/26).
