package com.josemorenodevs.modernswitch.service;

import org.springframework.stereotype.Service;
import com.josemorenodevs.modernswitch.model.TipoCliente;

@Service
public class DescuentoService {

    /**
     * Expresión switch exhaustiva con enum (sin necesidad de default).
     */
    public double calcularDescuento(TipoCliente tipoCliente) {
        return switch (tipoCliente) {
            case VIP, FUNDADOR -> 0.20;
            case REGULAR -> 0.05;
            case NUEVO -> 0.10;
        };
    }
}