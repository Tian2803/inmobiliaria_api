package com.example.inmobiliaria.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoSimpleDTO {
    private String nombre;
    private float precio;
}
