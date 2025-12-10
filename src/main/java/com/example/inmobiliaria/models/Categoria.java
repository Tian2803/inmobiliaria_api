package com.example.inmobiliaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categorias")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Categoria {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false )
    private String descripcion;

    @Column(name = "activo", nullable = false)
    private Boolean activo;
}