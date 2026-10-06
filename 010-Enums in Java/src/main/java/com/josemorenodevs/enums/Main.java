package com.josemorenodevs.enums;

import com.josemorenodevs.enums.domain.Order;
import com.josemorenodevs.enums.domain.OrderStatus;

public class Main {

    public static void main(String[] args) {
        // [HOOK y 1. ¿Qué es un enum?]
        OrderStatus status = OrderStatus.PENDING;
        System.out.println("Estado: " + status);

        // [3. Comparar enums con ==]
        Order order = new Order(OrderStatus.PENDING);
        if (order.getStatus() == OrderStatus.PENDING) {
            System.out.println("El pedido está pendiente");
        }

        // [4. Switch con enums]
        switch (order.getStatus()) {
            case PENDING -> System.out.println("Pendiente");
            case CONFIRMED -> System.out.println("Confirmado");
            case SHIPPED -> System.out.println("Enviado");
            case DELIVERED -> System.out.println("Entregado");
            case CANCELLED -> System.out.println("Cancelado");
        }

        // [5. Los enums pueden tener datos]
        System.out.println("Descripción: " + OrderStatus.PENDING.getDescription());

        // [6. values() y valueOf()]
        for (OrderStatus s : OrderStatus.values()) {
            System.out.println(s);
        }

        OrderStatus fromString = OrderStatus.valueOf("PENDING");
        System.out.println("valueOf: " + fromString);

        // [7. Error habitual: ordinal()]
        System.out.println("ordinal: " + OrderStatus.PENDING.ordinal());
    }
}
