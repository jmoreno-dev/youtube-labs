package com.josemorenodevs.modernswitch.model;

/**
 * [5. THE TRICK WITH ENUMS: DO YOU NEED DEFAULT? — 3:40]
 */
public enum CustomerType {
    VIP,
    FOUNDER,
    REGULAR,
    NEW
    // COMPANY // Uncomment to demonstrate how the compiler catches missing cases in an exhaustive switch
}