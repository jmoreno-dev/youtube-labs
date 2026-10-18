package com.josemorenodevs.equals.model;

/**
 * [6. CÓMO DEFINIMOS LA IGUALDAD — 4:50 - Opción 3: Records]
 * 
 * Los Records generan automáticamente equals() y hashCode()
 * basados en sus componentes (id y nombre).
 */
public record ProductoRecord(Long id, String nombre) {
}
