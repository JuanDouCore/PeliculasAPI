package ar.com.juanferrara.desafiointegrador3.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagen_pelicula")

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ImagenPelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre_archivo", nullable = false, length = 50)
    private String nombreArchivo;

    @Column(name = "contenido", nullable = false)
    private byte[] contenido;

}