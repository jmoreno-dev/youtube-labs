package com.josemorenodevs.equals.model;

import lombok.EqualsAndHashCode;

/**
 * [6. HOW WE DEFINE EQUALITY — 4:50 - Option 2: Lombok]
 * 
 * @EqualsAndHashCode automatically generates equals() and hashCode()
 * for all non-static fields.
 */
@EqualsAndHashCode
public class ProductLombok {

    private Long id;
    private String name;

    public ProductLombok(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}