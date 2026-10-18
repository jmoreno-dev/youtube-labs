package com.josemorenodevs.equals.model;

import java.util.Objects;

/**
 * [5. LA SORPRESA: EQUALS TAMBIÉN PUEDE COMPARAR REFERENCIAS — 4:05]
 * 
 * Si no sobrescribimos equals(), hereda la implementación de Object (this == obj).
 * 
 * [6. CÓMO DEFINIMOS LA IGUALDAD — 4:50 - Opción 1]
 * Al implementar equals() y hashCode(), definimos igualdad por valor lógico.
 */
public class Producto {

    private Long id;
    private String nombre;

    public Producto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // --- OPCIÓN 1: Implementar equals() y hashCode() manualmente ---
    // (Comenta o descomenta para mostrar la diferencia durante la grabación)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto that)) return false;
        return Objects.equals(id, that.id) &&
               Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
