package com.example.inmobiliaria.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inmobiliaria.dto.ResumenDTO;
import com.example.inmobiliaria.dto.producto.ProductoCreateRequest;
import com.example.inmobiliaria.dto.producto.ProductoResponse;
import com.example.inmobiliaria.dto.producto.ProductoUpdateRequest;
import com.example.inmobiliaria.services.producto.ProductoService;

import jakarta.validation.Valid;

@RequestMapping("/api/producto")
@RestController
@CrossOrigin(origins = "*")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/registrar")
    public ProductoResponse registrar(@Valid @RequestBody ProductoCreateRequest request) {

        return productoService.registrar(request);
    }

    @GetMapping("/listar")
    public Page<ProductoResponse> listarProductos( @RequestParam(required = false) String nombre,
    @RequestParam(required = false) String marca,
    @RequestParam(required = false) Integer categoria,
    @RequestParam(required = false) Float precioMin,
    @RequestParam(required = false) Float precioMax, Pageable pageable) {
        return productoService.listarProductos(nombre, marca, categoria, precioMin, precioMax, pageable);
    }

    @PutMapping("/actualizar/{id}")
    public void actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoUpdateRequest request) {
        this.productoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        this.productoService.eliminar(id);
    }

    @GetMapping("/resumen")
    public ResumenDTO obtenerResumen() {
        return productoService.obtenerResumen();
    }
}
