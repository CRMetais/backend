package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.sptech.cr_metais.entity.PrecoProdutoTabela;
import school.sptech.cr_metais.entity.TipoTabela;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrecoProdutoTabelaRepository extends JpaRepository<PrecoProdutoTabela, Integer> {

    @Query(value = """
        SELECT *
        FROM preco_produto_tabela ppt
        WHERE (:tabelaPrecoId IS NULL OR ppt.fk_tabela_preco = :tabelaPrecoId)
          AND (:produtoId IS NULL OR ppt.fk_produto = :produtoId)
        """, nativeQuery = true)
    List<PrecoProdutoTabela> buscarPrecos(
        @Param("tabelaPrecoId") Long tabelaPrecoId,
        @Param("produtoId") Long produtoId
    );

    Optional<PrecoProdutoTabela> findFirstByProdutoIdProdutoAndTabelaPrecoTipoAndTabelaPrecoNomeTabelaAndTabelaPrecoAtivaTrueOrderByTabelaPrecoVersaoDesc(
            Integer idProduto,
            TipoTabela tipo,
            String nomeTabela
    );
}
