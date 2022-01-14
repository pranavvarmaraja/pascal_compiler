package ast;
import emitter.Emitter;
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

    /**
     * Compiles a condition into MIPS assembly by creating a branch statement depending on the relop
     * The branch statement then specifies a target label for the program to jump to depending
     * on the outcome of the relop.
     * @param e Emitter used to emit MIPS code
     * @param targetLabel label to jump to if the condition evaluates to true (or false in the case of >= and <=)
     */
    public void compile(Emitter e, String targetLabel) {
        exp1.compile(e);
        e.emitPush("$v0");
        exp2.compile(e);
        e.emitPop("$t0");
        switch (getOp()) {
            case "=":
                e.emit("beq $t0 $v0 " + targetLabel);
                break;
            case "<>":
                e.emit("bne $t0 $v0 " + targetLabel);
                break;
            case "<":
                e.emit("blt $t0 $v0 " + targetLabel);
                break;
            case ">":
                e.emit("bgt $t0 $v0 " + targetLabel);
                break;
            case "<=":
                e.emit("bgt $t0 $v0 " + targetLabel);
                break;
            case ">=":
                e.emit("blt $t0 $v0 " + targetLabel);
                break;

        }
    }
    
}
