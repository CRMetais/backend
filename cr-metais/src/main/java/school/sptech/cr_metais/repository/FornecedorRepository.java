package school.sptech.cr_metais.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.Fornecedor;

import java.time.LocalDate;
import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    List<Fornecedor> findAllByAtivoTrue();

    Boolean existsByDocumentoAndAtivoTrue(String documento);

    Boolean existsByApelidoAndAtivoTrue(String apelido);

    @Modifying
    @Transactional
    @Query("UPDATE Fornecedor f SET f.responsavel = null WHERE f.responsavel.idUsuario = :idUsuario")
    void desassociarResponsavel(@Param("idUsuario") Integer idUsuario);

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
            WHERE (:dataInicio IS NULL OR c.data_compra >= :dataInicio)
              AND (:dataFim IS NULL OR c.data_compra <= :dataFim)
            GROUP BY f.id_fornecedor, f.nome, f.apelido
            ORDER BY total_rendimento DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Object[]> buscarTop10FornecedoresPorRendimento(@Param("dataInicio") LocalDate dataInicio,
                                                        @Param("dataFim") LocalDate dataFim);

}