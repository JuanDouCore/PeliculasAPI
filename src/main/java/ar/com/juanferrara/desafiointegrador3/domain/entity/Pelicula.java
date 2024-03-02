package ar.com.juanferrara.desafiointegrador3.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pelicula")

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 50)
    private String titulo;

    @ManyToMany
    @JoinTable(
            name = "pelicula_genero",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> generosList;

    @Column(name = "url_sitio_web", nullable = false, length = 100)
    private String urlSitioWeb;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "imagen_id", nullable = false)
    private ImagenPelicula imagen;
}