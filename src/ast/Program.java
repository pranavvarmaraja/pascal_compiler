package ast;
import java.util.List;

/**
 * The Program class is an AST class representing an entire PASCAL program, thus 
 * it serves as the root of the AST. It stores a list of procedureDeclarations at 
 * the start of the program as well as the main program statement.
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public class Program {

    private List<ProcedureDeclaration> declarations;
    private Statement stmt;

    /**
     * constructor for a program object, takes in a list of declarations and a statement
     * @param declarations List<ProcedureDeclaration> of procedureDeclarations at the start of the file
     * @param stmt Statement body statement to be run as part of the "main method"
     */
    public Program(List<ProcedureDeclaration> declarations, Statement stmt) {

        this.declarations = declarations;
        this.stmt = stmt;

    }

    /**
     * getter which returns the list of procedureDeclarations
     * @return List<ProcedureDeclaration> list of procedures declared at the start of the file
     */
    public List<ProcedureDeclaration> getProcedures() {
        return declarations;
    }

    /**
     * getter which returns the body statement of the program
     * @return Statement statement to be run within the main program
     */
    public Statement getStatement() {
        return stmt;
    }
    
}
