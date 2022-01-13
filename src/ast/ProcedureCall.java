package ast;
import java.util.List;

public class ProcedureCall extends Expression{

    private String procedureName;
    private List<Expression> parameters;

    public ProcedureCall(String name) {

        procedureName = name;
        parameters = null;

    }

    public ProcedureCall(String name, List<Expression> params) {

        procedureName=name;
        parameters=params;
    }

    public String getName() {
        return procedureName;
    }

    public List<Expression> getParams() {
        return parameters;
    }
    
}
