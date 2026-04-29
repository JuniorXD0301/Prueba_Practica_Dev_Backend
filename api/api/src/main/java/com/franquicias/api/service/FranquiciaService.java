package com.franquicias.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.franquicias.api.dto.FranquiciaRequest;
import com.franquicias.api.dto.ProductoMaxStockResponse;
import com.franquicias.api.dto.ProductoRequest;
import com.franquicias.api.dto.StockRequest;
import com.franquicias.api.dto.SucursalRequest;
import com.franquicias.api.model.Franquicia;
import com.franquicias.api.model.Producto;
import com.franquicias.api.model.Sucursal;
import com.franquicias.api.repository.FranquiciaRepository;
import com.franquicias.api.repository.ProductoRepository;
import com.franquicias.api.repository.SucursalRepository;

@Service
public class FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    public FranquiciaService(FranquiciaRepository franquiciaRepository, SucursalRepository sucursalRepository,
            ProductoRepository productoRepository) {
        this.franquiciaRepository = franquiciaRepository;
        this.sucursalRepository = sucursalRepository;
        this.productoRepository = productoRepository;
    }

    public Franquicia addranquicia(FranquiciaRequest dto) {
        Franquicia franquicia = new Franquicia();
            
        franquicia.setNombre(dto.getNombre());
                
        return franquiciaRepository.save(franquicia);
    }

    public Franquicia addSucursal(Long franquiciaId, SucursalRequest dto) {
        Franquicia franquicia = findFranquicia(franquiciaId);
        Sucursal sucursal = new Sucursal();

        sucursal.setNombre(dto.getNombre());
        sucursal.setFranquicia(franquicia);

        sucursalRepository.save(sucursal);
        return findFranquicia(franquiciaId); // Retorna actualizada
    }

    public Franquicia addProducto(Long franquiciaId,
            Long sucursalId,
            ProductoRequest dto) {
        Franquicia franquicia = findFranquicia(franquiciaId);
        Sucursal sucursal = findSucursal(sucursalId, franquiciaId);

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setStock(dto.getStock());
        producto.setSucursal(sucursal);

        productoRepository.save(producto);
        return findFranquicia(franquiciaId); // Retorna actualizada
    }

    public Franquicia deleteProducto(Long franquiciaId,
            Long sucursalId,
            Long productoId) {
        findFranquicia(franquiciaId); // Valida existencia
        findSucursal(sucursalId, franquiciaId);
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoRepository.delete(producto);
        return findFranquicia(franquiciaId);
    }

    public Franquicia actualizarStock(Long franquiciaId,
            Long sucursalId,
            Long productoId,
            StockRequest dto) {
        findFranquicia(franquiciaId); // Valida existencia
        findSucursal(sucursalId, franquiciaId);
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(dto.getStock());
        productoRepository.save(producto);
        return findFranquicia(franquiciaId);
    }

    public List<ProductoMaxStockResponse> getProductoMaxStockPorSucursal(
            Long franquiciaId) {
        findFranquicia(franquiciaId); // Valida existencia
        List<Producto> productos = productoRepository
                .findTopProductosPorFranquicia(franquiciaId);
        return productos.stream()
                .map(p -> new ProductoMaxStockResponse(
                        p.getSucursal().getNombre(),
                        p.getNombre(),
                        p.getStock()))
                .collect(Collectors.toList());
    }

    private Franquicia findFranquicia(Long id) {
        return franquiciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada con id: " + id));
    }

    private Sucursal findSucursal(Long sucursalId, Long franquiciaId) {
        return sucursalRepository.findById(sucursalId)
                .filter(s -> s.getFranquicia().getId().equals(franquiciaId))
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con id: " + sucursalId));
    }
}
