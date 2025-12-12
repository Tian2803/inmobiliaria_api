package com.example.inmobiliaria.dto;

import java.util.List;

import com.example.inmobiliaria.dto.categoria.CategoriaCantidadDTO;
import com.example.inmobiliaria.dto.producto.ProductoSimpleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumenDTO {
    private long totalProductos;
    private long totalCategorias;
    private double valorInventario;
    private double valorInventarioCategoriasActivas;
    private double valorInventarioCategoriasInactivas;
    private double precioPromedio;
    private ProductoSimpleDTO productoMasCaro;
    private ProductoSimpleDTO productoMasBarato;
    private List<CategoriaCantidadDTO> productosPorCategoria;
}

