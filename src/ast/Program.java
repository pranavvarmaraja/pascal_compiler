package ast;
import java.util.List;

public class Program {

    private List<ProcedureDeclaration> declarations;
    private Statement stmt;

    public Program(List<ProcedureDeclaration> declarations, Statement stmt) {

        this.declarations = declarations;
        this.stmt = stmt;

    }

    public List<ProcedureDeclaration> getProcedures() {
        return declarations;
    }

    public Statement getStatement() {
        return stmt;
    }

    public void addProcedure(ProcedureDeclaration dec) {
        declarations.add(dec);
    }
    
}
