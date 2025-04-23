package br.ufrn.lojaonline.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class CadastroController {

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if ("cliente".equals(tipo)) {
            LoginController.clientes.add(new LoginController.Cliente(nome, email, senha));
        } else if ("lojista".equals(tipo)) {
            LoginController.lojistas.add(new LoginController.Lojista(nome, email, senha));
        }

        response.sendRedirect("login.html");
    }
}

