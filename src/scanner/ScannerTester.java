package scanner;

import java.io.*;

/**
 * ScannerTester is a tester for the simple scanner for Compilers and Interpreters (2021-2022) lab exercise 1
 * @author Pranav Varmaraja
 *
 * Usage: point the scanner object to the text file you wish to scan, and run the main method
 *
 */
public class ScannerTester {

    public static void main(String[] args) {
        Scanner scanner = null;
        try{
//            paste filepath to the text file you wish to tokenize below
            scanner = new Scanner(new FileInputStream
                    (new File("/home/pranav/projects/ATCS_Compilers/Compiler/src/scanner/scanner_testing/ScannerTest.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNext()) {
            try{
                System.out.println(scanner.nextToken());
                if(!scanner.hasNext()) {
                    break;
                }

            } catch(ScanErrorException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

    }
}
