package ast;
import emitter.Emitter;

/**
 * If is an AST Statement class which stores all Statements within an IF cond THEN ...  statement.
 * The class also stores the condition of the if statement for later evaluation
 * @author Pranav Varmaraja
 * @version 01/11/2021
 * Usage:
 * 
 * If stmt = new If(cond, stmt);
 */
public class If extends Statement {

    private Condition cond;
    private Statement stmt;

    /**
     * constructor for an If statement, takes in a condition and a statement which are stored as instance variables
     * @param cond If condition to be stored
     * @param statement statement to be executed if condition evaluates to true
     */
    public If(Condition cond, Statement statement) {
        this.cond = cond;
        stmt = statement;
    }

    /**
     * getter which returns the condition of the if statement
     * @return Condition condition of if statement
     */
    public Condition getCondition() {

        return cond;

    }

    /**
     * getter which returns the statement to be executed if the condition is true
     * @return Statement to be executed if condition evaluates to true
     */
    public Statement getStatement() {
        return stmt;
    }
    
    @Override
    public void compile(Emitter e) {
        int labelId = e.nextLabelID();
        if(cond.getOp().equals("<=") || cond.getOp().equals(">=")) {
            cond.compile(e, "endif" + labelId);
            e.emit("j startif" + labelId);
        } else {
            cond.compile(e, "startif" + labelId);
            e.emit("j endif" + labelId);
        }

        e.emit("startif" + labelId + ":");
        getStatement().compile(e);
        e.emit("endif" + labelId + ":");

    }
}
