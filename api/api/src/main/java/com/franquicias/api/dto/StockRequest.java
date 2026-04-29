package com.franquicias.api.dto;

import jakarta.validation.constraints.NotNull;

public class StockRequest {

    @NotNull(message = "El stock es obligatorio")
    Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
