package ar.com.juanferrara.desafiointegrador3.business.service;

import ar.com.juanferrara.desafiointegrador3.domain.entity.Usuario;

public interface JwtService {

    String generarToken(Usuario usuario);
    void validarToken(String token);
    String obtenerEmailDelToken(String token);
}
