package com.example.inmobiliaria.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.example.inmobiliaria.dto.producto.ProductoCreateRequest;
import com.example.inmobiliaria.dto.producto.ProductoResponse;
import com.example.inmobiliaria.dto.producto.ProductoUpdateRequest;
import com.example.inmobiliaria.models.Categoria;
import com.example.inmobiliaria.models.Producto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoCreateRequest request);

    @Mapping(target = "categoria", source = "categoria.nombre")
    ProductoResponse toResponse(Producto entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    void update(ProductoUpdateRequest update, @MappingTarget Producto entity);
    
    default String mapCategoria(Categoria categoria) {
        return categoria != null ? categoria.getNombre() : null;
    }
}
