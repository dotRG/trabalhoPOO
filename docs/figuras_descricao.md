# Descrição das figuras do relatório

Guião para desenhar as figuras (diagrams.net / Word). Numeração igual à Lista de figuras.
Convenção de fluxogramas: **terminador** (oval: Início/Fim), **processo** (retângulo),
**decisão** (losango, saídas Sim/Não), **entrada/saída** (paralelogramo: ler/escrever ficheiro).

---

## Figura 1 — Diagrama de pacotes (arquitetura em camadas)

Sete caixas (pacotes), organizadas de cima para baixo por camada, com setas de dependência
a apontar **para baixo** (quem usa → quem é usado):

```
                         ┌───────┐
                         │  app  │   (Main)
                         └───┬───┘
                             v
                         ┌───────┐
                         │  ui   │   Apresentação
                         └───┬───┘
                             v
                       ┌────────────┐
                       │ controller │  Negócio (fachada)
                       └─────┬──────┘
                             v
                        ┌─────────┐
                        │ gestao  │   Serviço / Repositório
                        └────┬────┘
                             v
                      ┌──────────────┐
                      │ persistence  │  Acesso a dados
                      └──────────────┘

   Transversais (usados por várias camadas):
     ┌───────┐         ┌───────┐
     │ model │         │ utils │
     └───────┘         └───────┘
```

**Setas a desenhar:**
- `app → ui`
- `ui → controller`, `ui → utils`
- `controller → gestao`, `controller → persistence`
- `gestao → persistence`
- `model` é apontado por `ui`, `controller` e `gestao` (entidade transversal).
- `utils` é apontado por `ui`.

**Nota a incluir na legenda (opcional, honestidade técnica):** existe uma dependência
direta `ui → persistence` através do `LoginManager` (que lê/escreve `pass.txt`); é o único
ponto em que a apresentação acede à persistência sem passar pelo `controller`.

---

## Figura 2 — Diagrama de classes do domínio

Nove classes do pacote `model`. Mostrar nome, atributos principais e associações com
multiplicidades. Todas implementam `Serializable` (pode indicar-se com uma nota geral).

**Classes e atributos:**

- **Diretor** — nome, dataNascimento (LocalDate), email, paginaWeb, morada, moradaClubeFas, observacoes
- **Produtora** — nome, morada, paginaWeb, email, estiloPrincipal, observacoes
- **Jogo** — nome, duracaoMinutos (int), observacoes, estilo
- **Produto** — formato, plataforma, precoCusto (double), precoVenda (double), stock (int), disponivel (boolean)
  *(equals/hashCode por formato + plataforma + nomes dos jogos)*
- **Localizacao** — tipo {AREA, PRATELEIRA}, nome, capacidade (int), numeroPrateleira (int), descricao
  *(equals/hashCode por nome)*
- **Venda** — empregado, quantidade (int), custoTotal (double), dataVenda (LocalDateTime)
- **ClienteEspecial** — nome, contacto, email, morada
  *(equals/hashCode por nome + email)*
- **Promocao** — nome, percentagemDesconto (double), dataInicio, dataFim, observacoes
  + métodos `estaAtiva()`, `aplicarDesconto(preço)`
- **Utilizador** — username, passwordHash, tipo {ADMIN, CLIENTE}  *(classe isolada)*

**Associações (com multiplicidades e rótulo):**

| Origem | Mult. | — | Mult. | Destino | Rótulo |
|--------|-------|---|-------|---------|--------|
| Produto | `0..*` | —— | `1..*` | Jogo | "contém" |
| Jogo | `0..*` | —— | `0..1` | Diretor | "dirigido por" |
| Jogo | `0..*` | —— | `0..1` | Produtora | "produzido por" |
| Produto | `0..*` | —— | `0..*` | Localizacao | "localizado em" |
| Venda | `0..*` | —— | `1` | Produto | "refere" |
| Venda | `0..*` | —— | `0..1` | Promocao | "no âmbito de" |
| Venda | `0..*` | —— | `0..1` | ClienteEspecial | "feita por" |

