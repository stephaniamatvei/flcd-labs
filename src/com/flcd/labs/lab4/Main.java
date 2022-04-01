package com.flcd.labs.lab4;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String pathname = "/Users/stephaniamatvei/IdeaProjects/flcd-labs/src/com/flcd/labs/lab4/input/input.txt";
        try {
            StepFive stepFive = new StepFive(new File(pathname));
            stepFive.printChomskyNormalForm();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}