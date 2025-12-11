package com.example.inmobiliaria.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inmobiliaria.dto.categoria.CategoriaCreateRequest;
import com.example.inmobiliaria.dto.categoria.CategoriaResponse;
import com.example.inmobiliaria.dto.categoria.CategoriaUpdateRequest;
import com.example.inmobiliaria.services.categoria.CategoriaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/register")
    public CategoriaResponse registrar(@Valid @RequestBody CategoriaCreateRequest request) {

        return categoriaService.registrar(request);
    }

    @GetMapping("/listar")
    public Page<CategoriaResponse> listarCategorias(Pageable pageable) {
        return categoriaService.listarCategorias(pageable);
    }

    @PutMapping("/actualizar/{id}")
    public void actualizarCategoria(@PathVariable Integer id, @Valid @RequestBody CategoriaUpdateRequest request) {
        this.categoriaService.actualizarCategoria(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        this.categoriaService.eliminar(id);
    }
}
