package ast;

public class BinOp extends Expression {

    private String op;
    Expression exp1;
    Expression exp2;

    public BinOp(String str, Expression exp1, Expression exp2) {

        op = str;
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
