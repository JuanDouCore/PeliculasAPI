package ar.com.juanferrara.desafiointegrador3.persistence;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}