package ar.com.juanferrara.desafiointegrador3.presentation.controller;

import ar.com.juanferrara.desafiointegrador3.business.dto.PeliculaDTO;
import ar.com.juanferrara.desafiointegrador3.business.service.PeliculaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,  MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PeliculaDTO> crearPelicula(@RequestPart("pelicula") String peliculaStringToParse, @RequestPart("imagen") MultipartFile peliculaImage) {
        return ResponseEntity.ok(peliculaService.crearPelicula(peliculaStringToParse, peliculaImage));
    }

    @GetMapping("buscar/titulo")
    public ResponseEntity<List<PeliculaDTO>> buscarPeliculaPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(peliculaService.buscarPeliculasPorTitulo(titulo));
    }

    @GetMapping("buscar/genero")
    public ResponseEntity<List<PeliculaDTO>> buscarPeliculaPorGenero(@RequestParam String genero) {
        return ResponseEntity.ok(peliculaService.buscarPeliculasPorGenero(genero));
    }

}
