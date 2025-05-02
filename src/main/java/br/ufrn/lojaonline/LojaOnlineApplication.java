package br.ufrn.lojaonline;

import br.ufrn.lojaonline.dao.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class LojaOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaOnlineApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase() {
		return args -> {

			DatabaseConnector.createTables();

			try (Connection conn = br.ufrn.lojaonline.dao.ConnectionFactory.getConnection()) {

				PreparedStatement check = conn.prepareStatement("SELECT id FROM cliente WHERE email = ?");
				check.setString(1, "jp2017@uol.com.br");
				ResultSet rs = check.executeQuery();

				if (!rs.next()) {
					PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO cliente (nome, email, senha) VALUES (?, ?, ?)");
					stmt1.setString(1, "João Pedro");
					stmt1.setString(2, "jp2017@uol.com.br");
					stmt1.setString(3, "12345jaum");
					stmt1.executeUpdate();

					stmt1.setString(1, "Amara Silva");
					stmt1.setString(2, "amarasil@bol.com.br");
					stmt1.setString(3, "amara82");
					stmt1.executeUpdate();

					stmt1.setString(1, "Maria Pereira");
					stmt1.setString(2, "mariape@terra.com.br");
					stmt1.setString(3, "145aektm");
					stmt1.executeUpdate();

					PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO lojista (nome, email, senha,tipo) VALUES (?, ?, ?, ?)");
					stmt2.setString(1, "Taniro Rodrigues");
					stmt2.setString(2, "tanirocr@gmail.com");
					stmt2.setString(3, "123456abc");
					stmt2.setBoolean(4, true);
					stmt2.executeUpdate();

					stmt2.setString(1, "Lorena Silva");
					stmt2.setString(2, "lore_sil@yahoo.com.br");
					stmt2.setString(3, "12uhuuu@");
					stmt2.setBoolean(4, true);
					stmt2.executeUpdate();

					System.out.println("Dados inseridos com sucesso.");
				} else {
					System.out.println("Dados já existem. Ignorando inserção.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}

