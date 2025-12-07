package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.TipoTabela;

import java.util.Optional;

@Repository
public interface PrecoProdutoTabelaRepository extends JpaRepository<PrecoProdutoTabela, Integer> {

    Optional<PrecoProdutoTabela> findFirstByProdutoIdProdutoAndTabelaPrecoTipoAndTabelaPrecoNomeTabelaAndTabelaPrecoAtivaTrueOrderByTabelaPrecoVersaoDesc(
            Integer idProduto,
            TipoTabela tipo,
            String nomeTabela
    );
}
