package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Boolean existsByNome(String value);

    @Query(value = """
            SELECT
                p.id_produto,
                p.nome,
                SUM(COALESCE(ipv.peso_kg, 0)) AS total_peso_vendido
            FROM produto p
            JOIN item_pedido_venda ipv
                ON ipv.id_fk_produto = p.id_produto
            GROUP BY p.id_produto, p.nome
            ORDER BY total_peso_vendido DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Object[]> buscarTop10ProdutosPorPesoVendido();

}
