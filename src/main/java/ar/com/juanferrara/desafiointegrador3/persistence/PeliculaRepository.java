package ar.com.juanferrara.desafiointegrador3.persistence;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Pelicula p JOIN p.generos g WHERE g.id = :generoId")
    boolean existByGeneroId(Long generoId);
}