package ast;
import emitter.Emitter;

/**
 * Statement is an abstract class which is the parent class for various statement classes, e.g. Writeln, While, If, Assignment, Block
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public abstract class Statement {

    /**
     * compiles the statement using the Emitter parameter, emitting the MIPS
     * code to execute the statement
     * @param e Emitter to be used for emission of the MIPS code
     */
    public void compile(Emitter e) {
        throw new RuntimeException("Implement!");
    }
    
}
