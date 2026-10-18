package com.josemorenodevs.equals.model;

import lombok.EqualsAndHashCode;

/**
 * [6. CÓMO DEFINIMOS LA IGUALDAD — 4:50 - Opción 2: Lombok]
 * 
 * @EqualsAndHashCode genera automáticamente equals() y hashCode()
 * para todos los campos de la clase.
 */
@EqualsAndHashCode
public class ProductoLombok {

    private Long id;
    private String nombre;

    public ProductoLombok(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}