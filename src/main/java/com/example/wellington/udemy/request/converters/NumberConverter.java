package com.example.wellington.udemy.request.converters;

public class NumberConverter {
    public static Double convertToDouble(String strnumber) {
        if (strnumber == null || strnumber.isEmpty())
            throw new UnsupportedOperationException("please set a numeric value");
        String number = strnumber.replace(",", ".");
        return Double.parseDouble(number);

    }

    public static boolean isNumeric(String strnumber) {
        if (strnumber == null || strnumber.isEmpty()) return false;
        String number = strnumber.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");

    }
}
