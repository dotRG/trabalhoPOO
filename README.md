# Sistema de Gestão de Loja de Jogos

Projeto desenvolvido em Java para a disciplina de Programação Orientada a Objetos.

## Estrutura

```
src/
  model/         -> Entidades (Jogo, Diretor, Produtora, Produto, Venda, ClienteEspecial, Promocao, Utilizador)
  persistence/   -> FileManager e TextFileManager
  controller/    -> LojaController (lógica de negócio)
  ui/            -> Menus (MenuPrincipal, MenuAdmin, MenuCliente, LoginManager)
  utils/         -> CryptoUtils (encriptação SHA-256)
  Main.java      -> Ponto de entrada
data/            -> Ficheiros de dados persistentes
docs/            -> Relatório e Manual do Utilizador
```

## Compilação

```bash
javac -d out -sourcepath src src/Main.java
```

## Execução

```bash
java -cp out Main
```

## Login Admin

Password por defeito: `admin123` (guardada em `data/pass.txt`, encriptada após o primeiro login)

## Funcionalidades

- **Admin**: CRUD de diretores, produtoras, jogos, produtos, clientes, promoções; registo de vendas; estatísticas; alteração de password.
- **Cliente**: Pesquisas por nome, diretor, estilo; produtos relacionados; exportação de resultados para ficheiro de texto.

## Persistência

- Ficheiros de objetos (`.dat`) para entidades principais.
- Ficheiros de texto (`.txt`) para vendas, password e exportações.
