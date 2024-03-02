package ar.com.juanferrara.desafiointegrador3.business.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface IGenericMapper<Entity, DTO> {
    Entity toEntity(DTO dto);
    DTO toDto(Entity entity);
    List<DTO> toDTOList(List<Entity> entityList);
    List<Entity> toEntityList(List<DTO> dtoList);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Entity target, Entity source);
}
