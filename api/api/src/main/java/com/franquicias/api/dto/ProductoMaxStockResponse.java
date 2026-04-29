package com.franquicias.api.dto;

public class ProductoMaxStockResponse {

    String sucursalNombre;
    String productoNombre;
    Integer stock;

    public ProductoMaxStockResponse(String sucursalNombre, String productoNombre, Integer stock) {
        this.sucursalNombre = sucursalNombre;
        this.productoNombre = productoNombre;
        this.stock = stock;
    }

    public String getSucursalNombre() {
        return sucursalNombre;
    }

    public void setSucursalNombre(String sucursalNombre) {
        this.sucursalNombre = sucursalNombre;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
