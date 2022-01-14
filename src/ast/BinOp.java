package ast;
import emitter.Emitter;

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

    @Override
    public void compile(Emitter e) {

        exp1.compile(e);
        e.emitPush("$v0");
        exp2.compile(e);
        e.emitPop("$t0");
        if(getOp().equals("+")) {
            e.emit("add $v0 $t0 $v0");
        } else if(getOp().equals("-")) {
            e.emit("sub $v0 $t0 $v0");
        } else if(getOp().equals("*")) {
            e.emit("mult $t0 $v0");
            e.emit("mflo $v0");
        } else {
            e.emit("div $t0 $v0");
            e.emit("mflo $v0");
        }

    }
    
}
