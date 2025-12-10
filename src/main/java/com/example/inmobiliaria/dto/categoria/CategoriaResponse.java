package com.example.inmobiliaria.dto.categoria;

public record CategoriaResponse(
        int id,
        String codigo,
        String nombre,
        String descripcion,
        boolean activo) {
}