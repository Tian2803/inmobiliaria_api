package com.example.inmobiliaria.services.producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.inmobiliaria.dto.producto.ProductoCreateRequest;
import com.example.inmobiliaria.dto.producto.ProductoResponse;
import com.example.inmobiliaria.dto.producto.ProductoUpdateRequest;

public interface ProductoService {
    ProductoResponse registrar(ProductoCreateRequest request);
    void actualizar(Integer id, ProductoUpdateRequest request);
    Page<ProductoResponse> listarProductos(Pageable pageable);
    void eliminar(Integer id);
    Page<ProductoResponse> filtrarProductos(String nombre, String marca, Float precioMin, Float precioMax, Integer categoriaId, Pageable pageable);
    Object obtenerResumen(); // Podría ser un DTO con estadísticas
}
