package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.Produto;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Produto, Integer> {

    // ── 1. Info por fornecedor (mensal) ──────────────────────────────────────
    @Query(value = """
        SELECT
            f.nome                   AS nome_fornecedor,
            YEAR(c.data_compra)      AS ano,
            MONTH(c.data_compra)     AS mes,
            SUM(ipc.peso_kg)         AS peso_total,
            SUM(ipc.rendimento)      AS rendimento_total
        FROM compra c
        INNER JOIN fornecedor f
            ON c.fk_fornecedor = f.id_fornecedor
        INNER JOIN item_pedido_compra ipc
            ON c.id_compra = ipc.id_fk_compra
        GROUP BY
            f.nome,
            YEAR(c.data_compra),
            MONTH(c.data_compra)
        ORDER BY
            ano,
            mes,
            f.nome
    """, nativeQuery = true)
    List<Object[]> infoFornecedor();

    // ── 2. Rendimento por produto (mensal) ───────────────────────────────────
    @Query(value = """
        SELECT
            p.nome                   AS produto,
            YEAR(c.data_compra)      AS ano,
            MONTH(c.data_compra)     AS mes,
            SUM(ipc.rendimento)      AS rendimento_total,
            SUM(ipc.peso_kg)         AS peso_total
        FROM compra c
        INNER JOIN item_pedido_compra ipc
            ON c.id_compra = ipc.id_fk_compra
        INNER JOIN produto p
            ON ipc.id_fk_produto = p.id_produto
        GROUP BY
            p.nome,
            YEAR(c.data_compra),
            MONTH(c.data_compra)
        ORDER BY
            rendimento_total DESC
    """, nativeQuery = true)
    List<Object[]> infoProduto();

    // ── 3. Variação mensal Cobre mel + Cobre misto ───────────────────────────
    @Query(value = """
        WITH media_mensal AS (
            SELECT
                DATE_FORMAT(tp.data_inicio_validade, '%Y-%m-01') AS mes,
                AVG(ppt.preco_produto)                           AS media_preco
            FROM preco_produto_tabela ppt
            JOIN produto p
                ON p.id_produto = ppt.fk_produto
            JOIN tabela_preco tp
                ON tp.id_tabela = ppt.fk_tabela_preco
            WHERE p.nome IN ('Cobre mel', 'Cobre misto')
              AND tp.nome_tabela = 'VITAL'
            GROUP BY DATE_FORMAT(tp.data_inicio_validade, '%Y-%m-01')
        )
        SELECT
            mes,
            media_preco,
            ROUND(
                (
                    (media_preco - LAG(media_preco) OVER (ORDER BY mes))
                    / LAG(media_preco) OVER (ORDER BY mes)
                ) * 100,
                2
            ) AS variacao_percentual
        FROM media_mensal
        ORDER BY mes ASC
    """, nativeQuery = true)
    List<Object[]> analiseVariacaoCobre();
}