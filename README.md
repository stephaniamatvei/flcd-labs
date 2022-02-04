
# *Formal Languages and Compiler Design*

## *Stephania Matvei, FAF-203, 13th variant*

## Table of Contents

* [Introduction](#introduction)
* [Literature to read](#literature-to-read)
* [Laboratory tasks](#laboratory-tasks)
  * [Lab 1 -  Regular Grammar to Finite Automata](#lab-1---regular-grammar-to-finite-automata)
  * [Lab 2 - NFA to DFA conversion](#lab-2---nfa-to-dfa-conversion)
  * [Lab 3 - Lexer](#lab-3---lexer)
  * [Lab 4 - Chomsky Natural Form](#lab-4---chomsky-natural-form)
  * [Lab 5 - LL1 and Simple precedence parsing](#lab-5---ll1-and-simple-precedence-parsing)
  * [Lab 6 - Parser and AST construction](#lab-6---parser-and-ast-construction)

## Introduction


The main objective of the course is to introduce the fundamental notions about formal languages (Chomsky classification of Languages, Regular Languages, Automata, Context Free Grammars, Push Down Automata, Turing Machine) and understand the mechanisms governing the analysis and synthesis of programming languages. Students will learn the most important techniques for the representation and generation of Languages (in particular, regular and context-free languages).

Those techniques will be applied to the construction of a compiler for a programming language. In particular, during this course the student will learn how to build the different parts of a Compiler with a particular emphasis on Lexical Analysers, Parsers.

<img src="https://cs.fit.edu/~ryan/cse4083/formal1.png" width="350" height="250" align="middle" alt="formal">  

## Literature to read

- Peter Linz - "Formal Languages and Automata"
- John E. Hopcroft, Jeffrey D. Ullman - "Introduction to Automata Theory, Languages, and Computation"
- [Andrew M. Pitts - "Regular Languages and Finite Automata"](https://www.cl.cam.ac.uk/teaching/0910/RLFA/reglfa.pdf)
- [Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ullman - "Compilers: Principles, Techniques, & Tools"](http://ce.sharif.edu/courses/94-95/1/ce414-2/resources/root/Text%20Books/Compiler%20Design/Alfred%20V.%20Aho,%20Monica%20S.%20Lam,%20Ravi%20Sethi,%20Jeffrey%20D.%20Ullman-Compilers%20-%20Principles,%20Techniques,%20and%20Tools-Pearson_Addison%20Wesley%20(2006).pdf)

## Laboratory tasks

### Lab 1 - Regular Grammar to Finite Automata

1. Write a program which converts regular grammar to Finite Automaton (FA).
2. Using Finite Automaton (FA) check if some input string is accepted by FA  (meaning you could generate that string by traversing FA).
3. Determine the grammar type by the Chromsky classification.
4. Bonus Point -> Using some graphic library plot FA graph.

***Deadline: 05.02.2022***

### Lab 2 - NFA to DFA conversion

***Deadline: 12.02.2022***

### Lab 3 - Lexer

***Deadline: 05.03.2022***

### Lab 4 - Chomsky Natural Form

***Deadline: 02.04.2022***

### Lab 5 - LL1 and Simple precedence parsing

***Deadline: 23.04.2022***

### Lab 6 - Parser and AST construction

***Deadline: 14.05.2022***