package br.ufrn.lojaonline.repository;

import br.ufrn.lojaonline.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
