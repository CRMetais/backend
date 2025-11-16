package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;

@Repository
public interface PrecoProdutoTabelaRepository extends JpaRepository<PrecoProdutoTabela, Integer> {
}
