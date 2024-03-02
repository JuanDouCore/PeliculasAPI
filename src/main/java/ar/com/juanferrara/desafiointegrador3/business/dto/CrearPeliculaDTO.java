package ar.com.juanferrara.desafiointegrador3.business.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearPeliculaDTO {

    @NotBlank
    @Size(max = 50, message = "El título de la película no puede superar los 50 caracteres")
    private String titulo;

    @NotEmpty
    private List<Long> generos;

    @NotBlank
    @Size(max = 100, message = "La URL del sitio web no puede superar los 100 caracteres")
    private String urlSitioWeb;

    @NotNull(message = "La imagen de la película es requerida")
    private MultipartFile imagen;
}
