# CS361_P1
# Project 1: Deterministic Finite Automata

* Author: Flynn Hoare & Nick Bortz
* Class: CS361 Section 2
* Semester: Spring 2025

## Overview

This program is a Deterministic Finite Automata (DFA). The DFA
contains a set of states that can transition between each other over
an alphabet. The purpose is to determine if a string is accepted by
the language that the DFA defines.

## Reflection
### Flynn
As a whole, the project went very smoothly. I think the thing that caused 
the most trouble was figuring out what structure to use in order to contain 
the transition table for each DFA. The first thing we tried was using Hashmap
of type <String, String> and using a comma as a delimiter in order to separate
the start state and the symbol we were transitioning on. Thinking this could 
cause problems if states were created using names with the delimeter we were 
using, we ended up settling on a nested Hashmap instead. Besides that, we didn't 
run into any problems that weren't quickly resolved.

### Nick
This project went very well. The biggest struggle I had was figuring out 
a way to use maps and sets in a way that would order the items correctly
when iterating. We eventually used linked maps and sets, which is primarily
useful for the toString function, ensuring everything is outputted in order.
A technique I used was to write each function and test it individually with
small unit tests and such. Doing it this way, each function only took a few
minutes to complete and have passing tests. The design process went very
smooth, since both Flynn and I understood what the project was and how it 
should work. We had some meetings to split up the work and met occasionally
to see what the other was working on. If I could go back in time, I would
tell myself to think a little more about what data types I wanted to use.
Using sets and maps worked, but another solution would be to have the 
individual states handle the transitions or use arraylists instead. Overall,
this was a very interesting project that helped me better understand the
workings of how OOP can develop this type of machine.

## Compiling and Using

To compile, execute the following command in the main project directory:
```
javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java
```
Run the compiled class with the command:
```
java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar
org.junit.runner.JUnitCore test.dfa.DFATest
```

The program requires no user input.

## Sources used

https://docs.oracle.com/javase/8/docs/api/java/util/Set.html
https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
