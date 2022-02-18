package com.flcd.labs.lab2;

public class Main {
    public static void main(String[] args) {
        // a b = alphabet
        // 3 = final state
        // 0 1 2 3 = states
        String input = """
                a b
                3
                0 1 2 3
                0 a 0
                0 b 1
                1 a 1
                1 a 2
                1 b 3
                2 a 2
                2 b 3
                """;

        NfaToDfaConverter nfa = new NfaToDfaConverter(input);
        NfaToDfaConverter dfa = nfa.convertToDfa();
    }

}
