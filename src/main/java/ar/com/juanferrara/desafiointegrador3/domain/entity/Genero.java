package ar.com.juanferrara.desafiointegrador3.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genero")

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
}