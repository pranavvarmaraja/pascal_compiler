package ast;

/**
 * Variable is an AST Expression which stores an variable name.
 * @author Pranav Varmaraja
 * @version 01/11/2021
 * Usage:
 * 
 * Variable num = new Variable("x");
 */
public class Variable extends Expression{

    private String name;

    /**
     * Variable constructor which takes an input string and assigns it to the name instance variable
     * @param str variable name to be stored
     */
    public Variable(String str) {
        name = str;
    }

    /**
     * getter which returns the variable name
     * @return String the name of the variable
     */
    public String getName() {
        return name;
    }
    
}
