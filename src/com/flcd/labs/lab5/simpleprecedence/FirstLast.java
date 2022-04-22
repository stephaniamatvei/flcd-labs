package com.flcd.labs.lab5.simpleprecedence;

import java.util.ArrayList;
import java.util.HashSet;

public class FirstLast {
    private final ArrayList<Rule> ruleList;
    private final static ArrayList<Rule> firstList = new ArrayList<>();
    private final static ArrayList<Rule> lastList = new ArrayList<>();
    private final HashSet<Character> nonTerminalSymbols;

    public FirstLast(ArrayList<Rule> ruleList, HashSet<Character> nonTerminalSymbols) {
        this.ruleList = ruleList;
        this.nonTerminalSymbols = nonTerminalSymbols;
    }

    public ArrayList<Rule> createFirstList() {
        HashSet <Character> firstNon = new HashSet<>();
        for (Rule rule: ruleList) {
            for (char symbol: nonTerminalSymbols) {
                if (!firstNon.contains(symbol)) {
                    if (rule.getKey() == symbol) {
                        createFirstList(symbol);
                        firstNon.add(symbol);
                        break;
                    }
                }
            }

        }
        return firstList;
    }

    private void createFirstList(char key) {
        String firstValue = "";
        for (Rule rule: ruleList) {
            if (rule.getKey() == key) {
                char first = rule.getValue().charAt(0);
                if (!firstValue.contains(Character.toString(first))) {
                    if (first >= 'A' && first <= 'Z') {
                        firstValue = firstValue.concat(Character.toString(first));
                        firstValue = search(firstValue, first);
                    } else {
                        firstValue = firstValue.concat(Character.toString(first));
                    }
                }
            }
        }
        if (!firstValue.isEmpty()) firstList.add(new Rule(key,firstValue));
    }

    private String search(String firstValue, char key) {
        for (Rule rule: ruleList) {
            if (rule.getKey() == key) {
                char first = rule.getValue().charAt(0);
                if (!firstValue.contains(Character.toString(first))) {
                    if (first >= 'A' && first <= 'Z') {
                        firstValue = firstValue.concat(Character.toString(first));
                        firstValue = search(firstValue, first);
                    } else {
                        firstValue = firstValue.concat(Character.toString(first));
                    }
                }
            }
        }
        return firstValue;
    }

    public ArrayList<Rule> createLastList() {
        HashSet <Character> lastNon = new HashSet<>();
        for (Rule rule: ruleList){
            for (char symbol: nonTerminalSymbols) {
                if (!lastNon.contains(symbol)){
                    if (rule.getKey() == symbol) {
                        createLastList(symbol);
                        lastNon.add(symbol);
                        break;
                    }
                }
            }
        }
        return lastList;
    }

    private void createLastList(char key) {
        String lastValue = "";
        for (Rule rule: ruleList) {
            if (rule.getKey() == key) {
                char last = rule.getValue().charAt(rule.getValue().length() - 1);
                if (!lastValue.contains(Character.toString(last))) {
                    if (last >= 'A' && last <= 'Z') {
                        lastValue = lastValue.concat(Character.toString(last));
                        lastValue = searchLast(lastValue, last);
                    } else {
                        lastValue = lastValue.concat(Character.toString(last));
                    }
                }
            }
        }
        if (!lastValue.isEmpty()) lastList.add(new Rule(key, lastValue));
    }

    private String searchLast(String lastValue, char key) {
        for (Rule rule: ruleList) {
            if (rule.getKey() == key) {
                char last = rule.getValue().charAt(rule.getValue().length() - 1);
                if (!lastValue.contains(Character.toString(last))) {
                    if (last >= 'A' && last <= 'Z') {
                        lastValue = lastValue.concat(Character.toString(last));
                        lastValue = search(lastValue, last);
                    } else {
                        lastValue = lastValue.concat(Character.toString(last));
                    }
                }
            }
        }
        return lastValue;
    }
}
