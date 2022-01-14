package emitter;
import scanner.Scanner;
import parser.Parser;
import java.io.*;
import ast.Evaluator;
import environment.Environment;
import ast.Program;

/**
 * tests the compiler by compiling a PASCAL program and ouputting its corresponding MIPS code
 * @author Pranav Varmaraja
 * @version 01/13/2022
 */
public class EmitterTester {


    public static void main(String[] args){

        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileInputStream
        (new File("/home/pranav/projects/ATCS_Compilers/Compiler/src/emitter/emitter_testing/parserTest9.txt"))); //paste your desired filepath here
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        Parser parser=  new Parser(scanner);
        Evaluator evaluator = new Evaluator();
        Environment env = new Environment();
        Program p = parser.parseProgram();
        p.compile("outputFile.txt"); //output filename here
        // evaluator.run(p, env);


    }
    
}
