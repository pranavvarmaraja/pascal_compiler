package ast;

public class Assignment extends Statement {

    private String var;
    private Expression exp;

    public Assignment(String var, Expression exp) {
        this.var = var;
        this.exp = exp;
    }

    public String getVariable() {
        return var;
    }

    public Expression getExpression() {
        return exp;
    }


    
}
