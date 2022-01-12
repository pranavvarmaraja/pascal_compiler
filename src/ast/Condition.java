package ast;
/**
 * Condition is an AST class to represent a boolean condition in the form expr relop expr
 * @author Pranav Varmaraja
 * @version 1/11/2022
 * Usage: Condition cond = new Condition(exp,op,exp2)
 */
public class Condition {

    private String op;
    Expression exp1;
    Expression exp2;

    /**
     * constructor which initializes a condition with 2 expressions and a relational operator
     * @param exp1 first expression on left hand side of operator
     * @param op boolean operation to be conducted
     * @param exp2 second expression on right hand side of operator
     */
    public Condition( Expression exp1, String op, Expression exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * getter which returns the operation to be conducted within this condition
     * @return String operation to be conducted, e.g. "<"
     */
    public String getOp() {
        return op;
    }

    /**
     * getter which returns the first expression preceding the operator
     * @return Expression the left hand side of the operator
     */
    public Expression getFirstExp() {
        return exp1;
    }

    
    /**
     * getter which returns the second expression, after the operator
     * @return Expression on right hand side of the operator
     */
    public Expression getSecondExp() {
        return exp2;
    }
    
}
