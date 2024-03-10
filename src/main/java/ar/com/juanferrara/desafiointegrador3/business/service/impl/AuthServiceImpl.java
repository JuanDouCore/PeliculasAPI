package ar.com.juanferrara.desafiointegrador3.business.service.impl;

import ar.com.juanferrara.desafiointegrador3.business.dto.AuthResponseDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.AuthenticationRequestDTO;
import ar.com.juanferrara.desafiointegrador3.business.service.AuthService;
import ar.com.juanferrara.desafiointegrador3.business.service.JwtService;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Usuario;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.GenericException;
import ar.com.juanferrara.desafiointegrador3.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponseDTO register(AuthenticationRequestDTO registerRequestDTO) {
        if(usuarioRepository.existsByCorreo(registerRequestDTO.getEmail()))
            throw new GenericException("El correo ya se encuentra registrado", HttpStatus.BAD_REQUEST);

        Usuario usuarioForRegister = Usuario.builder()
                .correo(registerRequestDTO.getEmail())
                .contraseña(registerRequestDTO.getPassword())
                .build();

        String token = jwtService.generarToken(usuarioRepository.save(usuarioForRegister));

        return AuthResponseDTO.builder()
                .email(registerRequestDTO.getEmail())
                .tokenType("Bearer")
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO) {
        if(!usuarioRepository.existsByCorreo(authenticationRequestDTO.getEmail()))
            throw new GenericException("Bad credentials", HttpStatus.FORBIDDEN);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail(),
                        authenticationRequestDTO.getPassword());

        authenticationProvider.authenticate(usernamePasswordAuthenticationToken);

        Usuario usuario = usuarioRepository.findByCorreo(authenticationRequestDTO.getEmail()).get();
        String token = jwtService.generarToken(usuario);

        return AuthResponseDTO.builder()
                .email(authenticationRequestDTO.getEmail())
                .tokenType("Bearer")
                .token(token)
                .build();
    }
}
