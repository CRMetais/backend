package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.cr_metais.entity.Compra;

import java.time.LocalDate;

public interface    CompraRepository extends JpaRepository<Compra,Integer> {

		@Query(value = """
						SELECT COALESCE(SUM(ipc.peso_kg * ipc.preco_unitario), 0)
						FROM compra c
						JOIN item_pedido_compra ipc
								ON ipc.id_fk_compra = c.id_compra
						WHERE (:dataInicio IS NULL OR c.data_compra >= :dataInicio)
							AND (:dataFim IS NULL OR c.data_compra <= :dataFim)
						""", nativeQuery = true)
		Double buscarMontanteTotal(@Param("dataInicio") LocalDate dataInicio,
															 @Param("dataFim") LocalDate dataFim);

}
