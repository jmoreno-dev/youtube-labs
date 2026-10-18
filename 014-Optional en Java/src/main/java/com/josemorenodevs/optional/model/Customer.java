package com.josemorenodevs.optional.model;

public class Customer {

    private Address address;

    public Customer() {
    }

    public Customer(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}