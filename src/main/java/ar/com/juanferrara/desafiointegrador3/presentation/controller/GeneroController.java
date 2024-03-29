package ar.com.juanferrara.desafiointegrador3.presentation.controller;

import ar.com.juanferrara.desafiointegrador3.business.dto.CrearGeneroDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.GeneroDTO;
import ar.com.juanferrara.desafiointegrador3.business.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping("{id}")
    public ResponseEntity<GeneroDTO> buscarGeneroPorId(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.getGeneroPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarGeneros() {
        return ResponseEntity.ok(generoService.listarTodosLosGeneros());
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GeneroDTO> crearGenero(@RequestBody @Valid CrearGeneroDTO generoDTO) {
        GeneroDTO genero = generoService.crearGenero(generoDTO);
        return ResponseEntity.created(URI.create("/api/generos/" + genero.getId()))
                .body(genero);
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<GeneroDTO> eliminarGenero(@PathVariable Long id) {
        GeneroDTO generoDTO = generoService.eliminarGenero(id);
        return ResponseEntity.ok(generoDTO);
    }

    @Operation(security = @SecurityRequirement(name = "Bearer Authentication"))
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<GeneroDTO> actualizarGenero(@PathVariable Long id, @RequestBody @Valid CrearGeneroDTO generoDTO) {
        return ResponseEntity.ok(generoService.modificarGenero(id, generoDTO));
    }
}
