package com.flcd.labs.lab2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NfaToDfaConverter {
    private final HashMap<String, HashMap<String, String>> transitions;
    private final String[] alphabet;
    private final String endState;

    public NfaToDfaConverter(HashMap<String, HashMap<String, String>> transitions, String[] alphabet, String endState) {
        this.transitions = transitions;
        this.alphabet = alphabet;
        this.endState = endState;
    }

    public NfaToDfaConverter(String input) {
        String[] lines = input.split("[\\r\\n]+");
        String[] states = lines[2].trim().split(" ");

        alphabet = lines[0].trim().split(" ");
        endState = lines[1].trim();
        transitions = new HashMap<>();

        for (String state : states)
            transitions.put(state, (HashMap<String, String>) Stream.of(alphabet).collect(Collectors.toMap(a -> a, a -> "")));

        for (int i = 3; i < lines.length; i++) {
            String[] line = lines[i].trim().split(" ");
            String state = transitions.get(line[0]).get(line[1]) + line[2];
            transitions.get(line[0]).replace(line[1], state);
        }
    }

    public NfaToDfaConverter convertToDfa() {
        HashMap<String, HashMap<String, String>> dfaTransitions = new HashMap<>();

        dfaTransitions.put("0", transitions.get("0"));

        System.out.println("NFA:");
        printTransitions(transitions);
        System.out.println("NFA to DFA:");
        printTransitions(dfaTransitions);

        while(true) {
            HashMap<String, HashMap<String, String>> newTransitions = getNewTransitions(dfaTransitions);
            if (newTransitions.isEmpty()) break;
            dfaTransitions.putAll(newTransitions);
            printTransitions(dfaTransitions);
        }
        return new NfaToDfaConverter(dfaTransitions, alphabet, endState);
    }

    private HashMap<String, HashMap<String, String>> getNewTransitions(HashMap<String, HashMap<String, String>> dfaTransitions) {
        HashMap<String, HashMap<String, String>> newTransitions = new HashMap<>();

        dfaTransitions.forEach((state, transitions) -> transitions.values().forEach((currentState) -> {
            if (checkForNewState(dfaTransitions, currentState)) {
                newTransitions.put(currentState, new HashMap<>());
                for (String transitionVariable : alphabet)
                    newTransitions.get(currentState).put(transitionVariable, getNewState(transitionVariable, currentState));
            }}));

        return newTransitions;
    }

    private boolean checkForNewState(HashMap<String, HashMap<String, String>> transitions, String currentState) {
        return !transitions.containsKey(currentState) && !Objects.equals(currentState, "");
    }

    private String sortNewState(StringBuilder generatedString) {
        SortedSet<String> sortedGeneratedState = new TreeSet<>(Arrays.asList(generatedString.toString().split("")));
        return sortedGeneratedState.stream().reduce("", String::concat);
    }

    private String getNewState(String transitionVariable, String currentState) {
        StringBuilder generatedString = new StringBuilder();
        String[] nestedStates = currentState.split("");

        for (String nestedState : nestedStates)
            generatedString.append(transitions.get(nestedState).get(transitionVariable));

        return sortNewState(generatedString);
    }

    private void printTransitions(HashMap<String, HashMap<String, String>> rows) {
        rows.forEach((state, transitions) -> {
            if (state.equals("0"))
                System.out.print("->" + state + "  ");
            else if (state.contains(endState))
                System.out.print("*" + state + "   ");
            else
                System.out.print(" " + state + "   ");
            System.out.println(transitions);
        });
        System.out.print("\n");
    }

}