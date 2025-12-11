package com.example.inmobiliaria.services.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.example.inmobiliaria.dto.categoria.CategoriaCreateRequest;
import com.example.inmobiliaria.dto.categoria.CategoriaResponse;
import com.example.inmobiliaria.dto.categoria.CategoriaUpdateRequest;
import com.example.inmobiliaria.exceptions.CustomException;
import com.example.inmobiliaria.mappers.CategoriaMapper;
import com.example.inmobiliaria.models.Categoria;
import com.example.inmobiliaria.repositories.CategoriaRepository;
import com.example.inmobiliaria.repositories.ProductoRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    final CategoriaRepository categoriaRepository;
    final CategoriaMapper categoriaMapper;
    final ProductoRepository productoRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
        this.productoRepository = productoRepository;
    }

    @Override
    public CategoriaResponse registrar(CategoriaCreateRequest request) {
        if (categoriaRepository.existsByCodigo(request.codigo())) {
            throw new CustomException("El código de la categoría ya existe.", HttpStatus.BAD_REQUEST);
        }

        if (categoriaRepository.existsByNombre(request.nombre())) {
            throw new CustomException("El nombre de la categoría ya existe.", HttpStatus.BAD_REQUEST);
        }

        Categoria categoria = categoriaMapper.toEntity(request);

        Categoria saved = categoriaRepository.save(categoria);
        return categoriaMapper.toResponse(saved);
    }

    @Override
    public Page<CategoriaResponse> listarCategorias(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(categoriaMapper::toResponse);
    }

    @Override
    public void actualizarCategoria(Integer id, CategoriaUpdateRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CustomException("Categoría no encontrada", HttpStatus.NOT_FOUND));
        categoriaMapper.update(request, categoria);
        categoriaRepository.save(categoria);
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CustomException("Categoría no encontrada", HttpStatus.NOT_FOUND));
        
        if (productoRepository.existsByCategoria(categoria)) {
            throw new CustomException("No se puede eliminar la categoría porque tiene productos asociados", HttpStatus.BAD_REQUEST);
        }

        categoriaRepository.deleteById(categoria.getId());
    }
}
