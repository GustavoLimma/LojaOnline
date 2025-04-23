package br.ufrn.lojaonline.controller;

import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class FinalizaCompraController {

    @RequestMapping(value = "/finalizar", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("carrinho") == null) {
            response.sendRedirect("produtos");
            return;
        }

        CarrinhoController.Carrinho carrinho = (CarrinhoController.Carrinho) session.getAttribute("carrinho");
        double total = 0.0;

        for (ListaProdutosController.Produto p : carrinho.getProdutos()) {
            ListaProdutosController.Produto produtoOriginal = ListaProdutosController.getProdutoById(p.getId());
            if (produtoOriginal != null && produtoOriginal.getQuantidade() > 0) {
                produtoOriginal.setQuantidade(produtoOriginal.getQuantidade() - 1);
                total += produtoOriginal.getPreco();
            }
        }

        session.removeAttribute("carrinho");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Compra Finalizada</title></head><body>");
        out.println("<h1>Compra Finalizada com Sucesso</h1>");
        out.println("<p>Total da compra: R$" + String.format("%.2f", total) + "</p>");
        out.println("<a href='produtos'>Voltar para produtos</a>");
        out.println("</body></html>");
    }
}
