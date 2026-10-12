package com.josemorenodevs.enums.domain;

public enum OrderStatus {

    PENDING("Descripcion del estado pendiente"),
    CONFIRMED("Confirmado"),
    SHIPPED("Enviado"),
    CANCELLED("Cancelado"),
    DELIVERED("Entregado");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
