package com.josemorenodevs.ternary;

public class Main {

    public static void main(String[] args) {
        boolean isPaid = false;
        String status = "";

        if (isPaid) {
            status = "Paid";
        } else {
            status = "Pending";
        }

        System.out.println(status);
    }
}
