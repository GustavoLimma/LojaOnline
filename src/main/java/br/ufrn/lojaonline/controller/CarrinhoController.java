package br.ufrn.lojaonline.controller;

import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class CarrinhoController {

    public static class Carrinho {
        private final ArrayList<ListaProdutosController.Produto> produtos = new ArrayList<>();

        public void addProduto(ListaProdutosController.Produto produto) {
            produtos.add(produto);
        }

        public void removeProduto(int id) {
            produtos.removeIf(p -> p.getId() == id);
        }

        public ArrayList<ListaProdutosController.Produto> getProdutos() {
            return produtos;
        }
    }

    @RequestMapping(value = "/carrinho", method = RequestMethod.GET)
    public void carrinho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String comando = request.getParameter("comando");

        HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new Carrinho();
            session.setAttribute("carrinho", carrinho);
        }

        ListaProdutosController.Produto produto = ListaProdutosController.getProdutoById(id);

        if (produto != null) {
            if ("add".equals(comando)) {
                carrinho.addProduto(produto);
            } else if ("remove".equals(comando)) {
                carrinho.removeProduto(id);
            }
        }

        response.sendRedirect("produtos");
    }

    @RequestMapping(value = "/vercarrinho", method = RequestMethod.GET)
    public void verCarrinho(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("carrinho") == null) {
            response.sendRedirect("produtos");
            return;
        }

        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Carrinho</title></head><body>");
        out.println("<h1>Seu Carrinho</h1>");

        for (ListaProdutosController.Produto p : carrinho.getProdutos()) {
            out.println("<p>" + p.getNome() + " - R$" + p.getPreco());
            out.println(" <a href='carrinho?id=" + p.getId() + "&comando=add'>[ + ]</a>");
            out.println(" <a href='carrinho?id=" + p.getId() + "&comando=remove'>[ - ]</a></p>");
        }

        out.println("<a href='finalizar'>Finalizar Compra</a><br>");
        out.println("<a href='produtos'>Voltar</a>");
        out.println("</body></html>");
    }
}
