package ast;

public class If extends Statement {

    private Condition cond;
    private Statement stmt;

    public If(Condition cond, Statement statement) {
        this.cond = cond;
        stmt = statement;
    }

    public Condition getCondition() {

        return cond;

    }

    public Statement getStatement() {
        return stmt;
    }
    
}
