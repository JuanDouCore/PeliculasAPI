package ar.com.juanferrara.desafiointegrador3.persistence;

import ar.com.juanferrara.desafiointegrador3.domain.entity.ImagenPelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenPeliculaRepository extends JpaRepository<ImagenPelicula, Long> {
}