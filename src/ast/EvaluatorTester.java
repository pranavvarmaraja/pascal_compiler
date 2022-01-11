package ast;
import scanner.Scanner;
import java.io.*;
import parser.Parser;
import environment.Environment;


public class EvaluatorTester {

    public static void main(String[] args){

        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileInputStream
        (new File("/home/pranav/projects/ATCS_Compilers/Compiler/src/ast/ast_testing/parserTest6.txt"))); //paste your desired filepath here
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        Parser parser=  new Parser(scanner);
        Evaluator evaluator = new Evaluator();
        Environment env = new Environment();
        while(scanner.hasNext()) {
            evaluator.exec(parser.parseStatement(), env);
        }


    }
    
}
