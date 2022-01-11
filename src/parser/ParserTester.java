package parser;
import scanner.*;
import java.io.*;

/**
 * ParserTester is a tester for the simple parser for Compilers and Interpreters (2021-2022) lab exercise 2
 * @author Pranav Varmaraja
 *
 * Usage: point the parser object to the text file you wish to scan, and run the main method
 *
 */
public class ParserTester {
    public static void main(String[] args) {
        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileInputStream
        (new File("/home/pranav/projects/ATCS_Compilers/Compiler/src/parser/parser_testing/parserTest4.txt"))); //paste your desired filepath here
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        Parser parser=  new Parser(scanner);
        while(scanner.hasNext()) {
            parser.parseStatement();
        }
    }
}
