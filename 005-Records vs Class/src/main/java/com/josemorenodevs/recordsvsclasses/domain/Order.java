package com.josemorenodevs.recordsvsclasses.domain;

public class Order {

    private final Long id;
    private OrderStatus status;

    public Order(Long id) {
        this.id = id;
        this.status = OrderStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void confirm() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending orders can be confirmed"
            );
        }

        status = OrderStatus.CONFIRMED;
    }

    public void ship() {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException(
                    "Only confirmed orders can be shipped"
            );
        }

        status = OrderStatus.SHIPPED;
    }

    public void cancel() {
        if (status == OrderStatus.SHIPPED) {
            throw new IllegalStateException(
                    "Shipped orders cannot be cancelled"
            );
        }

        status = OrderStatus.CANCELLED;
    }
}
