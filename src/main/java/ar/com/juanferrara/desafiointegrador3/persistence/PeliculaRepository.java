package ar.com.juanferrara.desafiointegrador3.persistence;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Genero;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Pelicula p JOIN p.generosList g WHERE g.id = :generoId")
    boolean existByGeneroId(Long generoId);

    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

    List<Pelicula> findByGenerosListContaining(Genero generoEntity);
}