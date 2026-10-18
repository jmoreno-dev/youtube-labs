package com.josemorenodevs.optional.model;

public class Direccion {

    private String ciudad;

    public Direccion() {
    }

    public Direccion(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
