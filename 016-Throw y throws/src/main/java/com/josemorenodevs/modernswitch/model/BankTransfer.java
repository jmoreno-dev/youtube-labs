package com.josemorenodevs.modernswitch.model;

public record BankTransfer(String iban) implements Payment {
}