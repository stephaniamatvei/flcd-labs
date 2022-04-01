package com.flcd.labs.lab4;

// STEP 3 - Eliminate Unproductive Symbols

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StepThree extends StepTwo {

    public StepThree(File grammarInput) throws FileNotFoundException {
        super(grammarInput);
    }

    protected void removeUnproductiveSymbols() {
        List<String> unproductive = new ArrayList<>();
        for (String nonTerminal: this.grammar.getNonTerminals()) {
            if (!this.grammar.getLeft().contains(nonTerminal)) {
                unproductive.add(nonTerminal);
            }
        }
        int i = 0;
        for (List<String> words: this.grammar.getRight()) {
            i += 1;
            List<String> removals = new ArrayList<>(); // word with non-productive non-terminal of some terminal
            for (String word: words) {
                for (char c: word.toCharArray()) {
                    if (unproductive.contains(String.valueOf(c))) {
                        removals.add(word);
                        break;
                    }
                }
            }
            this.grammar.getRight().get(i - 1).removeAll(removals);
        }
        List<Integer> removals = new ArrayList<>();
        int k = 0;
        for (String left: this.grammar.getLeft()) { // we might remove all word for some non-terminal
            if (this.grammar.rules(left).size() == 0) { // and now can remove this non-terminal too
                removals.add(k);
            }
            k += 1;
        }
        k = 0;
        for (int removal: removals) {
            this.grammar.getNonTerminals().remove(this.grammar.getLeft().get(removal - k));
            this.grammar.getRight().remove(removal - k);
            this.grammar.getLeft().remove(removal - k);
            k += 1;
        }
        if (unproductive.size() != 0) {
            System.out.print("\n\033[1mSTEP 3: Removing Unproductive Symbols\033[0m\nUnproductive symbols = { ");
            this.grammar.getNonTerminals().removeAll(unproductive);
            for (String s: unproductive) {
                System.out.print(s + " ");
            }
            System.out.println("}\n");
            this.grammar.print();
        } else {
            System.out.println("\n\033[1mSTEP 3: Removing Unproductive Symbols\033[0m\nNo unproductive symbols :-)");
        }
    }
}