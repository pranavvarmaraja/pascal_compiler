package ast;
import java.util.List;
import emitter.Emitter;

/**
 * The Program class is an AST class representing an entire PASCAL program, thus 
 * it serves as the root of the AST. It stores a list of procedureDeclarations at 
 * the start of the program as well as the main program statement.
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public class Program {

    private List<String> variables; 
    private List<ProcedureDeclaration> declarations;
    private Statement stmt;

    /**
     * constructor for a program object, takes in a list of declarations and a statement
     * @param vars List<String> of variableNames at the start of the file
     * @param declarations List<ProcedureDeclaration> of procedureDeclarations at the start of the file
     * @param stmt Statement body statement to be run as part of the "main method"
     */
    public Program(List<String> vars, List<ProcedureDeclaration> declarations, Statement stmt) {

        this.variables = vars;
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
     * getter which returns the list of variables in the program
     * @return List<String> list of variable names at the beginning of the file
     */
    public List<String> getVariables() {
        return variables;
    }

    /**
     * getter which returns the body statement of the program
     * @return Statement statement to be run within the main program
     */
    public Statement getStatement() {
        return stmt;
    }

    /**
     * compiles the program using an Emitter , emitting the MIPS
     * code to evaluate the entirety of the pgoram
     * @param outputFileName String name of the file to emit code to
     */
    public void compile(String outputFileName) {

        Emitter e = new Emitter(outputFileName);

        e.emit(".data");
        e.emit("newLine: .asciiz \"\\n\"");
        for(String var: variables) {
            e.emit("var"+var + ": .word 0");
        }
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main:");
        getStatement().compile(e);
        e.emit("li $v0 10");
        e.emit("syscall");
        for(ProcedureDeclaration procedure: getProcedures()) {
            procedure.compile(e);
        }


    }
    
}
