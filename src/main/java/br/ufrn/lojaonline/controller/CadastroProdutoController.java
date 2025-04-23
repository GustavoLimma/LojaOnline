package br.ufrn.lojaonline.controller;

import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CadastroProdutoController {

    private static int proximoId = 4;

    @RequestMapping(value = "/cadastrarproduto", method = RequestMethod.POST)
    public void cadastrarProduto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        ListaProdutosController.produtos.add(
                new ListaProdutosController.Produto(proximoId++, nome, descricao, preco, quantidade)
        );

        response.sendRedirect("estoque");
    }

    @RequestMapping(value = "/estoque", method = RequestMethod.GET)
    public void mostrarEstoque(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Estoque</title></head><body>");
        out.println("<h1>Estoque Atual</h1>");

        for (ListaProdutosController.Produto p : ListaProdutosController.produtos) {
            out.println("<p>" + p.getNome() + " - Estoque: " + p.getQuantidade() + "</p>");
        }

        out.println("<h2>Cadastrar Novo Produto</h2>");
        out.println("<form method='POST' action='cadastrarproduto'>");
        out.println("Nome: <input name='nome'/><br/>");
        out.println("Descrição: <input name='descricao'/><br/>");
        out.println("Preço: <input name='preco' type='number' step='0.01'/><br/>");
        out.println("Quantidade: <input name='quantidade' type='number'/><br/>");
        out.println("<input type='submit' value='Cadastrar'/>");
        out.println("</form>");

        out.println("<br><a href='produtos'>Voltar para Produtos</a>");
        out.println("</body></html>");
    }
}
