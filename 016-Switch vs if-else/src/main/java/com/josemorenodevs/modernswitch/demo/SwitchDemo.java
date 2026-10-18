package com.josemorenodevs.modernswitch.demo;

import com.josemorenodevs.modernswitch.model.Pago;
import com.josemorenodevs.modernswitch.model.Tarjeta;
import com.josemorenodevs.modernswitch.model.TipoCliente;
import com.josemorenodevs.modernswitch.model.Transferencia;

/**
 * Clase demostrativa para la grabación del vídeo:
 * "Switch vs if/else en Java: domina el Switch moderno"
 */
public class SwitchDemo {

    // =========================================================================
    // [HOOK — 0:00]
    // =========================================================================

    // Switch clásico con riesgo de fall-through
    public static double descuentoClasico(TipoCliente tipoCliente) {
        double descuento;

        switch (tipoCliente) {
            case VIP:
                descuento = 0.20;
                break;
            case REGULAR:
                descuento = 0.05;
                break;
            default:
                descuento = 0.0;
        }

        return descuento;
    }

    // [ELIMINAR EL break DEL CASE VIP]: Provoca fallo silencioso (fall-through)
    public static double descuentoClasicoSinBreak(TipoCliente tipoCliente) {
        double descuento;

        switch (tipoCliente) {
            case VIP:
                descuento = 0.20;
                // ¡Falta el break! Continúa hacia case REGULAR
            case REGULAR:
                descuento = 0.05;
                break;
            default:
                descuento = 0.0;
        }

        return descuento;
    }

    // Switch moderno como expresión
    public static double descuentoModerno(TipoCliente tipoCliente) {
        return switch (tipoCliente) {
            case VIP -> 0.20;
            case REGULAR -> 0.05;
            default -> 0.0;
        };
    }

    // =========================================================================
    // [2. PRIMER CAMBIO: CASE CON FLECHAS (SENTENCIA SWITCH) — 1:25]
    // =========================================================================

    public static void ejecutarAcciones(TipoCliente tipoCliente) {
        // Sentencia switch: ejecuta acciones con flechas sin riesgo de fall-through
        switch (tipoCliente) {
            case VIP -> enviarNotificacionVIP();
            case REGULAR -> registrarOperacion();
            default -> ignorarOperacion();
        }
    }

    private static void enviarNotificacionVIP() {
        System.out.println("Enviando notificación prioritaria a VIP");
    }

    private static void registrarOperacion() {
        System.out.println("Registrando operación habitual");
    }

    private static void ignorarOperacion() {
        System.out.println("Operación omitida por defecto");
    }

    // =========================================================================
    // [3. SWITCH EXPRESSIONS: CUANDO DEVUELVE UN VALOR — 2:10]
    // =========================================================================

    public static double switchExpressionAgrupado(TipoCliente tipoCliente) {
        // Agrupación con comas y variable inmutable (final)
        final double descuento = switch (tipoCliente) {
            case VIP, FUNDADOR -> 0.20;
            case REGULAR -> 0.05;
            case NUEVO -> 0.10;
        };

        return descuento;
    }

    // =========================================================================
    // [4. ¿Y SI UNA RAMA NECESITA VARIAS INSTRUCCIONES? YIELD — 2:55]
    // =========================================================================

    public static double switchConYield(TipoCliente tipoCliente) {
        final double descuento = switch (tipoCliente) {
            case VIP, FUNDADOR -> 0.20;
            case REGULAR -> 0.05;
            case NUEVO -> {
                System.out.println("Log: Aplicando promoción de bienvenida");
                yield 0.10; // Devuelve el valor del bloque
            }
        };

        return descuento;
    }

    // =========================================================================
    // [5. EL TRUCO CON ENUMS: ¿NECESITAS DEFAULT? — 3:40]
    // =========================================================================

    public static double switchExhaustivoSinDefault(TipoCliente tipoCliente) {
        // Al cubrir todas las constantes del enum, omitir 'default' es una decisión
        // deliberada para que el compilador avise si se añade un valor nuevo al enum.
        return switch (tipoCliente) {
            case VIP, FUNDADOR -> 0.20;
            case REGULAR -> 0.05;
            case NUEVO -> 0.10;
        };
    }

    // =========================================================================
    // [6. EL SALTO DE JAVA 21: PATTERN MATCHING — 4:30]
    // =========================================================================

    // Antes de Java 21: encadenar if / instanceof
    public static String reciboTradicional(Pago pago) {
        if (pago instanceof Tarjeta t && t.comision() > 10) {
            return "Tarjeta con sobrecargo: " + t.numero();
        } else if (pago instanceof Tarjeta t) {
            return "Tarjeta estándar: " + t.numero();
        } else if (pago instanceof Transferencia tr) {
            return "IBAN: " + tr.iban();
        } else if (pago == null) {
            return "Pago no disponible";
        }
        return "Método no soportado";
    }

    // En Java 21: Pattern Matching para switch con 'when' y 'case null'
    public static String reciboConPatternMatching(Pago pago) {
        return switch (pago) {
            case null -> "Pago no disponible";

            case Tarjeta t when t.comision() > 10 ->
                "Tarjeta con sobrecargo: " + t.numero();

            case Tarjeta t ->
                "Tarjeta estándar: " + t.numero();

            case Transferencia tr ->
                "IBAN: " + tr.iban();

            default -> "Método no soportado";
        };
    }

    // =========================================================================
    // [7. ¿CUÁNDO UTILIZAR IF, SWITCH O POLIMORFISMO? — 5:25]
    // =========================================================================

    public static boolean esAdultoActivo(int edad, boolean activo) {
        // IF/ELSE es la opción natural para rangos y condiciones booleanas:
        if (edad >= 18 && activo) {
            return true;
        }
        return false;
    }

    // =========================================================================
    // MÉTODO MAIN PARA EJECUTAR EN INTELLIJ
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=== [HOOK — 0:00]: EL PELIGRO DEL FALL-THROUGH ===");
        System.out.println("Descuento VIP (con break): " + descuentoClasico(TipoCliente.VIP));
        System.out.println("Descuento VIP (¡sin break!): " + descuentoClasicoSinBreak(TipoCliente.VIP));
        System.out.println("Descuento VIP (Switch moderno): " + descuentoModerno(TipoCliente.VIP));

        System.out.println("\n=== [2. CASE CON FLECHAS — 1:25] ===");
        ejecutarAcciones(TipoCliente.VIP);

        System.out.println("\n=== [4. USO DE YIELD — 2:55] ===");
        System.out.println("Descuento NUEVO: " + switchConYield(TipoCliente.NUEVO));

        System.out.println("\n=== [6. JAVA 21: PATTERN MATCHING — 4:30] ===");
        Pago tarjetaCara = new Tarjeta("4500-1234-5678-9010", 15.0);
        Pago tarjetaNormal = new Tarjeta("4500-9876-5432-1098", 2.0);
        Pago transferencia = new Transferencia("ES9121000418450200051332");
        Pago pagoNulo = null;

        System.out.println(reciboConPatternMatching(tarjetaCara));
        System.out.println(reciboConPatternMatching(tarjetaNormal));
        System.out.println(reciboConPatternMatching(transferencia));
        System.out.println(reciboConPatternMatching(pagoNulo));
    }
}