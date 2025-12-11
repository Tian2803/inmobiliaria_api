package com.example.inmobiliaria.services.producto;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.example.inmobiliaria.dto.producto.ProductoCreateRequest;
import com.example.inmobiliaria.dto.producto.ProductoResponse;
import com.example.inmobiliaria.dto.producto.ProductoUpdateRequest;
import com.example.inmobiliaria.exceptions.CustomException;
import com.example.inmobiliaria.mappers.ProductoMapper;
import com.example.inmobiliaria.models.Producto;
import com.example.inmobiliaria.repositories.CategoriaRepository;
import com.example.inmobiliaria.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
            ProductoMapper mapper) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoMapper = mapper;
    }

    @Override
    public ProductoResponse registrar(ProductoCreateRequest request) {
        if (productoRepository.existsByCodigo(request.codigo())) {
            throw new CustomException("El código del producto ya existe.", HttpStatus.BAD_REQUEST);
        }

        if (productoRepository.existsByNombre(request.nombre())) {
            throw new CustomException("El nombre del producto ya existe.", HttpStatus.BAD_REQUEST);
        }

        Producto producto = productoMapper.toEntity(request);
        producto.setCategoria(categoriaRepository.findById(request.categoria())
        .orElseThrow(() -> new CustomException("Categoría no encontrada", HttpStatus.NOT_FOUND)));
        
        Producto saved = productoRepository.save(producto);
        return productoMapper.toResponse(saved);
    }

    @Override
    public Page<ProductoResponse> listarProductos(Pageable pageable) {
        return productoRepository.findAll(pageable).map(productoMapper::toResponse);
    }

    @Override
    public void actualizar(Integer id, ProductoUpdateRequest request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Producto no encontrado", HttpStatus.NOT_FOUND));

        productoMapper.update(request, producto);
        productoRepository.save(producto);
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Producto no encontrado", HttpStatus.NOT_FOUND));
        productoRepository.delete(producto);
    }

    @Override
    public Page<ProductoResponse> filtrarProductos(String nombre, String marca, Float precioMin, Float precioMax,
            Integer categoriaId, Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filtrarProductos'");
    }

    @Override
    public Object obtenerResumen() {
        long totalProductos = productoRepository.count();
        // Agregar más estadísticas si es necesario
        return Map.of("totalProductos", totalProductos);
    }
}
