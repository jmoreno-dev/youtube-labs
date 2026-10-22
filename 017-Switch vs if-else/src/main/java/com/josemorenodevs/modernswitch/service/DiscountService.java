package com.josemorenodevs.modernswitch.service;

import org.springframework.stereotype.Service;
import com.josemorenodevs.modernswitch.model.CustomerType;

@Service
public class DiscountService {

    /**
     * Exhaustive switch expression with enum (no default needed).
     */
    public double calculateDiscount(CustomerType customerType) {
        return switch (customerType) {
            case VIP, FOUNDER -> 0.20;
            case REGULAR -> 0.05;
            case NEW -> 0.10;
        };
    }
}