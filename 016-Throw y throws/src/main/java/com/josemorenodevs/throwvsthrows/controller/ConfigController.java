package com.josemorenodevs.throwvsthrows.controller;

import com.josemorenodevs.throwvsthrows.service.ConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * Opción 1: El servicio capturó el error internamente y devolvió un fallback.
     */
    @GetMapping("/local-fallback")
    public ResponseEntity<String> getConfigWithFallback() {
        return ResponseEntity.ok(configService.readConfig());
    }

    /**
     * Opción 2: El servicio propaga IOException con 'throws'.
     * El controlador también propaga con 'throws IOException' hacia GlobalExceptionHandler.
     */
    @GetMapping("/propagate")
    public ResponseEntity<String> getConfigPropagating() throws IOException {
        return ResponseEntity.ok(configService.readConfigPropagating());
    }

    /**
     * Validación con throw: si age < 0, lanza IllegalArgumentException.
     * Capturado globalmente devolviendo HTTP 400.
     */
    @PostMapping("/validate-age")
    public ResponseEntity<String> validateAge(@RequestParam int age) {
        configService.validateAge(age);
        return ResponseEntity.ok("Edad válida registrada: " + age);
    }
}
