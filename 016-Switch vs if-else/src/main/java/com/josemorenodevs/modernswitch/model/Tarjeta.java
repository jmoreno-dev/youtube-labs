package com.josemorenodevs.modernswitch.model;

public record Tarjeta(String numero, double comision) implements Pago {
}