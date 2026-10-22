package com.josemorenodevs.optional.demo;

import java.util.Optional;

import com.josemorenodevs.optional.entity.User;
import com.josemorenodevs.optional.model.Address;
import com.josemorenodevs.optional.model.Customer;
import com.josemorenodevs.optional.model.Order;

/**
 * "Stop using if != null in Java: master Optional"
 */
public class OptionalDemo {

    // 1. Three levels of nested if statements to reach a property
    public String getCityWithIfs(Order order) {
        if (order != null) {
            if (order.getCustomer() != null) {
                if (order.getCustomer().getAddress() != null) {
                    return order.getCustomer().getAddress().getCity();
                }
            }
        }
        return "Unknown city";
    }

    // 2. The equivalent antipattern with Optional
    public User antipatternHook(Optional<User> user) {
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    // =========================================================================
    // [1. WHAT PROBLEM DOES OPTIONAL SOLVE — 0:35]
    // =========================================================================

    // Simulating legacy DAO that returns null when user is not found
    public User findUser(Long id) {
        return null; // userDao.findUser(id);
    }

    public void demoNullPointerException() {
        User user = findUser(1L);

        // Danger of NullPointerException if user is null or getEmail() is null!
        String email = user.getEmail().toLowerCase();
        System.out.println("Email: " + email);
    }

    // =========================================================================
    // [2. CREATING AN OPTIONAL — 1:15]
    // =========================================================================

    public void createOptional() {
        // 1. Empty result
        Optional<User> emptyUser = Optional.empty();

        // 2. Existing value (must NOT be null; throws NullPointerException if null)
        User existingUser = new User("jose", "jose@email.com");
        Optional<User> safeUser = Optional.of(existingUser);

        // 3. Value from legacy code or source that might return null
        User userFromLegacyCode = findUser(1L);
        Optional<User> nullableUser = Optional.ofNullable(userFromLegacyCode);
    }

    // =========================================================================
    // [3. THE ANTIPATTERN: ISPRESENT() + GET() — 1:55]
    // =========================================================================

    // Antipattern: asking if present and extracting immediately
    public User getUserAntipattern(Optional<User> userOpt) {
        if (userOpt.isPresent()) {
            return userOpt.get();
        }
        return createDefaultUser();
    }

    // Expressing intent directly with Optional
    public User getUserRecommended(Optional<User> userOpt) {
        return userOpt.orElseGet(
                () -> createDefaultUser());
    }

    // =========================================================================
    // [4. THE TRICK WITH ORELSE AND ORELSEGET — 2:40]
    // =========================================================================

    public static User createDefaultUser() {
        System.out.println("Creating default user");
        return new User("default", "default@email.com");
    }

    public static void demoOrElseVsOrElseGet() {
        // An Optional that DOES contain a user
        Optional<User> userOpt = Optional.of(new User("jose", "jose@email.com"));

        System.out.println("--- Testing orElse() ---");
        // Java evaluates createDefaultUser() BEFORE calling orElse()
        User user1 = userOpt.orElse(
                createDefaultUser());

        System.out.println("\n--- Testing orElseGet() ---");
        // Evaluated ONLY if the Optional is empty
        User user2 = userOpt.orElseGet(
                () -> createDefaultUser());
    }

    // =========================================================================
    // [5. THE REAL POWER: MAP AND FILTER — 3:30]
    // =========================================================================

    public String getCityWithOptional(Order order) {
        return Optional.ofNullable(order)
                .map(Order::getCustomer)
                .map(Customer::getAddress)
                .map(Address::getCity)
                .filter(c -> !c.isBlank())
                .orElse("Unknown city");
    }

    // =========================================================================
    // [7. WHEN TO USE ISPRESENT() — 5:15]
    // =========================================================================

    public void demoIfPresent(Optional<User> userOpt) {
        // Conditional action when present
        userOpt.ifPresent(
                user -> sendNotification(user));

        // Action when present, fallback when absent
        userOpt.ifPresentOrElse(
                user -> sendNotification(user),
                () -> System.out.println("No user to notify"));
    }

    private void sendNotification(User user) {
        System.out.println("Sending notification to " + user.getEmail());
    }

    // =========================================================================
    // MAIN METHOD TO RUN IN INTELLIJ
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("DEMO SECTION 4: orElse vs orElseGet");
        System.out.println("=================================================");
        demoOrElseVsOrElseGet();

        System.out.println("\n=================================================");
        System.out.println("DEMO SECTION 5: map() and filter()");
        System.out.println("=================================================");
        OptionalDemo demo = new OptionalDemo();

        Order fullOrder = new Order(new Customer(new Address("Madrid")));
        Order orderWithoutAddress = new Order(new Customer(null));
        Order nullOrder = null;

        System.out.println("Full order: " + demo.getCityWithOptional(fullOrder));
        System.out.println("Order without address: " + demo.getCityWithOptional(orderWithoutAddress));
        System.out.println("Null order: " + demo.getCityWithOptional(nullOrder));
    }
}