package ast;
import java.util.List;
import emitter.Emitter;

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
    private List<String> variables;

    

    /**
     * constructor for a ProcedureDeclaration, takes in a procedureName, body statement, a list of parameterNames, and a list of VariableNames
     * @param name name of procedure
     * @param body body of procedure
     * @param paramList list of parameter names
     * @param variableList list of variable names
     */
    public ProcedureDeclaration(String name, Statement body, List<String> paramList, List<String> variableList) {
        procedureName = name;
        procedureBody = body;
        parameters = paramList;
        variables = variableList;
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

    /**
     * getter which returns the list of local variables declared within the procedure
     * @return List<String> variables declared within the procedure
     */
    public List<String> getVariables() {
        return variables;
    }

    /**
     * compiles the declaration using the Emitter parameter, emitting the MIPS
     * code to evaluate the procedureDeclaration
     * @param e Emitter to be used for emission of the MIPS code
     */
    public void compile(Emitter e) {
        
        e.emit("proc" + getName() + ":");
        e.emit("li $t0 0");
        e.emitPush("$t0");
        e.setProcedureContext(this);
        for(String var: variables) {
            e.emit("li $t0 0");
            e.emitPush("$t0");
        }
        getBody().compile(e);
        e.clearProcedureContext();
        e.emitPop("$v0");
        e.emit("jr $ra");


    }

}
