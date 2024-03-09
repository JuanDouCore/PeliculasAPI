package ar.com.juanferrara.desafiointegrador3.persistence;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    Optional<Genero> findByNombre(String genero);
}