package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cr_metais.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Boolean existsByEmail(String value);

}
