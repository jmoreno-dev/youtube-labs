package com.josemorenodevs.equals.model;

import lombok.EqualsAndHashCode;

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