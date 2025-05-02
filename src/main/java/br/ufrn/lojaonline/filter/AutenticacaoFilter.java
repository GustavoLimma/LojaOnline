package br.ufrn.lojaonline.filter;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter(urlPatterns = {"/produtos", "/carrinho", "/vercarrinho", "/finalizar", "/estoque", "/cadastrarproduto"})
public class AutenticacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login");
            return;
        }

        chain.doFilter(req, res);
    }
}
