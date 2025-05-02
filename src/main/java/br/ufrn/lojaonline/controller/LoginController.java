package br.ufrn.lojaonline.controller;

import br.ufrn.lojaonline.dao.ClienteDAO;
import br.ufrn.lojaonline.dao.LojistaDAO;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class LoginController {

    public static final ArrayList<Cliente> clientes = new ArrayList<>();
    public static final ArrayList<Lojista> lojistas = new ArrayList<>();

    public static class Cliente {
        private String nome;
        private String email;
        private String senha;

        public Cliente(String nome, String email, String senha) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
        }

        public String getNome() { return nome; }
        public String getEmail() { return email; }
        public String getSenha() { return senha; }
    }

    public static class Lojista {
        private String nome;
        private String email;
        private String senha;

        public Lojista(String nome, String email, String senha) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
        }

        public String getNome() { return nome; }
        public String getEmail() { return email; }
        public String getSenha() { return senha; }
    }

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final LojistaDAO lojistaDAO = new LojistaDAO();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("""
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <title>Login</title>
                </head>
                <body>
                <h1>Login</h1>
                <form action="login" method="POST">
                    <label for="email">Email:</label>
                    <input id="email" name="email" type="email" required><br>

                    <label for="senha">Senha:</label>
                    <input id="senha" name="senha" type="password" required><br>

                    <input type="submit" value="Entrar">
                </form>
                <br>
                <a href="cadastro">Cadastrar novo usuário</a>
                </body>
                </html>
                """);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20 * 60);

        try {
            Cliente cliente = clienteDAO.buscarPorEmailESenha(email, senha);
            if (cliente != null) {
                session.setAttribute("usuario", cliente);
                session.setAttribute("tipo", "cliente");
                response.sendRedirect("produtos");
                return;
            }

            Lojista lojista = lojistaDAO.buscarPorEmailESenha(email, senha);
            if (lojista != null) {
                session.setAttribute("usuario", lojista);
                session.setAttribute("tipo", "lojista");
                response.sendRedirect("estoque");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login");
            return;
        }

        response.sendRedirect("login");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login");
    }
}
