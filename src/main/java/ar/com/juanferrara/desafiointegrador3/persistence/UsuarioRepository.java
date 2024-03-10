package ar.com.juanferrara.desafiointegrador3.persistence;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}