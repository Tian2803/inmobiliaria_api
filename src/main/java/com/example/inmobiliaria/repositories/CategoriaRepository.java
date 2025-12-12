package com.example.inmobiliaria.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.inmobiliaria.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Boolean existsByCodigo(String codigo);
    Boolean existsByNombre(String nombre);
    @Query("SELECT c FROM Categoria c WHERE c.activo = true")
    List<Categoria> findByActivoTrue();

    @Query("SELECT c FROM Categoria c WHERE (:codigo IS NULL OR LOWER(c.codigo) LIKE LOWER(CONCAT('%', :codigo, '%'))) " +
           "AND (:nombre IS NULL OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
           "AND (:activo IS NULL OR c.activo = :activo)")
    Page<Categoria> findByFiltros(String codigo, String nombre, Boolean activo, Pageable pageable);
}