package com.doklad.api.customers.utility.secrets;

import java.util.Random;

public interface SecretValueGenerator {
    String DICTIONARY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
    int LENGTH = 256;
    Random random = new Random();

    static String generate() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            output.append(DICTIONARY.charAt(random.nextInt(DICTIONARY.length())));
        }
        return output.toString();
    }
}
