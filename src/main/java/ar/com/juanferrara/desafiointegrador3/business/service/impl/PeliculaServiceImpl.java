package ar.com.juanferrara.desafiointegrador3.business.service.impl;

import ar.com.juanferrara.desafiointegrador3.business.dto.CrearPeliculaDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.PeliculaDTO;
import ar.com.juanferrara.desafiointegrador3.business.mapper.CrearPeliculaMapper;
import ar.com.juanferrara.desafiointegrador3.business.mapper.PeliculaMapper;
import ar.com.juanferrara.desafiointegrador3.business.service.ImageService;
import ar.com.juanferrara.desafiointegrador3.business.service.PeliculaService;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Genero;
import ar.com.juanferrara.desafiointegrador3.domain.entity.ImagenPelicula;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Pelicula;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.GenericException;
import ar.com.juanferrara.desafiointegrador3.persistence.GeneroRepository;
import ar.com.juanferrara.desafiointegrador3.persistence.PeliculaRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private CrearPeliculaMapper crearPeliculaMapper;

    @Autowired
    private ImageService imageService;

    @Override
    public PeliculaDTO crearPelicula(String peliculaStringToParse, MultipartFile peliculaImage) {
        Gson gson = new Gson();
        CrearPeliculaDTO peliculaDTO = gson.fromJson(peliculaStringToParse, CrearPeliculaDTO.class);
        peliculaDTO.setImagen(peliculaImage);

        Pelicula pelicula = crearPeliculaMapper.toEntity(peliculaDTO);
        pelicula.setImagen(
                ImagenPelicula.builder()
                        .nombreArchivo(peliculaDTO.getImagen().getOriginalFilename())
                        .contenido(imageService.convertMultiPartFileToByteArray(peliculaDTO.getImagen()))
                        .build()
        );
        pelicula.setGenerosList(peliculaDTO.getGeneros().stream()
                .map( id -> generoRepository.findById(id)
                        .orElseThrow(() -> new GenericException("No se encontró el género con id " + id, HttpStatus.BAD_REQUEST)))
                .toList()
        );

        return this.mapPeliculaToPeliculaDTO(peliculaRepository.save(pelicula));
    }

    @Override
    public List<PeliculaDTO> buscarPeliculasPorTitulo(String titulo) {
        return peliculaRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(this::mapPeliculaToPeliculaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PeliculaDTO> buscarPeliculasPorGenero(String genero) {
        Genero generoEntity = generoRepository.findByNombre(genero)
                .orElseThrow(() -> new GenericException("No se encontró el género " + genero, HttpStatus.BAD_REQUEST));

        return peliculaRepository.findByGenerosListContaining(generoEntity).stream()
                .map(this::mapPeliculaToPeliculaDTO)
                .collect(Collectors.toList());
    }

    private PeliculaDTO mapPeliculaToPeliculaDTO(Pelicula pelicula) {
        PeliculaDTO peliculaDTO =  peliculaMapper.toDto(pelicula);
        peliculaDTO.setGeneros(pelicula.getGenerosList().stream()
                .map(Genero::getNombre)
                .collect(Collectors.joining(", "))
        );

        return peliculaDTO;
    }
}
