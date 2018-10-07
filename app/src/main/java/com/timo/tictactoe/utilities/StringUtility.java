package com.timo.tictactoe.utilities;

public class StringUtility {

    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();

        for (int n: numbers) {
            sNumbers.append(n);
        }

        return sNumbers.toString();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
