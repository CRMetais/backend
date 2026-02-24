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
            SUM(i.pesoKg),
            AVG(i.precoUnitario),
            SUM(i.pesoKg * i.precoUnitario)
        FROM ItemPedidoCompra i
        JOIN i.produto p
        GROUP BY p.nome
            """)
    List<Object[]> buscarResumoProdutos();

    @Query(value = """
        SELECT SUM (i.pesoKg * i.precoUnitario) 
        FROM ItemPedidoCompra i
            """) Double totalAplicado();

    @Query(value = """
        SELECT SUM(i.pesoKg)
        FROM ItemPedidoCompra i
            """) Double pesoTotal();

    @Query(value = """
        SELECT SUM(i.pesoKg * i.precoUnitario)
        FROM ItemPedidoVenda i
        WHERE i.venda.dataVenda = :data
            """) Double notasHoje(LocalDate data);

    @Query(value = """
        SELECT SUM(i.pesoKg)
        FROM ItemPedidoVenda i
        WHERE i.venda.dataVenda = :data
            """) Double pesoHoje(LocalDate data);
}
