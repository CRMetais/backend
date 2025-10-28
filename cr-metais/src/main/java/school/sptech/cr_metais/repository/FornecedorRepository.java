package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cr_metais.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Boolean existsByDocumento(String documento);

    Boolean existsByApelido(String value);

}