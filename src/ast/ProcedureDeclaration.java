package ast;
import java.util.List;


public class ProcedureDeclaration {

    private String procedureName;
    private Statement procedureBody;
    private List<String> parameters;

    public ProcedureDeclaration(String name, Statement body) {
        procedureName = name;
        procedureBody = body;
        parameters = null;
    }

    public ProcedureDeclaration(String name, Statement body, List<String> paramList) {
        procedureName = name;
        procedureBody = body;
        parameters = paramList;
    }

    public String getName() {
        return procedureName;
    }

    public Statement getBody() {
        return procedureBody;
    }

    public List<String> getParameters() {
        return parameters;
    }
    
}
