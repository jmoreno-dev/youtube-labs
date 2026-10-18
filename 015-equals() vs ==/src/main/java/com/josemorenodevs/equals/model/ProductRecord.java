package com.josemorenodevs.equals.model;

/**
 * [6. HOW WE DEFINE EQUALITY — 4:50 - Option 3: Records]
 * 
 * Records automatically generate equals() and hashCode()
 * based on all components (id and name).
 */
public record ProductRecord(Long id, String name) {
}