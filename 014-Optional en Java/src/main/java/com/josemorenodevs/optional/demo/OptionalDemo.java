package com.josemorenodevs.optional.demo;

import java.util.Optional;

import com.josemorenodevs.optional.entity.User;
import com.josemorenodevs.optional.model.Cliente;
import com.josemorenodevs.optional.model.Direccion;
import com.josemorenodevs.optional.model.Pedido;

/**
 * Clase demostrativa para la grabación del vídeo:
 * "Deja de usar if != null en Java: domina Optional"
 * 
 * Cada sección corresponde exactamente a los bloques del guion.
 */
public class OptionalDemo {

    // =========================================================================
    // [HOOK — 0:00]
    // =========================================================================

    // 1. Tres niveles de if anidados para llegar a un dato
    public String obtenerCiudadConIfs(Pedido pedido) {
        if (pedido != null) {
            if (pedido.getCliente() != null) {
                if (pedido.getCliente().getDireccion() != null) {
                    return pedido.getCliente().getDireccion().getCiudad();
                }
            }
        }
        return "Ciudad desconocida";
    }

    // 2. El antipatrón equivalente con Optional
    public User antipatronHook(Optional<User> user) {
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    // =========================================================================
    // [1. QUÉ PROBLEMA RESUELVE OPTIONAL — 0:35]
    // =========================================================================

    // Simulación de DAO que devuelve null cuando no encuentra el usuario
    public User findUser(Long id) {
        return null; // userDao.findUser(id);
    }

    public void demoNullPointerException() {
        User user = findUser(1L);

        // ¡Peligro de NullPointerException si user es null o getEmail() es null!
        String email = user.getEmail().toLowerCase();
        System.out.println("Email: " + email);
    }

    // =========================================================================
    // [2. CREAR UN OPTIONAL — 1:15]
    // =========================================================================

    public void crearOptional() {
        // 1. Resultado vacío
        Optional<User> userVacio = Optional.empty();

        // 2. Valor existente (no debe ser null; lanza NullPointerException si lo es)
        User existingUser = new User("jose", "jose@email.com");
        Optional<User> userSeguro = Optional.of(existingUser);

        // 3. Valor de código legado o fuente que puede devolver null
        User userFromLegacyCode = findUser(1L);
        Optional<User> userNullable = Optional.ofNullable(userFromLegacyCode);
    }

    // =========================================================================
    // [3. EL ANTIPATRÓN: ISPRESENT() + GET() — 1:55]
    // =========================================================================

    // Antipatrón: preguntar si existe y extraerlo
    public User obtenerUsuarioAntipatron(Optional<User> userOpt) {
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return createDefaultUser();
    }

    // Expresar la intención directamente con Optional
    public User obtenerUsuarioRecomendado(Optional<User> userOpt) {
        return userOpt.orElseGet(
            () -> createDefaultUser()
        );
    }

    // =========================================================================
    // [4. EL TRUCO DE ORELSE Y ORELSEGET — 2:40]
    // =========================================================================

    public static User createDefaultUser() {
        System.out.println("Creando usuario por defecto");
        return new User("default", "default@email.com");
    }

    public static void demoOrElseVsOrElseGet() {
        // Tenemos un Optional que SÍ contiene un usuario
        Optional<User> userOpt = Optional.of(new User("jose", "jose@email.com"));

        System.out.println("--- Probando orElse() ---");
        // Java evalúa createDefaultUser() ANTES de llamar a orElse()
        User user1 = userOpt.orElse(
            createDefaultUser()
        );

        System.out.println("\n--- Probando orElseGet() ---");
        // Solo se evalúa si el Optional está vacío
        User user2 = userOpt.orElseGet(
            () -> createDefaultUser()
        );
    }

    // =========================================================================
    // [5. EL PODER REAL: MAP Y FILTER — 3:30]
    // =========================================================================

    public String obtenerCiudadConOptional(Pedido pedido) {
        return Optional.ofNullable(pedido)
                .map(Pedido::getCliente)
                .map(Cliente::getDireccion)
                .map(Direccion::getCiudad)
                .filter(c -> !c.isBlank())
                .orElse("Ciudad desconocida");
    }

    // =========================================================================
    // [7. ¿Y ENTONCES NUNCA DEBERÍAMOS USAR ISPRESENT()? — 5:15]
    // =========================================================================

    public void demoIfPresent(Optional<User> userOpt) {
        // Ejecutar acción condicional si existe
        userOpt.ifPresent(
            user -> sendNotification(user)
        );

        // Si existe ejecuta una acción; si no, ejecuta otra alternativa
        userOpt.ifPresentOrElse(
            user -> sendNotification(user),
            () -> System.out.println("No hay usuario para notificar")
        );
    }

    private void sendNotification(User user) {
        System.out.println("Enviando notificación a " + user.getEmail());
    }

    // =========================================================================
    // MÉTODO MAIN PARA EJECUTAR EN INTELLIJ
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("DEMO SECCIÓN 4: orElse vs orElseGet");
        System.out.println("=================================================");
        demoOrElseVsOrElseGet();

        System.out.println("\n=================================================");
        System.out.println("DEMO SECCIÓN 5: map() y filter()");
        System.out.println("=================================================");
        OptionalDemo demo = new OptionalDemo();

        Pedido pedidoCompleto = new Pedido(new Cliente(new Direccion("Madrid")));
        Pedido pedidoSinDireccion = new Pedido(new Cliente(null));
        Pedido pedidoNulo = null;

        System.out.println("Pedido completo: " + demo.obtenerCiudadConOptional(pedidoCompleto));
        System.out.println("Pedido sin dirección: " + demo.obtenerCiudadConOptional(pedidoSinDireccion));
        System.out.println("Pedido null: " + demo.obtenerCiudadConOptional(pedidoNulo));
    }
}
