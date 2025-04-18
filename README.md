
---

## ✅ LISTA COMPLETA - O QUE FAZER PARA REALIZAR O PROJETO

### 1. Estrutura Inicial do Projeto
- [ ] Criar um projeto Spring Boot com Spring Web
- [ ] Organizar as pastas:
```
src/
├── controller/
├── model/
├── service/
├── filter/
└── resources/templates/
```

---

### 2. Cadastro e Login de Clientes e Lojistas
- [ ] Criar modelo `Cliente` e `Lojista` com: nome, email, senha
- [ ] Criar `LoginController`
- [ ] Criar método `POST /login` para verificar:
    - Se é cliente ou lojista
    - Criar sessão com `session.setAttribute("usuario", ...)`
- [ ] Criar método `GET /logout`:
    - `session.invalidate()`
    - Redirecionar para `/login`

---

### 3. Criar Filtro de Autenticação
- [ ] Criar classe com `@WebFilter(urlPatterns = "/restrito/*")`
- [ ] Verificar se há sessão com atributo `usuario`
- [ ] Redirecionar para `/login` se não autenticado

---

### 4. Carrinho de Compras (com sessão)
- [ ] Criar classe `Carrinho` com `List<Produto>`
- [ ] Métodos: `addProduto(Produto p)`, `removeProduto(int id)`
- [ ] Salvar o carrinho na sessão: `session.setAttribute("carrinho", carrinho)`
- [ ] Criar `CarrinhoController`:
    - Comandos: `?comando=add&id=3`, `?comando=remove&id=3`
- [ ] Criar `VerCarrinhoController`:
    - Mostra produtos no carrinho
    - Redireciona para `/listarProdutos` se o carrinho não existir

---

### 5. Produtos
- [ ] Criar modelo `Produto` com: id, nome, descrição, preço, quantidade
- [ ] Criar `ProdutoService` com uma lista estática (simulando banco)
- [ ] Criar `ProdutoController` com:
    - `GET /listarProdutos` → mostra os produtos
    - `POST /cadastrarProduto` → para lojistas cadastrarem produtos
- [ ] Visualização do estoque para lojistas

---

### 6. Finalizar Compra
- [ ] Criar rota `/finalizarCompra`:
    - Somar os valores dos produtos do carrinho
    - Reduzir quantidade do estoque
    - Limpar carrinho da sessão

---

### 7. Páginas HTML (Templates simples)
- [ ] `login.html`
- [ ] `cadastro.html`
- [ ] `listarProdutos.html`
- [ ] `verCarrinho.html`
- [ ] `cadastrarProduto.html`

---

### 8. Testes e Demonstração
- [ ] Testar login/logout como cliente e lojista
- [ ] Testar adicionar/remover produtos do carrinho
- [ ] Testar redirecionamentos (com ou sem sessão)
- [ ] Definir tempo de sessão: `server.servlet.session.timeout=20m`
- [ ] Criar vídeo com a demo + subir projeto no GitHub

---
