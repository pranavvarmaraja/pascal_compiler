package ast;
import java.util.List;

/**
 * ProcedureDeclaration is an AST class representing a procedure declaration, in the form PROCEDURE id(params); stmt;
 * The class sotres the name of the procedure, the varNames of its parameters, and its body statement
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public class ProcedureDeclaration {

    private String procedureName;
    private Statement procedureBody;
    private List<String> parameters;

    

    /**
     * constructor for a ProcedureDeclaration, takes in a procedureName, body statement, and a list of parameterNames
     * @param name name of procedure
     * @param body body of procedure
     * @param paramList list of parameter names
     */
    public ProcedureDeclaration(String name, Statement body, List<String> paramList) {
        procedureName = name;
        procedureBody = body;
        parameters = paramList;
    }

    /**
     * getter which returns the name of the procedure
     * @return String name of procedure
     */
    public String getName() {
        return procedureName;
    }


    /**
     * returns the body Statement of the procedure
     * @return Statement, the body of the procedure
     */
    public Statement getBody() {
        return procedureBody;
    }

    /**
     * returns the list of parameterNames
     * @return List<String> the list of parameterNames
     */
    public List<String> getParameters() {
        return parameters;
    }
    
}
