package com.josemorenodevs.optional.model;

public class Cliente {

    private Direccion direccion;

    public Cliente() {
    }

    public Cliente(Direccion direccion) {
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
