package parser;

import scanner.*;

import java.beans.Expression;
import java.io.*;
import java.util.HashMap;

public class Parser {

    private Scanner scanner;
    private String currToken;
    private HashMap<String, Expression> map;

    public Parser(Scanner s)
    {
        scanner = s;
        map = new HashMap<String, Expression>();
        try
        {
            currToken = scanner.nextToken();
        } catch(ScanErrorException e)
        {
            e.printStackTrace();
        }
    }

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

    private int parseNumber() {
        int num = Integer.parseInt(currToken);
        eat(""+num);
        return num;
    }

    public void parseStatement() {
        eat("WRITELN");
        eat("(");
        int num = parseNumber();
        eat(")");
        eat(";");
        System.out.println(num);
    }

    public int parseFactor() {
        try {
            return this.parseNumber();
        } catch(NumberFormatException e) {
            if(currToken.equals("(")) {
                eat(currToken);
                int num = parseFactor();
                eat(")");
                return num;
            } else {
                eat("-");
                return -1*parseFactor();
            }
        }
    }

    public static void main(String[] args) {
        Parser parser = new Parser(new Scanner("-(2)\n."));
        System.out.println(parser.parseFactor());
    }

}
