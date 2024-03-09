package ar.com.juanferrara.desafiointegrador3.business.service.impl;

import ar.com.juanferrara.desafiointegrador3.business.service.ImageService;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Genero;
import ar.com.juanferrara.desafiointegrador3.domain.entity.ImagenPelicula;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Pelicula;
import ar.com.juanferrara.desafiointegrador3.persistence.GeneroRepository;
import ar.com.juanferrara.desafiointegrador3.persistence.PeliculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PeliculaServiceImplTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ImageService imageService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void crearPelicula() throws IOException {
        Genero genero = new Genero();
        genero.setNombre("genero1");
        Genero genero1 = generoRepository.save(genero);

        genero = new Genero();
        genero.setNombre("genero2");
        Genero genero2 = generoRepository.save(genero);

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Titulo01");
        pelicula.setUrlSitioWeb("url web pelicula 01");

        pelicula.setGenerosList(List.of(genero1, genero2));

        ImagenPelicula imagenPelicula = new ImagenPelicula();
        imagenPelicula.setNombreArchivo("pelicula01.jpg");
        imagenPelicula.setContenido(imageService.convertMultiPartFileToByteArray(new MockMultipartFile("pelicula01.jpeg", getClass().
                getResourceAsStream("./resources/pelicula01.jpg"))));

        pelicula.setImagen(imagenPelicula);

        Pelicula peliculaGuardada = peliculaRepository.save(pelicula);

        List<Pelicula> peliculas = peliculaRepository.findAll();

        assertTrue(!peliculas.isEmpty() && peliculas.get(0).getId().equals(peliculaGuardada.getId()));

        peliculaRepository.deleteAll();
        generoRepository.deleteAll();

        assertTrue(peliculaRepository.findAll().isEmpty() && generoRepository.findAll().isEmpty());

    }
}