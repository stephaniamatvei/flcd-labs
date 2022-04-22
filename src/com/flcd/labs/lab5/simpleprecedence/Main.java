package com.flcd.labs.lab5.simpleprecedence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static ArrayList<Rule> ruleList = new ArrayList<>();
    static ArrayList<Rule> firstList = new ArrayList<>();
    static ArrayList<Rule> lastList = new ArrayList<>();
    static HashSet<Character> nonTerminalSymbols= new HashSet<>();
    static HashSet<Character> symbols= new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {
        String path = "/Users/stephaniamatvei/IdeaProjects/flcd-labs/src/com/flcd/labs/lab5/simpleprecedence/input.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            if (line.startsWith("V")) {
                for(int i = line.indexOf('[')+1; i < line.length() - 1; i++) {
                    if(line.charAt(i) == ',') continue;
                    symbols.add(line.charAt(i));
                }
                symbols.add('$');
            } else {
                Rule rule = Rule.createRule(line);
                nonTerminalSymbols.add(rule.getKey());
                symbols.add(rule.getKey());
                ruleList.add(rule);
            }
        }
        scanner.close();

        System.out.print("\033[1mIntroduce the word to be analysed by the parser (ex: abacdfbfe) = \033[0m");
        Scanner scanIn = new Scanner(System.in);
        String input = scanIn.nextLine();
        scanIn.close();

        FirstLast listOfFirst = new FirstLast(ruleList,nonTerminalSymbols);
        firstList = listOfFirst.createFirstList();
        FirstLast listOfLast = new FirstLast(ruleList,nonTerminalSymbols);
        lastList = listOfLast.createLastList();

        System.out.println("\n\033[1mFIRST:\033[0m");
        for(Rule firstList: firstList) {
            System.out.println(firstList.getKey()+"->"+firstList.getValue());
        }

        System.out.println("\n\033[1mLAST:\033[0m");
        for(Rule lastList: lastList) {
            System.out.println(lastList.getKey()+ "->" + lastList.getValue());
        }

        ArrayList<Character> sym = new ArrayList<>(symbols);

        Matrix matrix = new Matrix(ruleList, firstList, lastList,sym);
        char[][] mainMatrix = matrix.createMatrix();

        System.out.println("\n\033[1mMATRIX:\033[0m");
        for (char[] chars : mainMatrix) {
            for (int j = 0; j < mainMatrix.length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }

        input = "$" + input + "$";
        System.out.println("\n\033[1mPARSING THE INPUT:\033[0m");
        Parser parser = new Parser(ruleList, mainMatrix, sym, input);
        System.out.println("\n\033[1mRESULT:\033[0m " + parser.parsing());
    }
}
