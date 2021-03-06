package com.example;

import java.io.StreamCorruptedException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Some comments
 */
public class Test {

    // Default constructor
    public Test() {
        super();
    }

    // Main Method
    public static void main(String[] args) throws StreamCorruptedException {
        for (i = 0; i < 1; i++) {
            StringBuilder str = new StringBuilder();
            str.append("Ronny");
            try {
                String newStr = (String) Stream.of(str.toString().toCharArray()).map(s -> s.hashCode() + 1).collect(Collectors.toList()).toString();
                System.out.println("Updated string: " + newStr);
            } catch (StreamCorruptedException sce) {
                System.out.println("exception!");
                throw new StreamCorruptedException();
            }
        }
    }
}