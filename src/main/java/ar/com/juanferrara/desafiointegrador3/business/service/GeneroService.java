package ar.com.juanferrara.desafiointegrador3.business.service;

import ar.com.juanferrara.desafiointegrador3.business.dto.CrearGeneroDTO;
import ar.com.juanferrara.desafiointegrador3.business.dto.GeneroDTO;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Genero;

import java.util.List;

public interface GeneroService {

    GeneroDTO crearGenero(CrearGeneroDTO generoDTO);
    GeneroDTO modificarGenero(Long id, CrearGeneroDTO generoDTO);
    GeneroDTO eliminarGenero(Long id);
    GeneroDTO getGeneroPorId(Long id);
    List<GeneroDTO> listarTodosLosGeneros();

}
