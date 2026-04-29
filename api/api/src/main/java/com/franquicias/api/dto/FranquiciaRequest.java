package com.franquicias.api.dto;

import jakarta.validation.constraints.NotBlank;

public class FranquiciaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
