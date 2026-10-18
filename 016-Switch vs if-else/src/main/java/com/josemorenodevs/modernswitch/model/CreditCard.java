package com.josemorenodevs.modernswitch.model;

public record CreditCard(String number, double fee) implements Payment {
}