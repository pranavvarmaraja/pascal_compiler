package ast;

public class Condition {

    private String op;
    Expression exp1;
    Expression exp2;

    public Condition( Expression exp1, String op, Expression exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public String getOp() {
        return op;
    }

    public Expression getFirstExp() {
        return exp1;
    }

    public Expression getSecondExp() {
        return exp2;
    }
    
}
