package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.Fornecedor;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Boolean existsByDocumento(String documento);

    Boolean existsByApelido(String value);

    @Query(value = """
            SELECT
                f.id_fornecedor,
                f.nome,
                f.apelido,
                SUM(COALESCE(ipc.rendimento, 0)) AS total_rendimento
            FROM fornecedor f
            JOIN compra c
                ON c.fk_fornecedor = f.id_fornecedor
            JOIN item_pedido_compra ipc
                ON ipc.id_fk_compra = c.id_compra
            GROUP BY f.id_fornecedor, f.nome, f.apelido
            ORDER BY total_rendimento DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Object[]> buscarTop10FornecedoresPorRendimento();

}