package com.flcd.labs.lab1;

public class Value {
    public String terminal;
    public String nonTerminal;

    public Value(String terminal, String nonTerminal) {
        this.terminal = terminal;
        this.nonTerminal = nonTerminal;
    }

    public Value(String terminal) {
        this.terminal = terminal;
        this.nonTerminal = "$";
    }
}
