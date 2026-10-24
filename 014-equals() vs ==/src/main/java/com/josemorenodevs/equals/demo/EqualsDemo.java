package com.josemorenodevs.equals.demo;

import java.util.Objects;

import com.josemorenodevs.equals.model.OrderStatus;
import com.josemorenodevs.equals.model.Product;
import com.josemorenodevs.equals.model.ProductLombok;
import com.josemorenodevs.equals.model.ProductRecord;

/**
 * "equals() vs == in Java: the trap that breaks your code in production"
 */
public class EqualsDemo {

    public static void hook() {
        Integer a = 100;
        Integer b = 100;

        System.out.println(a == b); // true

        Integer x = 200;
        Integer y = 200;

        System.out.println(x == y); // typically false
    }

    // =========================================================================
    // [1. WHAT == ACTUALLY COMPARES — 0:40]
    // =========================================================================

    public static void comparePrimitivesVsObjects() {
        // Primitives: compares numerical values
        int a = 100;
        int b = 100;
        System.out.println(a == b); // true

        // Objects: compares identity (memory references)
        String s1 = new String("Java");
        String s2 = new String("Java");
        System.out.println(s1 == s2); // false
    }

    // =========================================================================
    // [2. THE MYSTERY OF INTEGER — 1:35]
    // =========================================================================

    public static void integerMystery() {
        // Autoboxing uses Integer.valueOf(), which caches instances between -128 and
        // 127
        Integer a = 100;
        Integer b = 100;
        System.out.println("100 == 100: " + (a == b)); // true (same cached instance)

        Integer x = 200;
        Integer y = 200;
        System.out.println("200 == 200 (using ==): " + (x == y)); // false (different instances)

        // Solution: compare logical value with equals()
        System.out.println("200.equals(200): " + x.equals(y)); // true
    }

    // =========================================================================
    // [3. WHAT HAPPENS WITH STRINGS — 2:25]
    // =========================================================================

    public static void strings() {
        // String Pool: identical string literals reuse the same instance
        String s1 = "Java";
        String s2 = "Java";
        System.out.println("Literals (==): " + (s1 == s2)); // true

        // new String() explicitly creates a separate object on the heap
        String s3 = new String("Java");
        System.out.println("Literal vs new String (==): " + (s1 == s3)); // false

        // Rule: compare content using equals()
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true
    }

    // =========================================================================
    // [4. REAL WORLD CASE: IDS IN SPRING BOOT — 3:15]
    // =========================================================================

    public static void springBootIds() {
        Long id1 = 128L;
        Long id2 = 128L;

        // Incorrect: compares Long references (128 is outside LongCache default range)
        System.out.println("id1 == id2: " + (id1 == id2)); // false

        // Correct and null-safe:
        System.out.println("Objects.equals(id1, id2): " + Objects.equals(id1, id2)); // true

        Long nullId1 = null;
        Long nullId2 = null;
        System.out.println("Objects.equals(null, null): " + Objects.equals(nullId1, nullId2)); // true
    }

    // =========================================================================
    // [5 and 6. CUSTOM OBJECTS AND EQUALS — 4:05 & 4:50]
    // =========================================================================

    public static void customObjects() {
        // Traditional Product class with equals() and hashCode()
        Product p1 = new Product(1L, "Keyboard");
        Product p2 = new Product(1L, "Keyboard");

        System.out.println("p1 == p2: " + (p1 == p2)); // false (different reference)
        System.out.println("p1.equals(p2): " + p1.equals(p2)); // true (when equals is overridden)

        // Option 2: Lombok @EqualsAndHashCode
        ProductLombok pl1 = new ProductLombok(1L, "Keyboard");
        ProductLombok pl2 = new ProductLombok(1L, "Keyboard");
        System.out.println("Lombok pl1.equals(pl2): " + pl1.equals(pl2)); // true

        // Option 3: Record (automatically generates equals and hashCode)
        ProductRecord pr1 = new ProductRecord(1L, "Keyboard");
        ProductRecord pr2 = new ProductRecord(1L, "Keyboard");
        System.out.println("Record pr1.equals(pr2): " + pr1.equals(pr2)); // true
    }

    // =========================================================================
    // [7. WHEN TO USE == WITH OBJECTS — 5:35]
    // =========================================================================

    public static void enums() {
        OrderStatus status = OrderStatus.SENT;

        // With enums, '==' is idiomatic, safe, and null-friendly
        if (status == OrderStatus.SENT) {
            System.out.println("Status is SENT (comparison with ==)");
        }

        OrderStatus nullStatus = null;
        // Does NOT throw NullPointerException: safely evaluates to false
        System.out.println("null == SENT: " + (nullStatus == OrderStatus.SENT)); // false
    }

    // =========================================================================
    // MAIN METHOD TO RUN IN INTELLIJ
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== [HOOK — 0:00] ===");
        hook();

        System.out.println("\n=== [1. PRIMITIVES VS OBJECTS — 0:40] ===");
        comparePrimitivesVsObjects();

        System.out.println("\n=== [2. THE MYSTERY OF INTEGER — 1:35] ===");
        integerMystery();

        System.out.println("\n=== [3. STRINGS AND STRING POOL — 2:25] ===");
        strings();

        System.out.println("\n=== [4. IDS IN SPRING BOOT — 3:15] ===");
        springBootIds();

        System.out.println("\n=== [5 and 6. PRODUCT AND EQUALS — 4:05] ===");
        customObjects();

        System.out.println("\n=== [7. ENUMS WITH == — 5:35] ===");
        enums();
    }
}