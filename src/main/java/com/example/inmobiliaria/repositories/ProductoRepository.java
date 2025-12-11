package com.example.inmobiliaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inmobiliaria.models.Producto;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Boolean existsByCodigo(String codigo);
    Boolean existsByNombre(String nombre);
    Boolean existsByCategoria(com.example.inmobiliaria.models.Categoria categoria);
}
