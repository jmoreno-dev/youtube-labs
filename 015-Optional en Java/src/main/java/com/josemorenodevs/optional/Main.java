package com.josemorenodevs.optional;

import com.josemorenodevs.optional.entity.User;
import com.josemorenodevs.optional.exception.UserNotFoundException;
import com.josemorenodevs.optional.model.Address;
import com.josemorenodevs.optional.model.Customer;
import com.josemorenodevs.optional.model.Order;
import com.josemorenodevs.optional.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;


@SpringBootApplication
public class Main {
    Optional<User> userOpt = Optional.empty();

    private UserService userRepository;

    public static User createDefaultUser() {
        System.out.println("Creating default user");
        return new User("default", "default@email.com");
    }

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




    public String getCityWithOptional(Order order) {
        return Optional.ofNullable(order)
                .map(Order::getCustomer)
                .map(Customer::getAddress)
                .map(Address::getCity)
                .filter(c -> !c.isBlank())
                .orElse("Unknown city");
    }


    User user = userOpt.orElseGet(
            () -> createDefaultUser()
    );

























    public static void main(String[] args) {
    }
}
