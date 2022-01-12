package ast;

/**
 * Number is an AST Expression which stores an integer.
 * @author Pranav Varmaraja
 * @version 01/11/2021
 * Usage:
 * 
 * Number num = new Number(10);
 */
public class Number extends Expression {

    private int value;

    /**
     * Number constructor which takes in an integer to store
     * @param val integer to store
     */
    public Number(int val) {
        
        value = val;
        
    }

    /**
     * getter which returns the integer value of this Number
     * @return integer stored within the Number object
     */
    public int getValue() {
        return value;
    }
    
}
