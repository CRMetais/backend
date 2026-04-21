package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.cr_metais.entity.TabelaPreco;

import java.util.List;
import java.util.Optional;

public interface TabelaPrecoRepository extends JpaRepository<TabelaPreco, Integer>{

	@Query(value = """
			SELECT
				f.id_fornecedor,
				f.nome,
				f.apelido,
				f.documento,
				f.telefone,
				f.fk_tabela_preco,
				tp.nome_tabela
			FROM fornecedor f
			LEFT JOIN tabela_preco tp
				ON tp.id_tabela = f.fk_tabela_preco
			""", nativeQuery = true)
	List<Object[]> listarFornecedoresComTabelaPreco();


	@Query(value = """
    SELECT
        p.nome,
        tp.data_inicio_validade,
        tp.versao,
        ppt.preco_produto
    FROM produto p
    JOIN preco_produto_tabela ppt ON p.id_produto = ppt.fk_produto
    JOIN tabela_preco tp ON ppt.fk_tabela_preco = tp.id_tabela
    WHERE tp.nome_tabela = :nome
    ORDER BY tp.data_inicio_validade DESC
""", nativeQuery = true)
	List<Object[]> buscarPorNomeTabela(@Param("nome") String nome);

	Optional<TabelaPreco> findByNomeTabela(String nomeTabela);

	Optional<TabelaPreco> findByNomeTabelaAndDataFimValidadeIsNull(String nomeTabela);

	@Query("SELECT t FROM TabelaPreco t WHERE t.tipo = 'V'")
	List<TabelaPreco> findAllVenda();


}

