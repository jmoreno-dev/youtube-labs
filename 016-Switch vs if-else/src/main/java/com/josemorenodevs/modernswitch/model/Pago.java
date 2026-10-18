package com.josemorenodevs.modernswitch.model;

/**
 * [6. EL SALTO DE JAVA 21: PATTERN MATCHING — 4:30]
 */
public sealed interface Pago permits Tarjeta, Transferencia {
}