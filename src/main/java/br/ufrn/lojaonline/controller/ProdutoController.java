package br.ufrn.lojaonline.controller;

import br.ufrn.lojaonline.dao.ProdutoDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProdutoController {

    public static class Produto {
        private int id;
        private String nome;
        private String descricao;
        private double preco;
        private int quantidade;

        public Produto(int id, String nome, String descricao,double preco, int quantidade) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
            this.quantidade = quantidade;
            this.descricao = descricao;
        }

        public int getId() { return id; }
        public String getNome() { return nome; }
        public double getPreco() { return preco; }
        public int getQuantidade() { return quantidade; }
        public String getDescricao() { return descricao; }
    }

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public void listarProdutos(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Produto> produtos = produtoDAO.listarProdutos();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Produtos disponíveis</h2><ul>");

        for (Produto p : produtos) {
            out.println("<li>" + p.getNome() + " - descrição: " + p.getDescricao() + " - R$" + p.getPreco() + " - Qtd." + p.getQuantidade() +
                    " <form action='/carrinho' method='post' style='display:inline'>" +
                    "<input type='hidden' name='id' value='" + p.getId() + "'>" +
                    "<input type='hidden' name='nome' value='" + p.getNome() + "'>" +
                    "<input type='hidden' name='descricao' value='" + p.getDescricao() + "'>" +
                    "<input type='hidden' name='preco' value='" + p.getPreco() + "'>" +
                    " Quantidade: <input type='number' name='quantidade' value='1' min='1' max='" + p.getQuantidade() + "'>" +
                    "<input type='submit' value='Adicionar ao carrinho'>" +
                    "</form></li>");
        }

        out.println("</ul><a href='/vercarrinho'>Ver carrinho</a><br>");
        out.println("<a href='/logout'>Sair</a></body></html>");
    }

    @RequestMapping(value = "/estoque", method = RequestMethod.GET)
    public void estoque(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Produto> produtos = produtoDAO.listarProdutos();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Estoque de Produtos</h2><ul>");

        for (Produto p : produtos) {
            out.println("<li>" + p.getNome() + " - R$" + p.getPreco() + " - Qtd." + p.getQuantidade() + "</li>");
        }

        out.println("</ul><a href='/cadastrarproduto'>Cadastrar novo produto</a><br>");
        out.println("<a href='/logout'>Sair</a></body></html>");
    }

    @RequestMapping(value = "/cadastrarproduto", method = RequestMethod.GET)
    public void cadastrarProduto(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Cadastrar Produto</h2>");
        out.println("<form method='post' action='/cadastrarproduto'>");
        out.println("Nome: <input type='text' name='nome'><br>");
        out.println("Descrição: <input type='text' name='descricao'><br>");
        out.println("Preço: <input type='text' name='preco'><br>");
        out.println("Estoque: <input type='number' name='estoque'><br>");
        out.println("<input type='submit' value='Cadastrar'>");
        out.println("</form><a href='/estoque'>Voltar para o estoque</a></body></html>");
    }

    @RequestMapping(value = "/cadastrarproduto", method = RequestMethod.POST)
    public void cadastrarProdutoPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        double preco = Double.parseDouble(req.getParameter("preco"));
        int estoque = Integer.parseInt(req.getParameter("estoque"));

        produtoDAO.salvarProduto(nome, descricao, preco, estoque);

        resp.sendRedirect("/estoque");
    }

    @RequestMapping(value = "/carrinho", method = RequestMethod.POST)
    public void adicionarCarrinho(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        double preco = Double.parseDouble(req.getParameter("preco"));
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));

        Produto produto = new Produto(id, nome, descricao, preco, quantidade);
        HttpSession session = req.getSession();
        List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new ArrayList<>();
        }

        carrinho.add(produto);
        session.setAttribute("carrinho", carrinho);

        resp.sendRedirect("/produtos");
    }

    @RequestMapping(value = "/vercarrinho", method = RequestMethod.GET)
    public void verCarrinho(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Seu carrinho</h2>");

        if (carrinho == null || carrinho.isEmpty()) {
            out.println("<p>Carrinho vazio.</p>");
        } else {
            for (int i = 0; i < carrinho.size(); i++) {
                Produto p = carrinho.get(i);
                out.println("<p>" + p.getNome() + " - descrição: " + p.getDescricao() + " - R$" + p.getPreco() + " - Quantidade: " + p.getQuantidade() +
                        " <form method='post' action='/removeritem' style='display:inline'>" +
                        "<input type='hidden' name='index' value='" + i + "'>" +
                        "<input type='submit' value='Remover'>" +
                        "</form></p>");
            }

            out.println("<form method='post' action='/finalizarcompra'>");
            out.println("<input type='submit' value='Finalizar compra'>");
            out.println("</form>");
        }

        out.println("<a href='/produtos'>Voltar</a></body></html>");
    }

    @RequestMapping(value = "/removeritem", method = RequestMethod.POST)
    public void removerItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int index = Integer.parseInt(req.getParameter("index"));
        HttpSession session = req.getSession();
        List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");

        if (carrinho != null && index >= 0 && index < carrinho.size()) {
            carrinho.remove(index);
        }

        resp.sendRedirect("/vercarrinho");
    }

    @RequestMapping(value = "/finalizarcompra", method = RequestMethod.POST)
    public void finalizarCompra(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        List<Produto> carrinho = (List<Produto>) session.getAttribute("carrinho");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h2>Compra Finalizada</h2>");

        if (carrinho == null || carrinho.isEmpty()) {
            out.println("<p>Seu carrinho está vazio.</p>");
        } else {
            double total = 0.0;

            out.println("<ul>");
            for (Produto p : carrinho) {
                double subtotal = p.getPreco() * p.getQuantidade();
                total += subtotal;

                out.println("<li>" + p.getNome() + " - R$" + p.getPreco() +
                        " x " + p.getQuantidade() + " = R$" + String.format("%.2f", subtotal) + "</li>");
            }
            out.println("</ul>");

            out.println("<p><strong>Total da compra: R$" + String.format("%.2f", total) + "</strong></p>");
        }

        out.println("<a href='/produtos'>Voltar para produtos</a>");
        out.println("</body></html>");

        session.removeAttribute("carrinho");
    }
}
