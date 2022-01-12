package ast;

import java.util.List;

/**
 * Block is an AST Statement class which stores all Statements within a BEGIN...END block statement.
 * @author Pranav Varmaraja
 * @version 01/11/2021
 * Usage:
 * 
 * Block block = new Block(stmt_list);
 */
public class Block extends Statement {

    private List<Statement> stmts;

    /**
     * Block constructor which takes in a List<Statement>
     * @param list List<Statement> which corresponds to statements included within the block
     */
    public Block(List<Statement> list) {
        stmts = list;
    }
    
    /**
     * getter which returns the list of statements in the block
     * @return List<Statement> list of statements in block
     */
    public List<Statement> getStatements() {
        return stmts;
    }

    /**
     * mutator which allows for addition of a statement into a Block
     * @param s statement to add into the list of statements
     */
    public void addStatement(Statement s) {
        stmts.add(s);
    }
}
