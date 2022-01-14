package ast;
import emitter.Emitter;
/**
 * Assignment is an AST Statement which assigns a variable name to its value.
 * @author Pranav Varmaraja
 * @version 01/10/2022
 * Usage:
 * Assignment assign = new Assignment(var, exp);
 * assign.getVariable(); // returns variable name
 * assign.getExpression(); // returns the exprsession to which variable is instantiated
 *
 */
public class Assignment extends Statement {

    private String var;
    private Expression exp;

    /**
     * constructor for an Assignment statement
     * @param var variable name to be stored within this object
     * @param exp variable value to be stored within this object
     */
    public Assignment(String var, Expression exp) {
        this.var = var;
        this.exp = exp;
    }

    /**
     * returns the variable name of the declared variable
     * @return String variable name
     */
    public String getVariable() {
        return var;
    }

    /**
     * returns the value of the expression to which the variable is assigned
     * @return instantiated expression of variable
     */
    public Expression getExpression() {
        return exp;
    }

    @Override
    public void compile(Emitter e) {
        exp.compile(e);
        if(!e.isLocalVariable(getVariable())) {
            e.emit("sw $v0 var" + getVariable()); 
        } else {
            e.emit("sw $v0 " + e.getOffset(getVariable())+"($sp)");
        }
    }


    
}
