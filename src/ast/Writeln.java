package ast;

/**
 * Writeln is an AST Statement class which stores the expression within a WRITELN statement.
 * @author Pranav Varmaraja
 * @version 01/11/2021
 * Usage:
 * 
 * Writeln stmt = new Writeln(exp);
 */
public class Writeln extends Statement {
    
    private Expression exp;

    /**
     * constructor for the Writeln object, takes in an expression and stores it in an instance variable
     * @param exp expression to be printed
     */
    public Writeln(Expression exp) {
        this.exp = exp;
    }

    /**
     * getter which returns the expression inside the Writeln statement
     * @return Expression expression to be printed
     */
    public Expression getExpression() {
        return exp;
    }

}
