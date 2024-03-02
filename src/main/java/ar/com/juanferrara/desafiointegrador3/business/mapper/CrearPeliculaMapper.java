package ar.com.juanferrara.desafiointegrador3.business.mapper;

import ar.com.juanferrara.desafiointegrador3.business.dto.CrearPeliculaDTO;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CrearPeliculaMapper extends IGenericMapper<Pelicula, CrearPeliculaDTO> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "generos", ignore = true)
    @Mapping(target = "imagen", ignore = true)
    Pelicula toEntity(CrearPeliculaDTO crearPeliculaDTO);
}
