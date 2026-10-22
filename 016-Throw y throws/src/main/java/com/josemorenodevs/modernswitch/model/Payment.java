package com.josemorenodevs.modernswitch.model;

/**
 * [6. THE LEAP IN JAVA 21: PATTERN MATCHING — 4:30]
 */
public sealed interface Payment permits CreditCard, BankTransfer {
}