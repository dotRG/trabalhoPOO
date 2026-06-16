# Manual do Utilizador - Sistema de Gestão de Loja de Jogos

## 1. Introdução

Este manual descreve como utilizar o sistema de gestão de loja de jogos desenvolvido em Java. O sistema permite gerir produtos (CDs, DVDs, Blu-rays, cartuchos), jogos, diretores, produtoras, vendas, clientes especiais e promoções.

## 2. Instalação e Execução

### Requisitos
- Java JDK 17 ou superior

### Execução
1. Abra um terminal na pasta do projeto.
2. Compile o projeto:
   ```bash
   javac -d out -sourcepath src src/Main.java
   ```
3. Execute o programa:
   ```bash
   java -cp out Main
   ```

## 3. Ecrã Inicial

Ao iniciar o programa, é apresentado o menu principal:

```
===== LOJA DE JOGOS =====
1. Zona Administrador
2. Zona Cliente
0. Sair
```

## 4. Zona Administrador

### Login
A password pré-definida é `admin123`. Após o primeiro login, a password é encriptada automaticamente. Pode alterá-la no menu de administrador.

### Funcionalidades
- **Gerir Diretores**: adicionar, listar, editar e remover diretores.
- **Gerir Produtoras**: adicionar, listar, editar e remover produtoras.
- **Gerir Jogos**: adicionar, listar, editar e remover jogos. Cada jogo está associado a um diretor, uma produtora e um estilo.
- **Gerir Produtos**: adicionar, listar, editar e remover produtos. Um produto contém um ou mais jogos, possui formato, plataforma, localização, preços e stock.
- **Gerir Clientes Especiais**: adicionar, listar, editar e remover clientes identificados.
- **Gerir Promoções**: adicionar, listar, editar e remover promoções com percentagem de desconto e datas de vigência.
- **Registar Venda**: selecionar produto, quantidade, empregado e cliente especial (opcional). O stock é atualizado automaticamente e o desconto de promoção ativa é aplicado.
- **Estatísticas**: visualizar jogos mais vendidos, estilos mais procurados, diretores e produtoras mais vendidos.
- **Alterar Password**: modificar a password de administrador.

## 5. Zona Cliente

Funcionalidades disponíveis sem necessidade de login:
- **Pesquisar Jogo por Nome**: procura parcial pelo nome do jogo.
- **Pesquisar Jogo por Diretor**: lista jogos de um determinado diretor.
- **Pesquisar Jogo por Estilo**: lista jogos de um determinado estilo (aventura, fantasia, etc.).
- **Ver Produtos que contêm um Jogo**: mostra todos os produtos que incluem o jogo selecionado.
- **Ver outros Jogos do mesmo Diretor**: dado um jogo, lista os restantes do mesmo diretor.
- **Ver outros Jogos do mesmo Estilo**: dado um jogo, lista os restantes do mesmo estilo.
- **Ver Produtos comprados pelos mesmos Clientes**: dado um produto, lista outros produtos adquiridos pelos mesmos clientes especiais.
- **Ver Diretores de uma Produtora**: lista todos os diretores cujos jogos pertencem a uma dada produtora.

### Exportação
Após cada pesquisa, o sistema pergunta se pretende exportar os resultados para um ficheiro de texto na pasta `data/`.

## 6. Ficheiros de Dados

Todos os dados são guardados na pasta `data/`:
- `diretores.dat`, `produtoras.dat`, `jogos.dat`, `produtos.dat`, `vendas.dat`, `clientes.dat`, `promocoes.dat` - ficheiros binários de objetos.
- `vendas.txt` - registo de vendas em formato de texto.
- `pass.txt` - password do administrador (encriptada após primeiro login).

## 7. Notas Importantes
- Não elimine a pasta `data/` pois contém toda a informação persistente.
- A password apenas pode ser alterada na zona de administrador.
- As promoções ativas são aplicadas automaticamente no registo de vendas.
