package ar.com.juanferrara.desafiointegrador3.presentation.controller;

import ar.com.juanferrara.desafiointegrador3.business.dto.AuthResponseDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.AuthenticationRequestDTO;
import ar.com.juanferrara.desafiointegrador3.business.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
