package com.franquicias.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franquicias.api.dto.FranquiciaRequest;
import com.franquicias.api.dto.ProductoMaxStockResponse;
import com.franquicias.api.dto.ProductoRequest;
import com.franquicias.api.dto.StockRequest;
import com.franquicias.api.dto.SucursalRequest;
import com.franquicias.api.model.Franquicia;
import com.franquicias.api.service.FranquiciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    private final FranquiciaService service;

    public FranquiciaController(FranquiciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Franquicia> crear(
            @Valid @RequestBody FranquiciaRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addranquicia(dto));
    }

    @PostMapping("/{id}/sucursales")
    public ResponseEntity<Franquicia> addSucursal(
            @PathVariable Long id,
            @Valid @RequestBody SucursalRequest dto) {
        return ResponseEntity.ok(service.addSucursal(id, dto));
    }

    @PostMapping("/{id}/sucursales/{sucId}/productos")
    public ResponseEntity<Franquicia> addProducto(
            @PathVariable Long id,
            @PathVariable Long sucId,
            @Valid @RequestBody ProductoRequest dto) {
        return ResponseEntity.ok(service.addProducto(id, sucId, dto));
    }

    @DeleteMapping("/{id}/sucursales/{sucId}/productos/{prodId}")
    public ResponseEntity<Franquicia> deleteProducto(
            @PathVariable Long id,
            @PathVariable Long sucId,
            @PathVariable Long prodId) {
        return ResponseEntity.ok(service.deleteProducto(id, sucId, prodId));
    }

    @PostMapping("/{id}/sucursales/{sucId}/productos/{prodId}/stock")
    public ResponseEntity<Franquicia> actualizarStock(
            @PathVariable Long id,
            @PathVariable Long sucId,
            @PathVariable Long prodId,
            @Valid @RequestBody StockRequest dto) {
        return ResponseEntity.ok(service.actualizarStock(id, sucId, prodId, dto));
    }

    @GetMapping("/{id}/top-productos")
    public ResponseEntity<List<ProductoMaxStockResponse>> topProductos(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getProductoMaxStockPorSucursal(id));
    }

}
