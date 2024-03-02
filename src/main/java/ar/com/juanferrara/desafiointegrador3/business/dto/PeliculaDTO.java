package ar.com.juanferrara.desafiointegrador3.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeliculaDTO {

    private Long id;
    private String titulo;
    private String generos;
    private String urlSitioWeb;
    private String nombreArchivoImagen;
}
