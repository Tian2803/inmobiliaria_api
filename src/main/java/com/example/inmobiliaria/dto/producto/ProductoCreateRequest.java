package com.example.inmobiliaria.dto.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductoCreateRequest(
        @NotBlank(message = "El código no puede estar en blanco") 
        @Size(min = 4, max = 10, message = "El código debe tener entre 4 y 10 caracteres") 
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "El código no puede contener caracteres especiales ni espacios") 
        String codigo,

        @NotBlank(message = "El nombre es obligatorio") 
        @Size(min = 4, message = "El nombre debe tener al menos 4 caracteres") 
        String nombre,

        @NotBlank(message = "La descripción es obligatoria") 
        @Size(min = 4, message = "La descripción debe tener al menos 4 caracteres") 
        String descripcion,

        @NotBlank(message = "La marca es obligatoria") 
        @Size(min = 2, message = "La marca debe tener al menos 4 caracteres")
        String marca,

        @Positive(message = "El precio debe ser un valor positivo")
        @NotNull(message = "El precio es obligatorio")
        Float precio,
        
        @NotNull(message = "La categoría es obligatoria")
        Integer categoria) {
}
