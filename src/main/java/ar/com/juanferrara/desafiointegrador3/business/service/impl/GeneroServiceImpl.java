package ar.com.juanferrara.desafiointegrador3.business.service.impl;

import ar.com.juanferrara.desafiointegrador3.business.dto.CrearGeneroDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.GeneroDTO;
import ar.com.juanferrara.desafiointegrador3.business.mapper.CrearGeneroMapper;
import ar.com.juanferrara.desafiointegrador3.business.mapper.GeneroMapper;
import ar.com.juanferrara.desafiointegrador3.business.service.GeneroService;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Genero;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.GenericException;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.NotFoundException;
import ar.com.juanferrara.desafiointegrador3.persistence.GeneroRepository;
import ar.com.juanferrara.desafiointegrador3.persistence.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private CrearGeneroMapper crearGeneroMapper;
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public GeneroDTO crearGenero(CrearGeneroDTO generoDTO) {
        Genero genero = crearGeneroMapper.toEntity(generoDTO);
        return generoMapper.toDto(generoRepository.save(genero));
    }

    @Override
    public GeneroDTO modificarGenero(Long id, CrearGeneroDTO generoDTO) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genero no encontrado"));

        generoMapper.updateEntity(genero, crearGeneroMapper.toEntity(generoDTO));
        genero.setId(id);

        generoRepository.save(genero);

        return generoMapper.toDto(genero);
    }

    @Override
    public GeneroDTO eliminarGenero(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genero no encontrado"));

        if (peliculaRepository.existByGeneroId(id))
            throw new GenericException("No se puede eliminar el genero porque hay peliculas asociadas a el", HttpStatus.BAD_REQUEST);

        generoRepository.delete(genero);

        return generoMapper.toDto(genero);
    }

    @Override
    public GeneroDTO getGeneroPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genero no encontrado"));

        return generoMapper.toDto(genero);
    }

    @Override
    public List<GeneroDTO> listarTodosLosGeneros() {
        return generoMapper.toDTOList(generoRepository.findAll());
    }
}
