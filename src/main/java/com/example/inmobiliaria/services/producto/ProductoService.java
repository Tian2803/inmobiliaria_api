package com.example.inmobiliaria.services.producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.inmobiliaria.dto.ResumenDTO;
import com.example.inmobiliaria.dto.producto.ProductoCreateRequest;
import com.example.inmobiliaria.dto.producto.ProductoResponse;
import com.example.inmobiliaria.dto.producto.ProductoUpdateRequest;

public interface ProductoService {
    ProductoResponse registrar(ProductoCreateRequest request);
    void actualizar(Integer id, ProductoUpdateRequest request);
    Page<ProductoResponse> listarProductos(String nombre, String marca, Integer categoria, Float precioMin, Float precioMax, Pageable pageable);
    void eliminar(Integer id);
    ResumenDTO obtenerResumen();
}
