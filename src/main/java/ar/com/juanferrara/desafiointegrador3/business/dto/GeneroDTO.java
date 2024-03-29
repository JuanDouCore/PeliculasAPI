package ar.com.juanferrara.desafiointegrador3.business.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneroDTO {
    private Long id;

    @NotBlank
    @Size(max = 50, message = "El nombre del género no puede superar los 50 caracteres")
    private String nombre;
}
