package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.cr_metais.entity.Produto;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResumoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = """
            SELECT
                p.nome,
            
                COALESCE(c.total_comprado, 0) AS total_comprado,
                COALESCE(v.total_vendido, 0) AS total_vendido,
            
                COALESCE(c.total_comprado, 0)
                - COALESCE(v.total_vendido, 0) AS material_disponivel
            
            FROM produto p
            
            LEFT JOIN (
                SELECT
                    id_fk_produto,
                    SUM(peso_kg) AS total_comprado
                FROM item_pedido_compra
                GROUP BY id_fk_produto
            ) c
                ON p.id_produto = c.id_fk_produto
            
            LEFT JOIN (
                SELECT
                    id_fk_produto,
                    SUM(peso_kg) AS total_vendido
                FROM item_pedido_venda
                GROUP BY id_fk_produto
            ) v
                ON p.id_produto = v.id_fk_produto
            
            ORDER BY material_disponivel DESC
            """, nativeQuery = true)

//    List<Object[]> buscarResumoProdutos();

//    @Query("""
//    SELECT
//        p.nome,
//        SUM(i.pesoKg),
//        ppt.precoProduto,
//        tp.nomeTabela
//    FROM ItemPedidoCompra i
//    JOIN i.produto p
//    JOIN PrecoProdutoTabela ppt ON ppt.produto.idProduto = p.idProduto
//    JOIN ppt.tabelaPreco tp
//    WHERE ppt.versao = (
//        SELECT MAX(ppt2.versao)
//        FROM PrecoProdutoTabela ppt2
//        WHERE ppt2.produto.idProduto = p.idProduto
//    )
//    GROUP BY
//        p.nome,
//        ppt.precoProduto,
//        tp.nomeTabela
//""")
    List<Object[]> buscarResumoProdutos();

    @Query(value = """
            SELECT
                COALESCE(SUM(
                    CASE
                        WHEN COALESCE(c.total_comprado, 0) > 0 THEN
                            (
                                (
                                    COALESCE(c.total_comprado, 0)
                                    - COALESCE(v.total_vendido, 0)
                                )
                                *
                                (
                                    COALESCE(c.valor_total_compras, 0)
                                    / COALESCE(c.total_comprado, 0)
                                )
                            )
                        ELSE 0
                    END
                ), 0) AS valor_total_estoque
            
            FROM produto p
            
            LEFT JOIN (
                SELECT
                    id_fk_produto,
                    SUM(peso_kg) AS total_comprado,
                    SUM(peso_kg * preco_unitario) AS valor_total_compras
                FROM item_pedido_compra
                GROUP BY id_fk_produto
            ) c
                ON p.id_produto = c.id_fk_produto
            
            LEFT JOIN (
                SELECT
                    id_fk_produto,
                    SUM(peso_kg) AS total_vendido
                FROM item_pedido_venda
                GROUP BY id_fk_produto
            ) v
                ON p.id_produto = v.id_fk_produto
            """, nativeQuery = true)
    Double totalAplicado();

    @Query(value = """
            SELECT
                COALESCE(SUM(
                    COALESCE(c.total_comprado, 0)
                    - COALESCE(v.total_vendido, 0)
                ), 0) AS peso_total_estoque
            
            FROM produto p
            
            LEFT JOIN (
                SELECT
                    id_fk_produto,
                    SUM(peso_kg) AS total_comprado
                FROM item_pedido_compra
                GROUP BY id_fk_produto
            ) c
                ON p.id_produto = c.id_fk_produto
            
            LEFT JOIN (
                SELECT
                    id_fk_produto,
                    SUM(peso_kg) AS total_vendido
                FROM item_pedido_venda
                GROUP BY id_fk_produto
            ) v
                ON p.id_produto = v.id_fk_produto
            """, nativeQuery = true)
    Double pesoTotal();

    @Query(value = """
            SELECT SUM(i.pesoKg * i.precoUnitario)
            FROM ItemPedidoVenda i
            WHERE i.venda.dataVenda = :data
            """)
    Double notasHoje(LocalDate data);

    @Query(value = """
            SELECT SUM(i.pesoKg)
            FROM ItemPedidoVenda i
            WHERE i.venda.dataVenda = :data
            """)
    Double pesoHoje(LocalDate data);


    @Query(value = """
            SELECT
            razao_social
            FROM cliente
            ORDER BY razao_social
            """, nativeQuery = true)
    List<String> buscarClientesResumo();

    @Query(value = """
            SELECT
            tp.nome_tabela,
            p.nome AS nome_produto,
            pt.preco_produto
    
        FROM preco_produto_tabela pt
    
        INNER JOIN tabela_preco tp
            ON pt.fk_tabela_preco = tp.id_tabela
    
        INNER JOIN produto p
            ON pt.fk_produto = p.id_produto
    
        WHERE tp.ativa = 1
        AND tp.tipo = 'V'
    
        ORDER BY tp.nome_tabela, p.nome
            """, nativeQuery = true)
    List<Object[]> buscarTabelasResumo();



}