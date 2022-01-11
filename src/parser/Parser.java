package parser;

import scanner.*;
import ast.Number;
import ast.Statement;
import ast.Expression;
import ast.If;
import ast.Variable;
import ast.BinOp;
import ast.Writeln;
import ast.Assignment;
import ast.Block;
import java.util.List;
import java.util.ArrayList;
import ast.Condition;
import ast.While;

/**
 * Parser is a PASCAL parser for Compilers and Interpreters (2021-2022) lab exercise 2
 * The parser takes in a scanner pointing to a string or text file, and is able to parse PASCAL statements 
 * and expressions, returning Statement, Expression, and Condition objects as part of an AST.
 * @author Pranav Varmaraja
 * @version 01/10/2022
 * Usage:
 * Parser parser = new Parser(new Scanner(new FileInputStream(new File(<filepath>))));
 *
 */
public class Parser {

    private Scanner scanner;
    private String currToken;

    /**
     * Parser constructor for construction of a parser that
     * uses an Scanner object for input.
     * Usage:
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * Parser parser = new Parser(lex);
     * @param inStream the input stream to use
     */
    public Parser(Scanner s)
    {
        scanner = s;
        try
        {
            currToken = scanner.nextToken();
        } catch(ScanErrorException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method: eat
     * "eats" the current token, calls Scanner.nextToken() to move the stream one token forward
     * while also checking whether the token is the same as expected
     * @param expected the character expected to be the current character in the stream
     * @throws IllegalArgumentException throws if instance variable currToken does not match param expected
     */
    private void eat(String expected) throws IllegalArgumentException
    {
        if(expected.equals(currToken))
        {
            try
            {
                currToken = scanner.nextToken();

            } catch(ScanErrorException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            throw new IllegalArgumentException("Expected <" + expected +
                    "> but received <" + currToken + ">!");

        }
    }

    /**
     * Method: parseNumer
     * parses the current token and returns the numeric value of said token,
     * should not be called if currToken is not an integer
     * @return the integer value of currToken
     */
    private Number parseNumber() {
        int num = Integer.parseInt(currToken);
        eat(""+num);
        return new Number(num);
    }

    /**
     * Method: parseStatement
     * parses a BEGIN..END, WRITELN, or variable declaration (e.g. x:=10) statement in PASCAL
     * if a variable is declared, adds said variable to the HashMap<String, Integer> map
     * prints the results of all WRITELN statements to the console
     */
    public Statement parseStatement() {
        if(currToken.equals("WRITELN")) {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(exp);
        } else if(currToken.equals("BEGIN")) {
            List<Statement> stmts = new ArrayList<Statement>();
            eat("BEGIN");
            while(currToken.equals("BEGIN") || currToken.equals("WRITELN") || currToken.equals("END") || 
            Scanner.isLetter(currToken.charAt(0))) {
                if(currToken.equals("END")) {
                    eat("END");
                    eat(";");
                    return new Block(stmts);
                } else  {
                    stmts.add(parseStatement());
                }
            }
        } else if(currToken.equals("IF")) {
            eat("IF");
            Condition condition = parseCondition();
            eat("THEN");
            Statement stmt = parseStatement();
            return new If(condition, stmt);            

        } else if(currToken.equals("WHILE")) {
            eat("WHILE");
            Condition condition = parseCondition();
            eat("DO");
            Statement stmt = parseStatement();
            return new While(condition, stmt); 

        } else if(Scanner.isLetter(currToken.charAt(0))) {
            String varName = currToken;
            eat(currToken);
            eat(":=");
            Expression varValue = parseExpression();
            eat(";");
            return new Assignment(varName, varValue);
            

        }
        return null;
        
    }

    /**
     * Method: parseFactor
     * parses a factor, as per the grammar definition in the lab document, id | num | (expr) | - factor
     * @return int value of the factor, with all parentheses and negative signs applied
     */
    public Expression parseFactor() {
        try {
            return this.parseNumber();
        } catch(NumberFormatException e) {
            if(currToken.equals("(")) {
                eat(currToken);
                Expression num = parseExpression();
                eat(")");
                return num;
            } else if(currToken.equals("-")) {
                eat("-");
                return new BinOp("-", new Number(0),parseFactor());
            } else {
                String varName = currToken;
                eat(currToken);
                return new Variable(varName);
            }
        }
    }

    /**
     * Method: parseTerm
     * recursively parses a multiplication or division term, i.e. a term*factor, or a term/factor.
     * @return int value of the term, with all multiplication, division, parentheses, negative signs applied
     */
    public Expression parseTerm() {
        Expression val = parseFactor();
        while(currToken.equals("*") || currToken.equals("/")) {
            String operator = currToken;
            eat(currToken);
            if(operator.equals("*")) {
                val = new BinOp("*",val,parseFactor());
            } else if(operator.equals("/")) {
                val = new BinOp("/",val,parseFactor());
            }

        }
        return val;
    }

/**
     * Method: parseExpression
     * parses a factor, as per the grammar definition in the lab document, id | num | (expr) | - factor
     * @return int value of the expression, with all mathematical operations, grouping, and negative signs applied
     */
    public Expression parseExpression() {
        Expression val = this.parseTerm();
        while(currToken.equals("+") || currToken.equals("-")) {
            String operator = currToken;
            eat(currToken);
            if(operator.equals("+")) {
                val = new BinOp("+",val,parseTerm());
            } else if(operator.equals("-")){
                val = new BinOp("-",val,parseTerm());
            }
        }
        return val;
    }

    public Condition parseCondition() {
        Expression exp1 = parseExpression();
        String op = currToken;
        eat(currToken);
        Expression exp2 = parseExpression();
        return new Condition(exp1, op, exp2);
    }

   

}
