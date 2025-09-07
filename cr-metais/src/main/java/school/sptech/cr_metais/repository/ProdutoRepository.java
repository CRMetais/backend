package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cr_metais.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
