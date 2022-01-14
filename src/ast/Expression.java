package ast;
import emitter.Emitter;
/**
 * Expression is an abstract class which is the parent class for various expression classes, e.g. Number, Variable, BinOp
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public abstract class Expression {

    /**
     * compiles the expression using the Emitter parameter, emitting the MIPS
     * code to evaluate the expression
     * @param e Emitter to be used for emission of the MIPS code
     */
    public void compile(Emitter e) {
        throw new RuntimeException("Implement!");
    }
    
}
