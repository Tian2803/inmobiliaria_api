package com.example.inmobiliaria.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.inmobiliaria.models.Producto;
import com.example.inmobiliaria.models.Categoria;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Boolean existsByCodigo(String codigo);
    Boolean existsByNombre(String nombre);
    Boolean existsByCategoria(Categoria categoria);

    @Query("SELECT p FROM Producto p WHERE p.categoria.activo = true " +
           "AND (:nombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
           "AND (:marca IS NULL OR LOWER(p.marca) LIKE LOWER(CONCAT('%', :marca, '%'))) " +
           "AND (:categoria IS NULL OR p.categoria.id = :categoria) " +
           "AND (:precioMin IS NULL OR p.precio >= :precioMin) " +
           "AND (:precioMax IS NULL OR p.precio <= :precioMax)")
    Page<Producto> findByFiltros(
        @Param("nombre") String nombre,
        @Param("marca") String marca,
        @Param("categoria") Integer categoria,
        @Param("precioMin") Float precioMin,
        @Param("precioMax") Float precioMax,
        Pageable pageable
    );

    @Query("SELECT SUM(p.precio) FROM Producto p")
    Double sumTotalPrecios();

    @Query("SELECT SUM(p.precio) FROM Producto p WHERE p.categoria.activo = true")
    Double sumPreciosCategoriasActivas();

    @Query("SELECT SUM(p.precio) FROM Producto p WHERE p.categoria.activo = false")
    Double sumPreciosCategoriasInactivas();

    @Query("SELECT AVG(p.precio) FROM Producto p")
    Double avgPrecios();

    @Query("SELECT p FROM Producto p ORDER BY p.precio DESC LIMIT 1")
    Producto findProductoMasCaro();

    @Query("SELECT p FROM Producto p ORDER BY p.precio ASC LIMIT 1")
    Producto findProductoMasBarato();

    @Query("SELECT c.nombre, COUNT(p) FROM Producto p JOIN p.categoria c GROUP BY c.nombre")
    java.util.List<Object[]> countProductosPorCategoria();
}
