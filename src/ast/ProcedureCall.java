package ast;
import java.util.List;

/**
 * ProcedureCall is an AST class representing a ProcedureCall expression, 
 * storing the Procedure name and its parameter list
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public class ProcedureCall extends Expression{

    private String procedureName;
    private List<Expression> parameters;

    

    /**
     * constructor for a ProcedureCall, which takes in its name and its parameterList
     * @param name name of procedure
     * @param params list of parameters passed into the procedureCall
     */
    public ProcedureCall(String name, List<Expression> params) {

        procedureName=name;
        parameters=params;
    }

    /**
     * returns the name of the procedure
     * @return String, procedureName
     */
    public String getName() {
        return procedureName;
    }

    /**
     * returns the parameterList corresponding to this procedure object
     * @return List<Expression> list of parameters passed into the procedureCall
     */
    public List<Expression> getParams() {
        return parameters;
    }
    
}
