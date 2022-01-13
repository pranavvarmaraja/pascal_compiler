package scanner;

import java.io.*;
/**
 * Scanner is a simple scanner for Compilers and Interpreters (2021-2022) lab exercise 1
 * The scanner takes in a string or a text file and tokenizes the input into identifiers,
 * operators, or numbers
 * @author Pranav Varmaraja
 * @version 09/28/2021
 * Usage:
 * Scanner scanner = new Scanner(new FileInputStream(new File(<filepath>)))
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    /**
     * Scanner constructor for construction of a scanner that
     * uses an InputStream object for input.
     * Usage:
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }
    /**
     * Scanner constructor for constructing a scanner that
     * scans a given input string.  It sets the end-of-file flag and then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }
    /**
     * Method: getNextChar
     * sets private instance variable currentChar to the next character in the character stream
     * using the BufferedReader read() method
     */
    private void getNextChar()
    {
        int nextChar = 0;
        try
        {
            nextChar = in.read();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        if(nextChar==-1 || nextChar=='.')
        {
            eof=true;
        }
        currentChar = (char) nextChar;

    }
    /**
     * Method: eat
     * "eats" the current character, calls getNextChar() to move the stream one character forward
     * while also checking whether the characters are the same as expected
     * @param expected the character expected to be the current character in the stream
     */

    private void eat(char expected) throws ScanErrorException
    {
        if(expected!=currentChar)
        {
            throw new ScanErrorException("Illegal character - expected <" +
                    expected + "> and found <" + currentChar + ">!");
        }
        else if(hasNext())
        {
            getNextChar();
        }



    }
    /**
     * Method: hasNext
     * returns whether or not the file has additional characters in the stream.
     * @return true if has not reached the end of the file (the stream), false otherwise
     */
    public boolean hasNext()
    {
        return !eof;

    }
    /**
     * Method: nextToken
     * returns the next token in the input stream, ignoring whitespace and comments
     * @return the next token in the input stream, either a number, identifier, or operator
     */
    public String nextToken() throws ScanErrorException
    {
//        eats leading whitespace
        while(isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }
//        ignores line if in-line comment
        if(currentChar=='/')
        {
            eat(currentChar);
            if(currentChar=='/')
            {
                eat(currentChar);
                while(currentChar!='\n')
                {
                    eat(currentChar);
                }

            }
            else
            {
                return "/";
            }

        }
//        eats leading whitespace
        while(isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }
        if(isLetter(currentChar))
        {
            return scanIdentifier();
        }
        else if(isDigit(currentChar))
        {
            return scanNumber();
        }
        else
        {
            return scanOperand();
        }


    }

    /**
     * Method: isDigit
     * checks whether a given character is a digit from 0-9
     * @param input character to be checked
     * @return true if between 0-9, false otherwise
     */
    public static boolean isDigit(char input)
    {
        return(input>='0' && input<='9');
    }

    /**
     * Method: isLetter
     * checks whether a given character is a letter from a-z or A-Z
     * @param input character to be checked
     * @return true if a letter from a-z or A-Z, false otherwise
     */
    public static boolean isLetter(char input)
    {
        return((input>='A' && input<='Z') ||(input>='a' && input<='z'));
    }

    /**
     * Method: isWhiteSpace
     * checks whether a given character qualifies as whitespace " ", "\t", "\n", or "\r"
     * @param input character to be checked
     * @return true if whitespace, false otherwise
     */
    public static boolean isWhiteSpace(char input)
    {
        return(input==' ' || input=='\t' || input=='\r' || input=='\n');
    }

    /**
     * Method: scanNumber()
     * @return the next token, a digit, only called if the token begins with a digit
     * @throws ScanErrorException
     */
    private String scanNumber() throws ScanErrorException
    {
        String ret = "";
        while(isDigit(currentChar))
        {
            ret += Character.toString(currentChar);
            eat(currentChar);
        }
        return ret;
    }

    /**
     * Method: scanOperand()
     * @return the next token (an operator) in the input stream,
     * only called if not an identifier or a digit,
     * @throws ScanErrorException if no predetermined operator is recognized
     */
    private String scanOperand() throws ScanErrorException
    {
        String ret = "";
        char[] operands = {'=', '+', '-', '*', '/', '%', '(', ')',';',','};
        if(currentChar=='<' || currentChar==':' || currentChar=='>')
        {
            ret+=currentChar;
            eat(currentChar);
            if(currentChar=='=')
            {
                ret += currentChar;
                eat(currentChar);
                return ret;
            }
            else if(ret.equals(":"))
            {
                ret = "";
            }
            else
            {
                eat(currentChar);
                return ret;
            }
        }
        else
        {
            for(char operator : operands)
            {
                if(currentChar==operator)
                {
                    ret+=operator;
                    eat(currentChar);
                    return ret;
                }
            }
        }
        if(!Character.toString(currentChar).equals(".") && hasNext())
        {
            throw new ScanErrorException("No lexeme recognized!");

        }
       return "";

    }

    /**
     * Method: scanIdentifier()
     * @return next token in input stream, if qualifies as an identifier
     */
    private String scanIdentifier() throws ScanErrorException
    {
        String ret ="";
        while(!isWhiteSpace(currentChar) && (isLetter(currentChar) || isDigit(currentChar)))
        {
            ret+=currentChar;
            eat(currentChar);
        }
        return ret;
    }

}

