package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.cr_metais.entity.Venda;

import java.time.LocalDate;

public interface VendaRepository extends JpaRepository<Venda,Integer> {

		@Query(value = """
						SELECT COALESCE(SUM(ipv.peso_kg * ipv.preco_unitario), 0)
						FROM venda v
						JOIN item_pedido_venda ipv
								ON ipv.id_fk_venda = v.id_venda
						WHERE (:dataInicio IS NULL OR v.data_venda >= :dataInicio)
							AND (:dataFim IS NULL OR v.data_venda <= :dataFim)
						""", nativeQuery = true)
		Double buscarMontanteTotal(@Param("dataInicio") LocalDate dataInicio,
															 @Param("dataFim") LocalDate dataFim);

}
