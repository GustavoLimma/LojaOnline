package br.ufrn.lojaonline.dao;

import br.ufrn.lojaonline.controller.LoginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

        public void inserir(LoginController.Cliente cliente) throws Exception {
            String sql = "INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)";
            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getSenha());
                stmt.executeUpdate();
            }
        }

    public LoginController.Cliente buscarPorEmailESenha(String email, String senha) throws Exception {
        String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LoginController.Cliente(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                }
            }
        }
        return null;
    }
}
