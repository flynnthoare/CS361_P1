# CS361_P1
# Project 1: Deterministic Finite Automata

* Author: Flynn Hoare & Nick Bortz
* Class: CS361 Section 2
* Semester: Spring 2025

## Overview

Concisely explain what the program does. If this exceeds a couple of
sentences, you're going too far. Generally you should be pulling this
right from the project specification. Please don't just cut and
paste, but paraphrase what is stated in the project specification.

## Reflection

As a whole, the project went very smoothly. I think the thing that caused 
the most trouble was figuring out what structure to use in order to contain 
the transition table for each DFA. The first thing we tried was using Hashmap
of type <String, String> and using a comma as a delimiter in order to separate
the start state and the symbol we were transitioning on. Thinking this could 
cause problems if states were created using names with the delimeter we were 
using, we ended up settling on a nested Hashmap instead. Besides that, we didn't 
run into any problems that weren't quickly resolved.

Write a brief (2-3 paragraph) reflection describing your experience with this 
project. Answer the following questions (but feel free to add other insights): 
- What worked well and what was a struggle?
- What concepts still aren't quite clear?
- What techniques did you use to make your code easy to debug and modify?
- What would you change about your design process?
- If you could go back in time, what would you tell yourself about doing this project?

## Compiling and Using

This section should tell the user how to compile your code.  It is
also appropriate to instruct the user how to use your code. Does your
program require user input? If so, what does your user need to know
about it to use it as quickly as possible?

## Sources used

If you used any sources outside of the lecture notes, class lab files,
or text book you need to list them here. If you looked something up on
stackoverflow.com and fail to cite it in this section it will be
considered plagiarism and be dealt with accordingly. So be safe CITE!

----------
This README template is using Markdown. To preview your README output,
you can copy your file contents to a Markdown editor/previewer such
as [https://stackedit.io/editor](https://stackedit.io/editor).