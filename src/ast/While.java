package ast;

/**
 * While is an AST Statement class which stores all Statements within an WHILE cond DO ...  statement.
 * The class also stores the condition of the WHILE statement for later evaluation
 * @author Pranav Varmaraja
 * @version 01/11/2021
 * Usage:
 * 
 * While stmt = new While(cond, stmt);
 */
public class While extends Statement{

    private Condition cond;
    private Statement stmt;

    /**
     * While constructor which takes in a condition and a statement, adding them to instance variables
     * @param cond condition to be stored as the while loop condition
     * @param statement statement to be evaluated while the condition is true
     */
    public While(Condition cond, Statement statement) {
        this.cond = cond;
        stmt = statement;
    }

    /**
     * getter which returns the Condition of the while loop
     * @return Condition condition of the while loop
     */
    public Condition getCondition() {

        return cond;

    }


    /**
     * getter which returns the Statement of the while loop
     * @return Statement, statement to be executed while the loop condition evaluates to true
     */
    public Statement getStatement() {
        return stmt;
    }
    
    
}
