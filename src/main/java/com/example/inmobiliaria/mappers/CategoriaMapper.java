package com.example.inmobiliaria.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.example.inmobiliaria.dto.categoria.CategoriaCreateRequest;
import com.example.inmobiliaria.dto.categoria.CategoriaResponse;
import com.example.inmobiliaria.dto.categoria.CategoriaUpdateRequest;
import com.example.inmobiliaria.models.Categoria;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true) // El ID se deriva automáticamente con @MapsId
    Categoria toEntity(CategoriaCreateRequest request);

    CategoriaResponse toResponse(Categoria entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void update(CategoriaUpdateRequest update, @MappingTarget Categoria entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void patch(CategoriaUpdateRequest update, @MappingTarget Categoria entity);
}
