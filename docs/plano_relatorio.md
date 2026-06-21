# Plano do Relatório — "Loja de Jogos" (POO 2025/26)

Guião de escrita baseado no modelo do professor (`relatorioPOO.docx`).
**Limite: 10 páginas.** O enunciado obriga a incidir sobre os **algoritmos principais**
(com pseudocódigo ou fluxogramas) e a **documentar desvios/pressupostos**.

---

## Esqueleto (capítulos do modelo) + orçamento de páginas

| # | Secção | Conteúdo | Págs |
|---|--------|----------|------|
| — | **Capa** | ESTG · CTeSP-TPSI · POO 2025/26 · título "Loja de Jogos" · data · alunos | (não conta) |
| — | **Índice** | Automático | (não conta) |
| 1 | **Lista de figuras** | Só se houver figuras (ver lista abaixo) | ¼ |
| 2 | **Introdução** | Contexto (loja de jogos físicos), objetivo, tecnologias (Java, serialização, ficheiros de texto) | ½ |
| 3 | **Análise de requisitos** | Tabela R1–R13 agrupados + onde cada um é cumprido | 1–1½ |
| 4 | **Estrutura do sistema** | **Arquitetura em camadas** (ver secção dedicada abaixo) | 1½–2 |
| 5 | **Trabalho realizado** | O miolo (ver desdobramento abaixo) | 3½–4 |
| 6 | **Conclusões e trabalho futuro** | Resultados, dificuldades, melhorias futuras | ½–1 |
| 7 | **Referências** | Enunciado, docs Java | ¼ |
| 8 | **Bibliografia** | (se aplicável) | — |
| — | **Anexos** | Diagramas/fluxogramas grandes, manual do utilizador, excertos de código | — |

---

## Secção 4 — Estrutura do sistema (DECISÃO FIXADA)

**Título a usar:** *"Estrutura do sistema — Arquitetura em camadas"*

> Decisão: descrever como **arquitetura em camadas (layered / n-tier)**, NÃO como MVC.
> O rótulo "MVC" tinha sido adicionado por nós (não é imposição do professor) e seria
> difícil de defender no oral (não há View a observar o Model; `LojaController` é uma
> fachada de serviço e não um controller de MVC; o `model` é anémico).
> **Frase-tipo permitida:** "O nome do pacote `controller` e o papel de `LojaController`
> inspiram-se na ideia de coordenação do MVC, mas a organização real é por camadas."

**Fluxo de dependências (unidirecional):** `ui → controller → gestao → persistence`,
com `model` transversal a todas.

| Pacote | Camada | Papel |
|--------|--------|-------|
| `ui` | Apresentação | Menus de consola (lê input e imprime) |
| `controller` (`LojaController`) | Negócio / Serviço (fachada) | Regras: venda, estatísticas, pesquisas, capacidade |
| `gestao` (`Gere*`) | Serviço / Repositório | CRUD em memória + delega persistência |
| `persistence` (`FileManager<T>`, `TextFileManager`) | Acesso a dados | Serialização `.dat` / texto `.txt` |
| `model` | Domínio | Entidades (POJOs serializáveis) |
| `utils` (`Input`, `CryptoUtils`) | Transversal | Validação de input, SHA-256 |

⚠️ Reformular [docs/relatorio.md:18](relatorio.md) que ainda diz "padrão MVC simplificado".

---

## Secção 5 — Trabalho realizado (desdobramento)

- **5.1 Modelo de domínio** — relações: Produto 1..* Jogo · Jogo→Diretor/Produtora ·
  Venda→Produto/Cliente/Promoção · Localização.
- **5.2 Persistência** — `FileManager<T>` (`.dat`, objetos — R6/R7) e `TextFileManager`
  (`.txt`, vendas — R10); porquê os dois tipos.
- **5.3 Algoritmos principais** ⭐ *(secção obrigatória — pseudocódigo + fluxograma)*:
  1. **Autenticação + encriptação da password** (R12)
  2. **Registo de venda** — stock → promoção → baixa de stock → persistência dupla (R10/R13)
  3. **Estatísticas — mais vendidos** — agrupamento/contagem (R13)
- **5.4 Ponto de vista do utilizador** — menus admin vs cliente; descreve as pesquisas
  R8 (incl. "produtos comprados por clientes comuns") como *funcionalidade*; remete para
  o manual em anexo.
- **5.5 Ponto de vista do programador** — camadas, fachada `LojaController`,
  `FileManager<T>` genérico, validação centralizada em `Input`.
- **5.6 Decisões e pressupostos** *(o enunciado exige documentar desvios)*:
  - Promoção global "uma de cada vez" (a primeira ativa aplica-se a qualquer produto).
  - Password SHA-256 **sem salt** (âmbito académico).
  - Localização permite produtos que existem no programa mas não na loja (R5).
  - **Identidade por chave de negócio** — `equals/hashCode` em `Produto` (formato+plataforma+
    nomes dos jogos) e `ClienteEspecial` (nome+email); resolveu o bug de R8 em que a
    comparação por referência entre `produtos.dat` e `vendas.dat` devolvia sempre vazio.

> Nota: das pesquisas, só a R8 "produtos por clientes comuns" NÃO vai como algoritmo
> destacado na 5.3 — fica descrita na 5.4 e a sua correção entra na 5.6.

---

## Figuras previstas (para a Lista de figuras)

- Fig.1 — Diagrama de pacotes / camadas
- Fig.2 — Diagrama de classes do domínio
- Fig.3 — Fluxograma: autenticação + encriptação
- Fig.4 — Fluxograma: registo de venda
- Fig.5 — Fluxograma: estatísticas (mais vendidos)

---

## Pendente antes de escrever

1. **Dados da capa** — nome(s) e número(s) (individual ou grupo até 3) + data de entrega.
2. **Formato final** — preencher o `relatorioPOO.docx` **ou** Markdown → Word. *(em standby)*
3. **Figuras** — gerar em ASCII/descrição ou fazer em diagrams.net/Word.

## Material reutilizável já existente

- [docs/relatorio.md](relatorio.md) — rascunho (atualizar 4.3 e a nota de "unicidade do objeto",
  que descrevem a versão antiga/com bug, e o "MVC simplificado").
- [docs/manual_utilizador.md](manual_utilizador.md) — manual do utilizador (entregável à parte / anexo).
