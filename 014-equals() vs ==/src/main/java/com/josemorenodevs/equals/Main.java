package com.josemorenodevs.equals;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        // Primitives
        int a = 100;
        int b = 100;
        System.out.println(a == b);

        // Objects
        String s1 = "Java";
        String s3 = new String("Java");
        System.out.println("s1.equals(s3): " + s1.equals(s3));

        // Null
        Long id1 = 128L;
        Long id2 = null;
        System.out.println("Objects.equals(id1, id2): " + Objects.equals(id1, id2));
    }

}
