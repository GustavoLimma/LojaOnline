wpackage br.ufrn.lojaonline.controller;

import br.ufrn.lojaonline.dao.ClienteDAO;
import br.ufrn.lojaonline.dao.LojistaDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class CadastroController {

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final LojistaDAO lojistaDAO = new LojistaDAO();

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("""
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <title>Cadastro</title>
                </head>
                <body>
                <h1>Cadastro</h1>
                <form action="cadastro" method="POST">
                    <label for="nome">Nome:</label>
                    <input id="nome" name="nome" required><br>

                    <label for="email">Email:</label>
                    <input id="email" name="email" type="email" required><br>

                    <label for="senha">Senha:</label>
                    <input id="senha" name="senha" type="password" required><br>

                    <label for="tipo">Tipo:</label>
                    <select id="tipo" name="tipo">
                        <option value="cliente">Cliente</option>
                        <option value="lojista">Lojista</option>
                    </select><br>

                    <input type="submit" value="Cadastrar">
                </form>
                </body>
                </html>
                """);
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        if ("cliente".equals(tipo)) {
            try {
                clienteDAO.inserir(new LoginController.Cliente(nome, email, senha));
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("erro.html");
                return;
            }
        } else if ("lojista".equals(tipo)) {
            try {
                lojistaDAO.inserir(new LoginController.Lojista(nome, email, senha));
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("erro.html");
                return;
            }
        }

        response.sendRedirect("login");
    }
}
