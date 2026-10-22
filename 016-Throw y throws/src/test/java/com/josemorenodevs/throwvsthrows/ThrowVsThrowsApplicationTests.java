package com.josemorenodevs.throwvsthrows;

import com.josemorenodevs.throwvsthrows.demo.ThrowVsThrowsDemo;
import com.josemorenodevs.throwvsthrows.service.ConfigService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ThrowVsThrowsApplicationTests {

    @Autowired
    private ConfigService configService;

    @Test
    void contextLoads() {
        assertNotNull(configService);
    }

    @Test
    @DisplayName("throw lanza IllegalArgumentException cuando la edad es negativa")
    void testThrowUncheckedException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            ThrowVsThrowsDemo.validateAge(-1);
        });
        assertEquals("La edad no puede ser negativa", ex.getMessage());
    }

    @Test
    @DisplayName("throws propaga IOException al leer archivo existente o inexistente")
    void testThrowsCheckedException() throws IOException {
        String content = ThrowVsThrowsDemo.readFile();
        assertNotNull(content);
        assertTrue(content.contains("ThrowVsThrowsDemo"));
    }

    @Test
    @DisplayName("readFile con path null lanza IllegalArgumentException mediante throw")
    void testBothThrowAndThrows() {
        assertThrows(IllegalArgumentException.class, () -> {
            ThrowVsThrowsDemo.readFile(null);
        });
    }

    @Test
    @DisplayName("readFile con archivo inexistente propaga IOException mediante throws")
    void testThrowsOnMissingFile() {
        assertThrows(IOException.class, () -> {
            ThrowVsThrowsDemo.readFile(Path.of("archivo_que_no_existe_123.txt"));
        });
    }

    @Test
    @DisplayName("Backend: Servicio recupera con fallback cuando procede")
    void testServiceLocalCatch() {
        String result = configService.readConfig();
        assertNotNull(result);
    }
}
