package com.josemorenodevs.throwvsthrows.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ThrowVsThrowsDemo {

    // =========================================================================
    // [HOOK — 0:00]
    // =========================================================================

    // 1. throw: instrucción que lanza una instancia de excepción
    public static void throwExample() {
        throw new IllegalArgumentException("Error");
    }

    // 2. throws: cláusula en la firma del método que declara propagación
    public static void throwsExample() throws IOException {
        // ... operación que puede propagar IOException ...
        Files.readString(Path.of("config.txt"));
    }

    // =========================================================================
    // [1. QUÉ HACE REALMENTE THROW — 0:35]
    // =========================================================================

    public static void validateAge(int age) {
        if (age < 0) {
            // throw: lanza explícitamente una nueva excepción
            // Flujo normal se interrumpe y Java busca un bloque catch
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
    }

    // =========================================================================
    // [2. QUÉ HACE THROWS — 1:25]
    // =========================================================================

    // throws IOException: forma parte del contrato del método.
    // Avisa a quien llame que debe capturar o volver a declarar esta excepción.
    public static String readFile() throws IOException {
        return Files.readString(Path.of("config.txt"));
    }

    // =========================================================================
    // [3. EL MATIZ IMPORTANTE: CHECKED VS UNCHECKED — 2:15]
    // =========================================================================

    // Checked Exception (IOException):
    // El compilador exige try-catch O declararla con throws en la firma.
    public static String readCheckedExample() throws IOException {
        return Files.readString(Path.of("config.txt"));
    }

    // Unchecked Exception (IllegalArgumentException / RuntimeException):
    // El compilador NO obliga a declararla con throws ni a capturarla.
    public static void checkUncheckedExample(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Edad inválida");
        }
    }

    // =========================================================================
    // [4. CÓMO SE RELACIONAN THROW Y THROWS — 3:10]
    // =========================================================================

    // Ambos colaboran en el mismo método con responsabilidades distintas:
    // - throw lanza la excepción de validación (unchecked).
    // - throws declara que la operación de I/O puede propagar una checked
    // exception.
    public static String readFile(Path path) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("La ruta no puede ser null");
        }
        return Files.readString(path);
    }

    // =========================================================================
    // [5. EL CASO REAL: QUÉ HACER EN UNA APLICACIÓN BACKEND — 4:00]
    // =========================================================================

    // Opción 1: Gestionar la excepción en el propio método con try-catch
    public static String readConfigWithCatch() {
        try {
            return Files.readString(Path.of("config.txt"));
        } catch (IOException e) {
            return "Configuración no disponible";
        }
    }

    // Opción 2: Propagar la excepción hacia quien nos llama
    public static String readConfigWithThrows() throws IOException {
        return Files.readString(Path.of("config.txt"));
    }

    // =========================================================================
    // [6. EL ERROR QUE DEBES EVITAR — 4:55]
    // =========================================================================

    // Error habitual: declarar innecesariamente una excepción no comprobada
    public static void validateAgeWithRedundantThrows(int age) throws IllegalArgumentException {
        if (age < 0) {
            throw new IllegalArgumentException("Edad inválida");
        }
    }

    // Versión limpia y recomendada (no sobrecargar la firma innecesariamente)
    public static void validateAgeClean(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Edad inválida");
        }
    }

    // =========================================================================
    // [CIERRE — 5:30]: RESUMEN Y EJECUCIÓN PRÁCTICA
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   DEMO: throw vs throws en Java 17");
        System.out.println("=================================================\n");

        // 1. Demostración de throw (Unchecked Exception)
        System.out.println("--- [1. QUÉ HACE REALMENTE THROW] ---");
        try {
            System.out.println("Validando edad positiva (25)...");
            validateAge(25);
            System.out.println("-> OK: Edad válida.");

            System.out.println("Validando edad negativa (-5)...");
            validateAge(-5);
        } catch (IllegalArgumentException ex) {
            System.out.println("-> Capturada excepción lanzada con throw: " + ex.getMessage());
        }

        // 2. Demostración de throws (Checked Exception)
        System.out.println("\n--- [2. QUÉ HACE THROWS] ---");
        try {
            String content = readFile();
            System.out.println("-> Archivo leído correctamente:\n" + content.trim());
        } catch (IOException ex) {
            System.out.println("-> Capturada IOException declarada con throws: " + ex.getMessage());
        }

        // 3. throw y throws juntos en un único método
        System.out.println("\n--- [4. THROW Y THROWS JUNTOS] ---");
        try {
            System.out.println("Llamando a readFile(null)...");
            readFile(null);
        } catch (IllegalArgumentException ex) {
            System.out.println("-> Capturada validación (throw): " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("-> Capturado error de I/O (throws): " + ex.getMessage());
        }

        // 4. Backend: Recuperación vs Propagación
        System.out.println("\n--- [5. BACKEND: RECUPERACIÓN VS PROPAGACIÓN] ---");
        System.out.println("Opción A (catch con fallback): " + readConfigWithCatch());

        try {
            System.out.println("Opción B (propagación):");
            String result = readConfigWithThrows();
            System.out.println(result.trim());
        } catch (IOException ex) {
            System.out.println("-> Propagada a capa superior: " + ex.getMessage());
        }

        // 5. Versión limpia sin throws redundante
        System.out.println("\n--- [6. VERSIÓN LIMPIA] ---");
        validateAgeClean(30);
        System.out.println("-> Validación ejecutada sin firma redundante.");

        System.out.println("\n=================================================");
        System.out.println("Resumen de las 3 Reglas:");
        System.out.println("1. 'throw'  -> Ejecuta el lanzamiento de una excepción.");
        System.out.println("2. 'throws' -> Declara excepciones en la firma del método.");
        System.out.println("3. Checked vs Unchecked determinan si el compilador te obliga.");
        System.out.println("=================================================");
    }
}
