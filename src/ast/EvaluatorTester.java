package ast;
import scanner.Scanner;
import java.io.*;
import parser.Parser;
import environment.Environment;

/**
 * EvaluatorTester is a tester for the Evaluator class. Paste the desired filepath to the PASCAL program below, and this will evaluate the entirety of the program
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public class EvaluatorTester {

    public static void main(String[] args){

        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileInputStream
        (new File("/home/pranav/projects/ATCS_Compilers/Compiler/src/ast/ast_testing/parserTest8.txt"))); //paste your desired filepath here
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        Parser parser=  new Parser(scanner);
        Evaluator evaluator = new Evaluator();
        Environment env = new Environment();
        evaluator.run(parser.parseProgram(), env);


    }
    
}
