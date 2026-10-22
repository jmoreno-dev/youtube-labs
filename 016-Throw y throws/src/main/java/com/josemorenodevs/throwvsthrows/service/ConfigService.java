package com.josemorenodevs.throwvsthrows.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ConfigService {

    /**
     * Opción 1: Gestionar la excepción en el propio método con try-catch.
     * Adecuado si devolver un valor por defecto o fallback no oculta un problema crítico.
     */
    public String readConfig() {
        try {
            return Files.readString(Path.of("config.txt"));
        } catch (IOException e) {
            return "Configuración no disponible";
        }
    }

    /**
     * Opción 2: Propagar la excepción hacia capas superiores con throws.
     * Deja que quien invoca el servicio (o un GlobalExceptionHandler) decida cómo actuar.
     */
    public String readConfigPropagating() throws IOException {
        return Files.readString(Path.of("config.txt"));
    }

    /**
     * Validación de negocio con throw y excepción no comprobada (unchecked).
     * No requiere 'throws IllegalArgumentException' en la firma.
     */
    public void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
    }
}