> A associação Venda–ClienteEspecial é **navegável nos dois sentidos**: a `Venda` conhece o
> seu `cliente` e o `ClienteEspecial` mantém o `historicoCompras : List<Venda>` (`1` cliente
> para `0..*` vendas).
>
> `Utilizador` não tem associações — fica isolada no canto.

---

## Figura 3 — Fluxograma: autenticação e encriptação (R12)

1. **Início**
2. **Decisão:** existe password? → **Não** → *retornar falso* → **Fim**
3. *(Sim)* **E/S:** ler conteúdo de `pass.txt`
4. **Decisão:** comprimento < 44? *(44 = tamanho de um hash SHA-256 em Base64)*
   - **Sim** (texto simples):
     - **Decisão:** conteúdo == password?
       - **Sim:** *processo* encriptar (SHA-256→Base64) → *E/S* gravar hash em `pass.txt` → *retornar verdadeiro*
       - **Não:** *retornar falso*
   - **Não** (já encriptada):
     - *processo* calcular hash(password)
     - **Decisão:** hash == conteúdo? → **Sim** *retornar verdadeiro* / **Não** *retornar falso*
5. **Fim**

---

## Figura 4 — Fluxograma: registo de venda (R10, R13)

1. **Início**
2. **Decisão:** stock < quantidade? → **Sim** → *retornar falso* → **Fim**
3. *(Não)* **processo:** precoFinal ← precoVenda × quantidade
4. **Decisão:** existe promoção ativa? *(percorre a lista; usa a 1.ª ativa)*
   - **Sim:** *processo* precoFinal ← aplicarDesconto(precoFinal); guardar promoção ativa
   - **Não:** seguir
5. **processo:** stock ← stock − quantidade  →  *E/S* guardar produtos (`.dat`)
6. **processo:** criar `Venda` e adicionar à lista (guardar vendas `.dat`)
7. **Decisão:** cliente especial ≠ nulo? → **Sim** *processo* adicionar compra ao cliente + guardar clientes
8. **E/S:** escrever linha em `vendas.txt`
9. *retornar verdadeiro* → **Fim**

---

## Figura 5 — Fluxograma: estatísticas — jogos mais vendidos (R13)

1. **Início**
2. **processo:** contador ← mapa vazio `<Jogo, inteiro>`
3. **Decisão (ciclo externo):** há mais vendas?
   - **Sim:**
     - **Decisão (ciclo interno):** há mais jogos neste produto?
       - **Sim:** *processo* contador[jogo] ← contador[jogo] + 1 → volta ao ciclo interno
       - **Não:** volta ao ciclo externo (próxima venda)
   - **Não** (fim das vendas): seguir
4. **processo:** ordenar contador por valor (contagem) **decrescente**
5. *retornar* lista ordenada → **Fim**

**Caixas → setas (para desenhar):**

| ID | Forma | Texto |
|----|-------|-------|
| 1 | Terminador | Início |
| 2 | Processo | contador ← mapa vazio <Jogo, inteiro> |
| 3 | Decisão | Há mais vendas? *(ciclo externo)* |
| 4 | Processo | Obter próxima venda e o seu produto |
| 5 | Decisão | Há mais jogos no produto? *(ciclo interno)* |
| 6 | Processo | contador[jogo] ← contador[jogo] + 1 |
| 7 | Processo | Ordenar contador por contagem (decrescente) |
| F | Terminador | **Fim → devolve lista ordenada** |

**Setas:** 1→2 · 2→3 · 3 –Não→ 7 · 3 –Sim→ 4 · 4→5 · 5 –Sim→ 6 · **6→5** *(volta: próximo jogo)* ·
**5 –Não→ 3** *(volta: próxima venda)* · 7→F

> Equivalente em Java (Stream API): `flatMap` para obter os jogos de cada venda +
> `Collectors.groupingBy(jogo, counting())` + ordenação por valor decrescente.
