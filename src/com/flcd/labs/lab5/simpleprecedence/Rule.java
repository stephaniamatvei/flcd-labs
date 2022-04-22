package com.flcd.labs.lab5.simpleprecedence;

public class Rule {
    private final char key;
    private final String value;

    public Rule(char key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Rule createRule(String str) {
        char key;
        String value;
        key = str.charAt(0);
        value = str.substring(3);
        return new Rule(key, value);
    }

    public char getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
