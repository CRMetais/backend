package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cr_metais.entity.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
}
