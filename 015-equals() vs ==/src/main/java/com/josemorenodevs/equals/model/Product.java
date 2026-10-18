package com.josemorenodevs.equals.model;

import java.util.Objects;

/**
 * [5. THE SURPRISE: EQUALS CAN ALSO COMPARE REFERENCES — 4:05]
 * 
 * If equals() is not overridden, it inherits the implementation from Object (this == obj).
 * 
 * [6. HOW WE DEFINE EQUALITY — 4:50 - Option 1]
 * By overriding equals() and hashCode(), we define equality by logical value.
 */
public class Product {

    private Long id;
    private String name;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // --- OPTION 1: Implement equals() and hashCode() manually ---
    // (Comment or uncomment to show the difference during recording)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product that)) return false;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}