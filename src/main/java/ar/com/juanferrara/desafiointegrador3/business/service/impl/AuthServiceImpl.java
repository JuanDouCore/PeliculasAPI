package ar.com.juanferrara.desafiointegrador3.business.service.impl;

import ar.com.juanferrara.desafiointegrador3.business.dto.AuthResponseDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.AuthenticationRequestDTO;
import ar.com.juanferrara.desafiointegrador3.business.service.AuthService;
import ar.com.juanferrara.desafiointegrador3.business.service.JwtService;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Usuario;
import ar.com.juanferrara.desafiointegrador3.domain.enums.Rol;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.GenericException;
import ar.com.juanferrara.desafiointegrador3.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponseDTO register(AuthenticationRequestDTO registerRequestDTO) {
        if(usuarioRepository.existsByCorreo(registerRequestDTO.getCorreo()))
            throw new GenericException("El correo ya se encuentra registrado", HttpStatus.BAD_REQUEST);

        Usuario usuarioForRegister = Usuario.builder()
                .correo(registerRequestDTO.getCorreo())
                .contraseña(passwordEncoder.encode(registerRequestDTO.getContraseña()))
                .role(Rol.USER)
                .build();

        String token = jwtService.generarToken(usuarioRepository.save(usuarioForRegister));

        return AuthResponseDTO.builder()
                .email(registerRequestDTO.getCorreo())
                .tokenType("Bearer")
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO) {
        if(!usuarioRepository.existsByCorreo(authenticationRequestDTO.getCorreo()))
            throw new GenericException("Bad credentials", HttpStatus.FORBIDDEN);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getCorreo(),
                        authenticationRequestDTO.getContraseña());

        authenticationProvider.authenticate(usernamePasswordAuthenticationToken);

        Usuario usuario = usuarioRepository.findByCorreo(authenticationRequestDTO.getCorreo()).get();
        String token = jwtService.generarToken(usuario);

        return AuthResponseDTO.builder()
                .email(authenticationRequestDTO.getCorreo())
                .tokenType("Bearer")
                .token(token)
                .build();
    }
}
