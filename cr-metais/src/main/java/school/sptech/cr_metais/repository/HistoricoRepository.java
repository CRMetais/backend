package school.sptech.cr_metais.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.Compra;

import java.util.UUID;

public interface HistoricoRepository extends JpaRepository<Compra, UUID> {

    @Query(value = """
    SELECT DISTINCT 
        c.id_compra AS id,
        c.data_compra AS data,
        f.nome AS parceiro,
        p.nome AS produto,
        ipc.peso_kg AS peso,
        ipc.preco_unitario AS preco,
        (ipc.peso_kg * ipc.preco_unitario) AS total,
        ipc.rendimento AS rendimento,
        'COMPRA' AS tipo
    FROM compra c
    JOIN fornecedor f ON c.fk_fornecedor = f.id_fornecedor
    JOIN item_pedido_compra ipc ON c.id_compra = ipc.id_fk_compra
    JOIN produto p ON ipc.id_fk_produto = p.id_produto
    ORDER BY c.id_compra DESC
""",
            countQuery = """
    SELECT COUNT(DISTINCT c.id_compra)
    FROM compra c
""",
            nativeQuery = true)
    Page<Object[]> listarCompras(Pageable pageable);


    @Query(value = """
    SELECT DISTINCT
        v.id_venda AS id,
        v.data_venda AS data,
        cl.razao_social AS parceiro,
        p.nome AS produto,
        ipv.peso_kg AS peso,
        ipv.preco_unitario AS preco,
        (ipv.peso_kg * ipv.preco_unitario) AS total,
        NULL AS rendimento,
        'VENDA' AS tipo
    FROM venda v
    JOIN cliente cl ON v.fk_cliente = cl.id_cliente
    JOIN item_pedido_venda ipv ON v.id_venda = ipv.id_fk_venda
    JOIN produto p ON ipv.id_fk_produto = p.id_produto
    ORDER BY v.id_venda DESC
""",
            countQuery = """
    SELECT COUNT(DISTINCT v.id_venda)
    FROM venda v
""",
            nativeQuery = true)
    Page<Object[]> listarVendas(Pageable pageable);

}