# PASCAL Compiler

## Main Objective

The main objective of this project is to implement a compiler written in Java which converts PASCAL code into MIPS assembly language. The compiler makes use of various modules, listed below, which are used in various steps of the PASCAL compilation.

## Modules

#### Scanner

This module scans and tokenizes an input program into identifiers, operators, or numbers. An operator is defined as a mathematical or boolean operator, while identifiers are any PASCAL keywords/reserved words. The full documentation is available within the `src/scanner/Scanner.java` file. The scanner makes use of the java BufferedReader class to read input from text files.

#### Parser

The Parser module makes use of a scanner to separate identifiers, operators, and numbers. The parser then returns Statement, Expression, and Condition objects as part of an Abstract Syntax Tree (AST) which is used to evaluate each type of object (Statement, Expression, and Condition). These objects and their functios are described later under the AST module section. The full documentation of the Parser can be found within the `src/parser/Parser.java` files. The parser keeps track of the current token and calls various parsing functions depending on what the current parsing job is.

#### Environment

The environment module stores all variables declared within a PASCAL program within a HashMap. The `getVariable(<varName>)` method can be used to access the value of a variable of a given name. The Environment object also stores all procedures declared within the program. These procedures (an AST object) can be accessed with the `getProcedure(<procName>)` method, which returns an AST object representing the Procedure.

#### AST

The AST module contains all classes pertaining to Abstract Syntax Tree objects. A description of an AST can be found [here](https://en.wikipedia.org/wiki/Abstract_syntax_tree). Essentially, the module contains classes which detail each individual Statement which can be parsed. The AST is then constructed out of these modules and represents a tree of operations to be parsed in a particular order. The AST contains classes for each of the following:

- Assignment: Represents an assignment statement in PASCAL
- BinOp: Represents a binary operation, such as `+, -, *, or /`
- Block: Represents a block of PASCAL code to be evaluated
- Condition: Represents a boolean condition in PASCAL
- Expression: The parent class for all expressions
- If: Represents an if statement in PASCAL
- Number: Represents a PASCAL number
- ProcedureCall: Represents a procedure call in PASCAL
- ProcedureDeclaration: Represents a procedure declaration in PASCAL
- Program: Represents an entire PASCAL program
- Statement: Represents any statement in PASCAL
- Variable: Represents a variable in PASCAL
- Writeln: Represents a PASCAL `WRITELN` statement

The AST class also contains the Evaluator class, which is used to evaluate all elements within an AST. More details on how this is achieved can be found in the file `src/ast/Evaluator.java`.

#### Emitter

The emitter class writes the MIPS equivalent code for each PASCAL object in the AST. Each class within the AST has a `compile()` method inherited from their parent class (Statement, Expression, etc). This method uses an emitter to translate that object into its equivalent MIPS code.

## Conclusion

This project serves as a demonstration of what a lightweight compiler for the PASCAL language compiler may look like. Any questions should be directed to the email listed on my [github profile](https://github.com/pranavvarmaraja).

