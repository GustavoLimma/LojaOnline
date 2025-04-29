package br.ufrn.lojaonline.controller;

import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class ListaProdutosController {

    public static final ArrayList<Produto> produtos = new ArrayList<>();

    public static class Produto {
        private int id;
        private String nome;
        private String descricao;
        private double preco;
        private int quantidade;

        public Produto(int id, String nome, String descricao, double preco, int quantidade) {
            this.id = id;
            this.nome = nome;
            this.descricao = descricao;
            this.preco = preco;
            this.quantidade = quantidade;
        }

        public Produto() {}

        public int getId() { return id; }
        public String getNome() { return nome; }
        public double getPreco() { return preco; }
        public int getQuantidade() { return quantidade; }

        public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
        public void setNome(String nome) { this.nome = nome; }
        public void setId(int id) { this.id = id; }
        public void setPreco(double preco) { this.preco = preco; }
    }

    public static Produto getProdutoById(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    static {
        produtos.add(new Produto(1, "Mesa", "Uma mesa de computador.", 500.0, 10));
        produtos.add(new Produto(2, "Lápis", "Lápis B2 grafite.", 2.0, 50));
        produtos.add(new Produto(3, "Computador", "Computador I5 16Gb de RAM.", 1500.0, 2));
    }

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Produtos</title></head><body>");
        out.println("<h1>Lista de Produtos</h1>");

        for (Produto p : produtos) {
            out.println("<p>");
            out.println(p.getNome() + " - R$ " + p.getPreco());
            out.println(" <a href='carrinho?id=" + p.getId() + "&comando=add'>Adicionar</a>");
            out.println("</p>");
        }

        out.println("<a href='vercarrinho'>Ver Carrinho</a><br><br>");
        out.println("<a href='logout'>Sair</a>");
        out.println("</body></html>");
    }
}
