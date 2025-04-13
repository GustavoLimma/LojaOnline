package br.ufrn.lojaonline.repository;

import br.ufrn.lojaonline.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmailAndSenha(String email, String senha);
}
