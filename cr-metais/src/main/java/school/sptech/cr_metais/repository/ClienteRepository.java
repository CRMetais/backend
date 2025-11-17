package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cr_metais.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

