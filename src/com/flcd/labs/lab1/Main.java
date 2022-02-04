package com.flcd.labs.lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String regularGrammar = """
                P={
                1. S-aB
                2. B-aD
                3. B-bB
                4. D-aD
                5. D-bS
                6. B-cS
                7. D-c }""";

        Map<String, ArrayList<Value>> hashMap = regularGrammarToFiniteAutomata(regularGrammar);

        System.out.println(checkInputString("aac", "S", hashMap));

        plotGraph(hashMap);
    }


    private static Map<String, ArrayList<Value>> regularGrammarToFiniteAutomata(String regularGrammar) {
        Map<String, ArrayList<Value>> hashMap = new HashMap<>();
        int position = regularGrammar.indexOf("P");
        String[] productions = regularGrammar.substring(position).split("\\.");

        for (int i = 1; i < productions.length; i++) {
            productions[i] = productions[i].replaceAll("\\s|[0-9]|\\{|}", "");

            if (hashMap.containsKey(productions[i].charAt(0) + "")) {
                hashMap.get(productions[i].charAt(0) + "").add(new Value(productions[i].charAt(2) + "", productions[i].length() == 4 ? productions[i].charAt(3) + "" : "$"));
            } else {
                ArrayList<Value> temp = new ArrayList<>();
                temp.add(new Value(productions[i].charAt(2) + "", productions[i].length() == 4 ? productions[i].charAt(3) + "" : "$"));
                hashMap.put(productions[i].charAt(0) + "", temp);
            }
        }
        return hashMap;
    }


    private static String parseValue(String nonTerminal, char terminal, Map<String, ArrayList<Value>> hashMap) {
        if (nonTerminal == null)
            return null;

        for (Value value : hashMap.get(nonTerminal)) {
            if (value.terminal.charAt(0) == terminal) {
                return value.nonTerminal;
            }
        }
        return null;
    }


    private static String checkInputString(String input, String nonTerminal, Map<String, ArrayList<Value>> hashMap) {
        for (int i = 0; i < input.length(); i++) {
            nonTerminal = parseValue(nonTerminal, input.charAt(i), hashMap);
            if (Objects.equals(nonTerminal, "$") && (input.length() - 1) - i != 0) {
                nonTerminal = null;
                break;
            }
        }

        if (nonTerminal == null || !nonTerminal.equals("$"))
            return "Rejected";
        else
            return "Accepted";
    }


    static void plotGraph(Map<String, ArrayList<Value>> hashMap) throws IOException {
        FileWriter myWriter = new FileWriter("graph.dot");

        StringBuilder graph = new StringBuilder("""
                digraph finite_state_machine {
                    rankdir=LR;
                    size="8,5"
                    node [shape=circle];
                    Empty [shape=doublecircle];
                """);

        for (String key : hashMap.keySet()) {
            for (Value value : hashMap.get(key)) {
                graph
                        .append(key)
                        .append(" -> ")
                        .append(value.nonTerminal.equals("$") ? "Empty" : value.nonTerminal)
                        .append("[ label = \"")
                        .append(value.terminal)
                        .append("\" ];\n");
            }
        }

        graph.append("}");
        myWriter.write(graph.toString());
        myWriter.close();
    }
}
