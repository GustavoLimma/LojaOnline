package br.ufrn.lojaonline.controller;

import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
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

        public String getEmail() { return email; }
        public String getSenha() { return senha; }
    }

    static {
        clientes.add(new Cliente("João Pedro", "jp2017@uol.com.br", "12345jaum"));
        clientes.add(new Cliente("Amara Silva", "amarasil@bol.com.br", "amara82"));
        clientes.add(new Cliente("Maria Pereira", "mariape@terra.com.br", "145aektm"));

        lojistas.add(new Lojista("Taniro Rodrigues", "tanirocr@gmail.com", "123456abc"));
        lojistas.add(new Lojista("Lorena Silva", "lore_sil@yahoo.com.br", "12uhuuu@"));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(20 * 60);

        for (Cliente c : clientes) {
            if (c.getEmail().equals(email) && c.getSenha().equals(senha)) {
                session.setAttribute("usuario", c);
                session.setAttribute("tipo", "cliente");
                response.sendRedirect("produtos");
                return;
            }
        }

        for (Lojista l : lojistas) {
            if (l.getEmail().equals(email) && l.getSenha().equals(senha)) {
                session.setAttribute("usuario", l);
                session.setAttribute("tipo", "lojista");
                response.sendRedirect("estoque");
                return;
            }
        }

        response.sendRedirect("login.html");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.html");
    }
}
