package ar.com.juanferrara.desafiointegrador3.business.mapper;

import ar.com.juanferrara.desafiointegrador3.business.dto.PeliculaDTO;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PeliculaMapper extends IGenericMapper<Pelicula, PeliculaDTO>{
    @Override
    @Mapping(target = "nombreArchivoImagen", source = "pelicula.imagen.nombreArchivo")
    PeliculaDTO toDto(Pelicula pelicula);
}
