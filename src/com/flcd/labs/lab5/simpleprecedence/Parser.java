package com.flcd.labs.lab5.simpleprecedence;

import java.util.ArrayList;
import java.util.Comparator;

public class Parser {
    private final ArrayList<Rule> ruleList;
    private final char[][] matrix;
    private final ArrayList<Character> sym;
    private final String input;

    public Parser(ArrayList<Rule> ruleList, char[][] matrix, ArrayList<Character> sym, String input) {
        this.ruleList = ruleList;
        this.matrix = matrix;
        this.sym = sym;
        this.input = input;
    }

    private StringBuilder refactorString() {
        int begin = 0;
        int end = 1;
        int split = 1;
        StringBuilder string = new StringBuilder(input);
        for (int i = 0; i < string.length()-1; i = i + 2) {
            int frs = sym.indexOf(string.charAt(begin));
            int snd = sym.indexOf(string.charAt(end));
            char spl = matrix[frs][snd];
            string.insert(split,spl);
            begin = i + 2;
            end = begin + 1;
            split = begin + 1;
        }
        return string;
    }

    public String parsing() {
        StringBuilder string = new StringBuilder(input);
        string = refactorString();
        return parsing(string, minimum(string));
    }

    private String parsing(StringBuilder input, String minimum) {
        String result;
        boolean bool = false;
        StringBuilder min = new StringBuilder();
        String[]stringArray = minimum.split("=");
        for (String s : stringArray) {
            min.append(s);
        }
        for (Rule rule: ruleList) {
            if (rule.getValue().equals(min.substring(2, min.length()-2))) {
                min.replace(1, min.length()-1, Character.toString(rule.getKey()));
                int frs = sym.indexOf(min.charAt(0));
                int snd = sym.indexOf(rule.getKey());
                min.insert(1,matrix[frs][snd]);
                frs = sym.indexOf(min.charAt(min.length()-2));
                snd = sym.indexOf(min.charAt(min.length()-1));
                min.insert(min.length()-1,matrix[frs][snd]);
                bool = true;
                break;
            }
        }
        if (!bool) result = "Word is not accepted :(";
        int index = input.indexOf(minimum);
        input.replace(index,index+ minimum.length(),min.toString());
        System.out.println(input);
        if (input.compareTo(new StringBuilder("$<S>$")) == 0) { result = "Word is accepted! :)"; }
        else { return parsing(input, minimum(input)); }
        return result;
    }

    private String minimum(StringBuilder string) {
        ArrayList<Index> indices = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '<'){
                int ind = i;
                for (int j = i + 1; j < string.length(); j++) {
                    if (string.charAt(j) == '<') {
                        i = j;
                        i--;
                        break;
                    }
                    if (string.charAt(j) == '>'){
                        indices.add(new Index(ind,j));
                    }
                }
            }

        }

        indices.sort(Comparator.comparing(Index::getLength));
        int begin = indices.get(0).getBegin() - 1;
        int end = indices.get(0).getEnd() + 2;
        return string.substring(begin, end);
    }
}
