package br.ufrn.lojaonline.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/lojaonline";
    private static final String USER = "root";
    private static final String PASSWORD = "6901";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTables() {
        String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuario (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(100)," +
                "email VARCHAR(100)," +
                "senha VARCHAR(100))";

        String sqlCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(100)," +
                "email VARCHAR(100)," +
                "senha VARCHAR(100))";

        String sqlLojista = "CREATE TABLE IF NOT EXISTS lojista (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(100)," +
                "email VARCHAR(100)," +
                "senha VARCHAR(100),"+
                "tipo BOOLEAN)";

        String sqlProduto = "CREATE TABLE IF NOT EXISTS produto (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(100) NOT NULL," +
                "descricao VARCHAR(255)," +
                "preco DECIMAL(10, 2) NOT NULL," +
                "estoque INT NOT NULL)";


        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sqlUsuario);
            statement.executeUpdate(sqlCliente);
            statement.executeUpdate(sqlLojista);
            statement.executeUpdate(sqlProduto);
            System.out.println("Tabelas criadas com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar as tabelas: " + e.getMessage());
        }
    }
}
