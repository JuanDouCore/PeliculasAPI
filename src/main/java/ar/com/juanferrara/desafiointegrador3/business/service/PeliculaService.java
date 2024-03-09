package ar.com.juanferrara.desafiointegrador3.business.service;

import ar.com.juanferrara.desafiointegrador3.business.dto.PeliculaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PeliculaService {

    PeliculaDTO crearPelicula(String peliculaStringToParse, MultipartFile peliculaImage);
    List<PeliculaDTO> buscarPeliculasPorTitulo (String titulo);
    List<PeliculaDTO> buscarPeliculasPorGenero (String genero);
}
