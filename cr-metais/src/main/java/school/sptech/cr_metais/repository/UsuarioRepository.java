package school.sptech.cr_metais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.cr_metais.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
