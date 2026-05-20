package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cr_metais.entity.Cliente;
import school.sptech.cr_metais.entity.ContaPagamento;

import java.util.Optional;

public interface ContaPagamentoRepository extends JpaRepository<ContaPagamento, Integer> {
    void deleteByFornecedor_IdFornecedor(Integer idFornecedor);

    Optional<ContaPagamento> findByFornecedor_IdFornecedor(Integer idFornecedor);

}