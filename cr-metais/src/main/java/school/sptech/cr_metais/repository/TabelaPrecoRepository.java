package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.TabelaPreco;

import java.util.List;

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

}
