# Relatório - Sistema de Gestão de Loja de Jogos

## 1. Introdução

Este relatório descreve o projeto desenvolvido no âmbito da disciplina de Programação Orientada a Objetos. O objetivo consiste na criação de uma aplicação Java para gestão de uma loja de jogos físicos, suportando operações de administrador e cliente, persistência de dados em ficheiros e pesquisas avançadas.

## 2. Análise de Requisitos

Foram identificados 13 requisitos funcionais (R1 a R13) agrupados em:
- Gestão de entidades: jogos, diretores, produtoras, produtos, clientes e promoções.
- Pesquisas: por nome, diretor, estilo e produtora.
- Vendas e stock: registo de vendas, atualização de stock e aplicação de promoções.
- Segurança: autenticação com password pré-definida e encriptação após primeiro login.
- Estatísticas: identificação de jogos, estilos, diretores e produtoras mais vendidos.

## 3. Modelo de Classes

O sistema foi estruturado segundo o padrão MVC simplificado:
- **Model** (`src/model/`): entidades de domínio (`Jogo`, `Diretor`, `Produtora`, `Produto`, `Venda`, `ClienteEspecial`, `Promocao`, `Utilizador`).
- **Persistence** (`src/persistence/`): classes `FileManager<T>` (serialização de objetos) e `TextFileManager` (ficheiros de texto).
- **Controller** (`src/controller/`): `LojaController` centraliza a lógica de negócio, CRUD, pesquisas e estatísticas.
- **UI** (`src/ui/`): menus de consola (`MenuPrincipal`, `MenuAdmin`, `MenuCliente`, `LoginManager`).
- **Utils** (`src/utils/`): `CryptoUtils` para encriptação SHA-256 da password.

## 4. Algoritmos Principais

### 4.1 Autenticação e Encriptação de Password

**Pseudocódigo:**
```
FUNCAO autenticarAdmin(password)
    conteudo = lerFicheiro(pass.txt)
    SE conteudo.length < 44 ENTAO
        // Ainda não encriptada
        SE conteudo == password ENTAO
            hash = SHA256(password)
            escreverFicheiro(pass.txt, hash)
            RETORNAR verdadeiro
        SENAO
            RETORNAR falso
    SENAO
        hashArmazenado = conteudo
        hashIntroduzida = SHA256(password)
        RETORNAR hashArmazenado == hashIntroduzida
    FIM SE
FIM FUNCAO
```

**Fluxograma (resumo):**
```
Início
  |
  v
Ler pass.txt
  |
  v
Tamanho < 44? --Sim--> Comparar texto plano
  |                      |
  Não                    v
  |                 Encriptar e guardar
  v                      |
Comparar hashes <--------+
  |
  v
Retornar resultado
```

### 4.2 Registo de Venda com Stock e Promoção

**Pseudocódigo:**
```
FUNCAO registarVenda(empregado, produto, quantidade, cliente)
    SE produto.stock < quantidade ENTAO
        RETORNAR falso
    FIM SE

    precoFinal = produto.precoVenda * quantidade
    promocaoAtiva = NULO

    PARA CADA promocao EM listaPromocoes FACA
        SE promocao.estaAtiva() ENTAO
            promocaoAtiva = promocao
            precoFinal = promocao.aplicarDesconto(precoFinal)
            PARAR
        FIM SE
    FIM PARA

    produto.stock = produto.stock - quantidade
    venda = NOVA Venda(empregado, produto, quantidade, precoFinal, AGORA, promocaoAtiva, cliente)
    adicionarVenda(venda)

    SE cliente != NULO ENTAO
        cliente.adicionarCompra(venda)
    FIM SE

    escreverLinhaEmFicheiroTexto(venda.toString())
    guardarDados()
    RETORNAR verdadeiro
FIM FUNCAO
```

### 4.3 Pesquisa de Produtos por Clientes Comuns

**Pseudocódigo:**
```
FUNCAO produtosCompradosPorClientesQueCompraram(produto)
    compradores = CONJUNTO_VAZIO

    PARA CADA venda EM listaVendas FACA
        SE venda.produto == produto E venda.cliente != NULO ENTAO
            compradores.adicionar(venda.cliente)
        FIM SE
    FIM PARA

    resultados = LISTA_VAZIA
    PARA CADA venda EM listaVendas FACA
        SE compradores.contem(venda.cliente) ENTAO
            resultados.adicionar(venda.produto)
        FIM SE
    FIM PARA

    RETORNAR resultados.distintos()
FIM FUNCAO
```

### 4.4 Estatísticas: Jogos Mais Vendidos

**Pseudocódigo:**
```
FUNCAO jogosMaisVendidos()
    contador = MAPA_VAZIO<Jogo, Inteiro>

    PARA CADA venda EM listaVendas FACA
        PARA CADA jogo EM venda.produto.jogos FACA
            contador[jogo] = contador[jogo] + 1
        FIM PARA
    FIM PARA

    ordenado = contador.ordenarPorValor(DESCENDENTE)
    RETORNAR ordenado
FIM FUNCAO
```

## 5. Persistência

O sistema utiliza dois tipos de persistência:
- **Ficheiros binários de objetos** (`.dat`): armazenam diretores, produtoras, jogos, produtos, vendas, clientes e promoções via `ObjectOutputStream` / `ObjectInputStream`. A lista completa é lida no arranque e escrita após cada modificação.
- **Ficheiros de texto** (`.txt`): registam vendas em formato legível e exportam resultados de pesquisas do cliente.

## 6. Interface do Utilizador

A interface é textual por consola, organizada em menus hierárquicos. Existe separação clara entre a zona de administrador (requer password) e a zona de cliente (livre acesso).

## 7. Decisões de Implementação

- A classe `FileManager<T>` é genérica, permitindo reutilização para qualquer tipo serializável.
- A promoção ativa aplicada numa venda é a primeira encontrada na lista que esteja no intervalo de datas válido. O sistema assume uma promoção de cada vez para simplificação, conforme solicitado.
- A localização de um produto pode ser uma área ou prateleira, representada livremente por texto (string), permitindo produtos sem existência física na loja.
- As versões diferentes do mesmo jogo são consideradas jogos distintos, garantido pela unicidade do objeto e não por nome.

## 8. Conclusão

O projeto cumpre todos os requisitos enunciados, oferecendo uma arquitetura modular, persistência de dados, segurança básica na autenticação e funcionalidades de pesquisa e estatísticas. A separação em camadas facilita a manutenção e futuras extensões.
