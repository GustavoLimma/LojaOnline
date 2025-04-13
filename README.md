**status geral do projeto** com base em **tudo já foi feito até agora** comparando com os **requisitos do trabalho** da faculdade.

---

## ✅ **O QUE JÁ FOI FEITO ATÉ AGORA**

| Item | Status | Observação |
|------|--------|------------|
| Projeto Spring Boot com Maven | ✅ | Estrutura criada com sucesso |
| Classe principal `LojaOnlineApplication` | ✅ | Compilando e rodando |
| Configuração do banco MySQL | ✅ | Banco criado (`lojaonline`) e configurado |
| Dependência MySQL no `pom.xml` | ✅ | Adicionada e sincronizada com o Maven |
| Entidades `Produto`, `Cliente`, `Lojista`, `Carrinho` | ✅ | Prontas e organizadas no pacote `model` |
| Repositórios `ProdutoRepository`, `ClienteRepository`, `LojistaRepository` | ✅ | Criados e com métodos de busca por email/senha |
| `application.properties` configurado | ✅ | Com dados do MySQL e Hibernate |
| Teste da aplicação inicial com MySQL | ✅ | Tabelas estão sendo criadas automaticamente |

---

## ⏳ **ONDE VOCÊ ESTÁ AGORA:**

📌 **Tudo do back-end básico (entidades + banco + conexão)** está pronto!  
➡️ Agora você pode começar as partes funcionais, como **login, listagem de produtos, carrinho**, etc.

---

## 🔧 **PRÓXIMOS PASSOS (o que ainda falta):**

### 🟡 **Autenticação e Sessão**
- [ ] Criar `LoginController` com autenticação de cliente ou lojista
- [ ] Salvar o tipo de usuário na `HttpSession`
- [ ] Criar `LogoutController` para destruir a sessão

---

### 🟡 **Carrinho e Produtos**
- [ ] Criar `ProdutoController` com listagem dos produtos
- [ ] Criar `CarrinhoController` com métodos `add`, `remove`, `ver`, `finalizar`
- [ ] Salvar o carrinho em `HttpSession` com timeout de 20min
- [ ] Finalizar compra e atualizar estoque

---

### 🟡 **Área do Lojista**
- [ ] Tela e controller para cadastrar novos produtos
- [ ] Tela para o lojista ver a lista de produtos com estoque

---

### 🟡 **Frontend / Views**
- [ ] Tela de login
- [ ] Tela de cadastro de cliente
- [ ] Tela de produtos (cliente)
- [ ] Tela de carrinho
- [ ] Tela de produtos do lojista
- [ ] Tela de cadastro de produto

---

### 🟡 **Entrega**
- [ ] Testar todas as funcionalidades
- [ ] Criar vídeo demonstrando cada caso de uso
- [ ] Subir projeto no GitHub e enviar pelo SIGAA

---

## 🧭 **Sugestão imediata:**
Vamos partir pro **LoginController**, com autenticação básica usando `ClienteRepository` e `LojistaRepository`.

Posso te mandar agora o código pronto e comentado, quer?
