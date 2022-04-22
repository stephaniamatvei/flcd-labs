package com.flcd.labs.lab5.simpleprecedence;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {
    private final ArrayList<Rule> ruleList;
    private final ArrayList<Rule> firstList;
    private final ArrayList<Rule> lastList;
    private final ArrayList<Character> sym;

    public Matrix(ArrayList<Rule> ruleList,
                  ArrayList<Rule> firstList,
                  ArrayList<Rule> lastList,
                  ArrayList<Character> sym) {
        this.ruleList = ruleList;
        this.firstList = firstList;
        this.lastList = lastList;
        this.sym = sym;
    }

    public char[][] createMatrix() {
        int length = sym.size();
        char[][] matrix = new char[length][length];

        for (char[] row: matrix) {
            Arrays.fill(row, ' ');
        }
        int $ = sym.indexOf('$');
        for (int i = 0; i < matrix.length; i++) {
            matrix [i][$] = '>';
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix [$][i] = '<';
        }

        firstRule(matrix, sym);
        secondRule(matrix, sym);
        thirdRule(matrix, sym);

        return matrix;
    }

    // FIRST RULE: x1 = x2
    private void firstRule(char[][] matrix, ArrayList <Character> sym) {
        System.out.println("\n\033[1mFIRST RULE (x1 = x2):\033[0m");
        for (Rule rule: ruleList) {
            if (rule.getValue().length() > 1) {
                int begin = 0;
                int end = 1;
                while (end != rule.getValue().length()) {
                    int frs = sym.indexOf(rule.getValue().charAt(begin));
                    int snd = sym.indexOf(rule.getValue().charAt(end));
                    matrix[frs][snd] = '=';
                    System.out.println(rule.getValue().charAt(begin) + "=" + rule.getValue().charAt(end));
                    begin++;
                    end++;
                }
            }
        }
    }

    /* SECOND RULE (x1 < x2):
     - Vt or Vn followed by Vn => (Vt or Vn) < FIRST(Vn) */
    private void secondRule(char[][] matrix, ArrayList <Character> sym) {
        System.out.println("\n\033[1mSECOND RULE (x1 < x2):\033[0m");
        for(Rule rule: ruleList) {
            if (rule.getValue().length() > 1) {
                int begin = 0;
                int end = 1;
                while (end != rule.getValue().length()) {
                    char f = rule.getValue().charAt(begin);
                    char s = rule.getValue().charAt(end);
                    if (((f >= 'a' && f <= 'z') || (f >= 'A' && f <= 'Z')) && (s >= 'A' && s <= 'Z')) {
                        int frs = sym.indexOf(f);
                        for (Rule firstList: firstList){
                            if (firstList.getKey() == s){
                                for (int i = 0; i < firstList.getValue().length(); i++) {
                                    int snd = sym.indexOf(firstList.getValue().charAt(i));
                                    matrix[frs][snd]='<';
                                    System.out.println(f + "<" + firstList.getValue().charAt(i));
                                }
                            }
                        }
                    }
                    begin++;
                    end++;
                }
            }
        }
    }

    /* THIRD RULE: x1 > x2
     - Vn followed by Vt => LAST(Vn) > Vt
     - Vn0 followed by Vn1 => LAST(Vn1) > FIRST(Vn1) */
    private void thirdRule(char[][] matrix, ArrayList <Character> sym) {
        System.out.println("\n\033[1mTHIRD RULE (x1 > x2):\033[0m");
        for(Rule rule: ruleList) {
            if (rule.getValue().length() > 1) {
                int begin = 0;
                int end = 1;
                while (end != rule.getValue().length()) {
                    char f = rule.getValue().charAt(begin);
                    char s = rule.getValue().charAt(end);
                    if ((f >= 'A' && f <= 'Z') && (s >= 'a' && s <= 'z')) {
                        int snd = sym.indexOf(s);
                        for (Rule lastList: lastList) {
                            if (lastList.getKey() == f) {
                                for (int i = 0; i < lastList.getValue().length(); i++) {
                                    int frs = sym.indexOf(lastList.getValue().charAt(i));
                                    matrix[frs][snd] = '>';
                                    System.out.println( lastList.getValue().charAt(i) + ">" + s);
                                }
                            }
                        }
                    }
                    if((f >= 'A' && f <= 'Z') && (s >= 'a' && s <= 'z')) {
                        for (Rule firstList: firstList) {
                            if (firstList.getKey() == s) {
                                for (int i = 0; i < firstList.getValue().length(); i++) {
                                    if (firstList.getValue().charAt(i) >= 'a' && firstList.getValue().charAt(i) <= 'z'){
                                        int snd = sym.indexOf(firstList.getValue().charAt(i));
                                        for (Rule lastList: lastList) {
                                            if (lastList.getKey() == f) {
                                                for (int j = 0; j < lastList.getValue().length(); j++) {
                                                    int frs = sym.indexOf(lastList.getValue().charAt(j));
                                                    matrix [frs][snd] = '>';
                                                    System.out.println(lastList.getValue().charAt(j) + ">"
                                                            + firstList.getValue().charAt(i));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    begin++;
                    end++;
                }
            }
        }
    }
}
