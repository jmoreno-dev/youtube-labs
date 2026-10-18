package com.josemorenodevs.equals.demo;

import java.util.Objects;

import com.josemorenodevs.equals.model.EstadoPedido;
import com.josemorenodevs.equals.model.Producto;
import com.josemorenodevs.equals.model.ProductoLombok;
import com.josemorenodevs.equals.model.ProductoRecord;

/**
 * Clase demostrativa para la grabación del vídeo:
 * "equals() vs == en Java: la trampa que rompe tu código en producción"
 */
public class EqualsDemo {

    // =========================================================================
    // [HOOK — 0:00]
    // =========================================================================

    public static void hook() {
        Integer a = 100;
        Integer b = 100;

        System.out.println(a == b); // true

        Integer x = 200;
        Integer y = 200;

        System.out.println(x == y); // normalmente false
    }

    // =========================================================================
    // [1. QUÉ COMPARA REALMENTE == — 0:40]
    // =========================================================================

    public static void compararPrimitivosVsObjetos() {
        // Primitivos: compara valores numéricos
        int a = 100;
        int b = 100;
        System.out.println(a == b); // true

        // Objetos: compara identidad (referencias en memoria)
        String s1 = new String("Java");
        String s2 = new String("Java");
        System.out.println(s1 == s2); // false
    }

    // =========================================================================
    // [2. EL MISTERIO DE INTEGER — 1:35]
    // =========================================================================

    public static void misterioInteger() {
        // Autoboxing usa Integer.valueOf() que tiene caché entre -128 y 127
        Integer a = 100;
        Integer b = 100;
        System.out.println("100 == 100: " + (a == b)); // true (misma instancia en caché)

        Integer x = 200;
        Integer y = 200;
        System.out.println("200 == 200 (con ==): " + (x == y)); // false (distintas instancias)

        // Solución: comparar valor lógico con equals()
        System.out.println("200.equals(200): " + x.equals(y)); // true
    }

    // =========================================================================
    // [3. QUÉ OCURRE CON LOS STRINGS — 2:25]
    // =========================================================================

    public static void strings() {
        // String Pool: literales idénticos reutilizan la misma instancia
        String s1 = "Java";
        String s2 = "Java";
        System.out.println("Literales (==): " + (s1 == s2)); // true

        // new String() crea explícitamente otro objeto en el heap
        String s3 = new String("Java");
        System.out.println("Literal vs new String (==): " + (s1 == s3)); // false

        // La regla: compara el contenido con equals()
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true
    }

    // =========================================================================
    // [4. EL CASO REAL: IDS EN SPRING BOOT — 3:15]
    // =========================================================================

    public static void idsSpringBoot() {
        Long id1 = 128L;
        Long id2 = 128L;

        // Incorrecto: compara referencias Long (128 está fuera de caché en Long)
        System.out.println("id1 == id2: " + (id1 == id2)); // false

        // Correcto y seguro frente a null:
        System.out.println("Objects.equals(id1, id2): " + Objects.equals(id1, id2)); // true

        Long idNulo1 = null;
        Long idNulo2 = null;
        System.out.println("Objects.equals(null, null): " + Objects.equals(idNulo1, idNulo2)); // true
    }

    // =========================================================================
    // [5 y 6. PRODUCTO: EQUALS EN CLASES PROPIAS — 4:05 & 4:50]
    // =========================================================================

    public static void objetosPersonalizados() {
        // Clase tradicional Producto con equals() y hashCode()
        Producto p1 = new Producto(1L, "Teclado");
        Producto p2 = new Producto(1L, "Teclado");

        System.out.println("p1 == p2: " + (p1 == p2));           // false (distinta referencia)
        System.out.println("p1.equals(p2): " + p1.equals(p2));   // true (si sobrescribe equals)

        // Opción 2: Lombok @EqualsAndHashCode
        ProductoLombok pl1 = new ProductoLombok(1L, "Teclado");
        ProductoLombok pl2 = new ProductoLombok(1L, "Teclado");
        System.out.println("Lombok pl1.equals(pl2): " + pl1.equals(pl2)); // true

        // Opción 3: Record (genera equals y hashCode automáticamente)
        ProductoRecord pr1 = new ProductoRecord(1L, "Teclado");
        ProductoRecord pr2 = new ProductoRecord(1L, "Teclado");
        System.out.println("Record pr1.equals(pr2): " + pr1.equals(pr2)); // true
    }

    // =========================================================================
    // [7. CUÁNDO SÍ UTILIZAR == CON OBJETOS — 5:35]
    // =========================================================================

    public static void enums() {
        EstadoPedido estado = EstadoPedido.ENVIADO;

        // Con enums, '==' es idiomático, seguro e insensible a null
        if (estado == EstadoPedido.ENVIADO) {
            System.out.println("Estado es ENVIADO (comparación con ==)");
        }

        EstadoPedido estadoNulo = null;
        // No lanza NullPointerException: devuelve false directamente
        System.out.println("null == ENVIADO: " + (estadoNulo == EstadoPedido.ENVIADO)); // false
    }

    // =========================================================================
    // MÉTODO MAIN PARA EJECUTAR EN INTELLIJ
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== [HOOK — 0:00] ===");
        hook();

        System.out.println("\n=== [1. PRIMITIVOS VS OBJETOS — 0:40] ===");
        compararPrimitivosVsObjetos();

        System.out.println("\n=== [2. MISTERIO DE INTEGER — 1:35] ===");
        misterioInteger();

        System.out.println("\n=== [3. STRINGS Y STRING POOL — 2:25] ===");
        strings();

        System.out.println("\n=== [4. IDS EN SPRING BOOT — 3:15] ===");
        idsSpringBoot();

        System.out.println("\n=== [5 y 6. PRODUCTO Y EQUALS — 4:05] ===");
        objetosPersonalizados();

        System.out.println("\n=== [7. ENUMS CON == — 5:35] ===");
        enums();
    }
}
