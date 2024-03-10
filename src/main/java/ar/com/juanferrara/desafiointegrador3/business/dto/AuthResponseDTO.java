package ar.com.juanferrara.desafiointegrador3.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private String email;
    private String tokenType;
    private String token;
}
