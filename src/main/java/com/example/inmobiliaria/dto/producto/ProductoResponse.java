package com.example.inmobiliaria.dto.producto;

public record ProductoResponse(
    int id,
    String codigo,
    String nombre,
    String descripcion,
    String marca,
    String categoria,
    float precio
) {
}
