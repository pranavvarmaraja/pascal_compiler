package ast;
import java.util.List;
import emitter.Emitter;
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

    @Override
    public void compile(Emitter e) {
        e.emitPush("$ra");
        for(Expression param: parameters) {
            param.compile(e);
            e.emitPush("$v0");
        }
        e.emit("jal proc" + getName());
        for(Expression param: parameters) {
            e.emitPop("$t0");
        }
        e.emitPop("$ra");

    }
    
}
