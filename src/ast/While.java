package ast;

public class While extends Statement{

    private Condition cond;
    private Statement stmt;

    public While(Condition cond, Statement statement) {
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
