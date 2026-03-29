package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.dto.Historico.HistoricoCompraDto;
import school.sptech.cr_metais.dto.Historico.HistoricoVendaDto;
import school.sptech.cr_metais.entity.Compra;

import java.util.List;
import java.util.UUID;

public interface HistoricoRepository extends JpaRepository<Compra, UUID> {


    @Query(value = """
    SELECT c.id_compra AS id, c.data_compra AS data, f.nome AS parceiro, p.nome AS produto, 
           ipc.peso_kg AS peso, ipc.preco_unitario AS preco, (ipc.peso_kg * ipc.preco_unitario) AS total, 
           ipc.rendimento AS rendimento, 'COMPRA' AS tipo
    FROM compra c
    JOIN fornecedor f ON c.fk_fornecedor = f.id_fornecedor
    JOIN item_pedido_compra ipc ON c.id_compra = ipc.id_fk_compra
    JOIN produto p ON ipc.id_fk_produto = p.id_produto
    """, nativeQuery = true)
    List<HistoricoCompraDto> listarCompras();


    @Query(value = """
    SELECT v.id_venda AS id, v.data_venda AS data, cl.razao_social AS parceiro, p.nome AS produto, 
           ipv.peso_kg AS peso, ipv.preco_unitario AS preco, (ipv.peso_kg * ipv.preco_unitario) AS total, 
           NULL AS rendimento, 'VENDA' AS tipo
    FROM venda v
    JOIN cliente cl ON v.fk_cliente = cl.id_cliente
    JOIN item_pedido_venda ipv ON v.id_venda = ipv.id_fk_venda
    JOIN produto p ON ipv.id_fk_produto = p.id_produto
    """, nativeQuery = true)
    List<HistoricoVendaDto> listarVendas();
}
