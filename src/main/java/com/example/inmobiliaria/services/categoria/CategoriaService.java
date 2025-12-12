package com.example.inmobiliaria.services.categoria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.inmobiliaria.dto.categoria.CategoriaCreateRequest;
import com.example.inmobiliaria.dto.categoria.CategoriaResponse;
import com.example.inmobiliaria.dto.categoria.CategoriaSimpleResponse;
import com.example.inmobiliaria.dto.categoria.CategoriaUpdateRequest;

public interface CategoriaService {
    CategoriaResponse registrar(CategoriaCreateRequest request);
    Page<CategoriaResponse> listarCategorias(String codigo, String nombre, Boolean activo, Pageable pageable);
    void actualizarCategoria(Integer id, CategoriaUpdateRequest request);
    void eliminar(Integer id);
    List<CategoriaSimpleResponse> listarCategorias();
}