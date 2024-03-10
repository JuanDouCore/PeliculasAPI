package ar.com.juanferrara.desafiointegrador3.persistence;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}