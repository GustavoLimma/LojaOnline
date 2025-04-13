package br.ufrn.lojaonline.repository;

import br.ufrn.lojaonline.model.Lojista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LojistaRepository extends JpaRepository<Lojista, Long> {
    Optional<Lojista> findByEmailAndSenha(String email, String senha);
}
