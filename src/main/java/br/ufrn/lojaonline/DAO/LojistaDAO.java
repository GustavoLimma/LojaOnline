package br.ufrn.lojaonline.dao;

import br.ufrn.lojaonline.controller.LoginController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LojistaDAO {

    public void inserir(LoginController.Lojista lojista) throws Exception {
        String sql = "INSERT INTO lojista (nome, email, senha) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lojista.getNome());
            stmt.setString(2, lojista.getEmail());
            stmt.setString(3, lojista.getSenha());
            stmt.executeUpdate();
        }
    }

    public LoginController.Lojista buscarPorEmailESenha(String email, String senha) throws Exception {
        String sql = "SELECT * FROM lojista WHERE email = ? AND senha = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LoginController.Lojista(rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
                }
            }
        }
        return null;
    }
}
