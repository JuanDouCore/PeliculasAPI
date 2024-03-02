package ar.com.juanferrara.desafiointegrador3.business.mapper;

import ar.com.juanferrara.desafiointegrador3.business.dto.GeneroDTO;
import ar.com.juanferrara.desafiointegrador3.domain.entity.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GeneroMapper extends IGenericMapper<Genero, GeneroDTO>{
}
