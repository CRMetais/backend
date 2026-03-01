package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import school.sptech.cr_metais.entity.Estoque;
import school.sptech.cr_metais.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    List<Estoque> findByProduto(Produto produto);
    Optional<Estoque> findFirstByProduto(Produto produto);
}
