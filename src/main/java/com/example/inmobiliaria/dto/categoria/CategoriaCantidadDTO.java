package com.example.inmobiliaria.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaCantidadDTO {
    private String categoria;
    private long cantidad;
}