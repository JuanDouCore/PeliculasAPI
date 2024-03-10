package ar.com.juanferrara.desafiointegrador3.business.service;

import ar.com.juanferrara.desafiointegrador3.business.dto.AuthResponseDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.AuthenticationRequestDTO;

public interface AuthService {

    AuthResponseDTO register(AuthenticationRequestDTO registerRequestDTO);
    AuthResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO);
}
