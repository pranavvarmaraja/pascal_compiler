package ast;

/**
 * BinOp is an AST Expression which stores a binary operation along with its two operands, which can also be expressions.
 * @author Pranav Varmaraja
 * @version 01/11/2021
 * Usage:
 * 
 * BinOp op = new BinOp("+", exp1, exp2);
 */
public class BinOp extends Expression {

    private String op;
    Expression exp1;
    Expression exp2;

    /**
     * constructor for a BinOp object
     * @param str operator to be stored
     * @param exp1 operand1 to be stored
     * @param exp2 operand2 to be stored
     */
    public BinOp(String str, Expression exp1, Expression exp2) {

        op = str;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * returns the operator of this BinOp
     * @return String operator of this BinOp
     */
    public String getOp() {
        return op;
    }
    
    /**
     * returns the first operand in this binary operation
     * @return Expression first operand in the operation
     */
    public Expression getFirstExp() {
        return exp1;
    }


    /**
     * returns the second operand in this binary operation
     * @return Expression second operand in the operation
     */
    public Expression getSecondExp() {
        return exp2;
    }
    
}
