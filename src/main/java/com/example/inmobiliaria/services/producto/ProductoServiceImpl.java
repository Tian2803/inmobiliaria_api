package com.example.inmobiliaria.services.producto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.example.inmobiliaria.dto.ResumenDTO;
import com.example.inmobiliaria.dto.categoria.CategoriaCantidadDTO;
import com.example.inmobiliaria.dto.producto.ProductoCreateRequest;
import com.example.inmobiliaria.dto.producto.ProductoResponse;
import com.example.inmobiliaria.dto.producto.ProductoSimpleDTO;
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
    public Page<ProductoResponse> listarProductos(String nombre, String marca, Integer categoria, Float precioMin, Float precioMax, Pageable pageable) {
        return productoRepository.findByFiltros(nombre, marca, categoria, precioMin, precioMax, pageable)
                .map(productoMapper::toResponse);
    }

    @Override
    public void actualizar(Integer id, ProductoUpdateRequest request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Producto no encontrado", HttpStatus.NOT_FOUND));

        if (request.codigo() != null && !request.codigo().equals(producto.getCodigo())
                && productoRepository.existsByCodigo(request.codigo())) {
            throw new CustomException("El código del producto ya existe.", HttpStatus.BAD_REQUEST);
        }

        if (request.nombre() != null && !request.nombre().equals(producto.getNombre())
                && productoRepository.existsByNombre(request.nombre())) {
            throw new CustomException("El nombre del producto ya existe.", HttpStatus.BAD_REQUEST);
        }
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
    public ResumenDTO obtenerResumen() {
        ResumenDTO resumen = new ResumenDTO();
        
        // Se obtiene la cantidad total de productos
        resumen.setTotalProductos(productoRepository.count());
        
        // Se obtiene la cantidad total de categorías
        resumen.setTotalCategorias(categoriaRepository.count());
        
        // Se calcula el valor total del inventario
        Double valorInventario = productoRepository.sumTotalPrecios();
        resumen.setValorInventario(valorInventario != null ? valorInventario : 0.0);
        
        // Se calcula el valor del inventario para categorías activas
        Double valorActivas = productoRepository.sumPreciosCategoriasActivas();
        resumen.setValorInventarioCategoriasActivas(valorActivas != null ? valorActivas : 0.0);
        
        // Se calcula el valor del inventario para categorías inactivas
        Double valorInactivas = productoRepository.sumPreciosCategoriasInactivas();
        resumen.setValorInventarioCategoriasInactivas(valorInactivas != null ? valorInactivas : 0.0);
        
        // Se calcula el promedio de precios
        Double precioPromedio = productoRepository.avgPrecios();
        resumen.setPrecioPromedio(precioPromedio != null ? precioPromedio : 0.0);
        
        // Se obtiene el producto más caro
        Producto masCaro = productoRepository.findProductoMasCaro();
        if (masCaro != null) {
            resumen.setProductoMasCaro(new ProductoSimpleDTO(masCaro.getNombre(), masCaro.getPrecio()));
        }
        
        // Se obtiene el producto más barato
        Producto masBarato = productoRepository.findProductoMasBarato();
        if (masBarato != null) {
            resumen.setProductoMasBarato(new ProductoSimpleDTO(masBarato.getNombre(), masBarato.getPrecio()));
        }
        
        // Lista la cantidad de productos por categoría
        List<Object[]> resultados = productoRepository.countProductosPorCategoria();
        List<CategoriaCantidadDTO> productosPorCategoria = new ArrayList<>();
        
        for (Object[] resultado : resultados) {
            String nombreCategoria = (String) resultado[0];
            Long cantidad = (Long) resultado[1];
            productosPorCategoria.add(new CategoriaCantidadDTO(nombreCategoria, cantidad));
        }
        
        resumen.setProductosPorCategoria(productosPorCategoria);
        
        return resumen;
    }
}
