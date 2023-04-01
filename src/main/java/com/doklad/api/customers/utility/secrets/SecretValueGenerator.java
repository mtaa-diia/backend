package com.doklad.api.customers.utility.secrets;

import java.util.Random;

public class SecretValueGenerator {
    public static final String dictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
    public static final int length = 256;
    private static final Random random = new Random();

    public static String generate() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < length; i++) {
            output.append(dictionary.charAt(random.nextInt(dictionary.length())));
        }
        return output.toString();
    }
}
