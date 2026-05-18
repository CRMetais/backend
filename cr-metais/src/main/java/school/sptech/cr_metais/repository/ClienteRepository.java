// Em: school.sptech.cr_metais.repository.ClienteRepository

package school.sptech.cr_metais.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByAtivoTrue();

    // Corrigido de findById para findByIdCliente
    Optional<Cliente> findByIdClienteAndAtivoTrue(Integer idCliente);

    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.ativo = false WHERE c.idCliente = :id")
    int inativarCliente(Integer id);
}