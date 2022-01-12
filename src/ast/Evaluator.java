package ast;
import environment.Environment;

/**
 * Evaluator is a class which evaluates the various AST classes returned by the parser
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public class Evaluator {

    /**
     * constructor, creates an instance of the evaluator
     */
    public Evaluator() {
    }

    /**
     * executes and evaluates a statement, calls various execute methods depending on what type of statement is encountered
     * @param stmt statement to be evaluated
     * @param env environment including all necessary variables
     */
    public void exec(Statement stmt, Environment env) {
        if(stmt instanceof Writeln) {
            execWriteln((Writeln) stmt, env);
        } else if(stmt instanceof Block) {
            execBlock((Block) stmt, env);
        } else if(stmt instanceof Assignment) {
            execAssignment((Assignment) stmt, env);
        } else if(stmt instanceof If) {
            execIf((If) stmt, env);
        } else if(stmt instanceof While) {
            execWhile((While) stmt, env);
        }
    } 

    /**
     * executes an assignment statement by adding the variable name and expression to the environment
     * @param stmt assignment statement to be evaluated
     * @param env environment in which all variables are contained
     */
    public void execAssignment(Assignment stmt, Environment env) {
        env.setVariable(stmt.getVariable(), eval(stmt.getExpression(),env));
    }

    /**
     * executes a block statement by iterating through the statement list, executing each statement individually
     * @param stmt Block statement to be evaluated
     * @param env environment in which all variables are contained
     */
    public void execBlock(Block stmt, Environment env) {
        for(Statement statement: stmt.getStatements()) {
            exec(statement,env);
        }
    }

    /**
     * executs a writeln statement by printing the result of the contained exprsession
     * @param stmt WRITELN statement to be evaluated
     * @param env environment in which all variables are contained
     */
    public void execWriteln(Writeln stmt, Environment env) {
        System.out.println(eval(stmt.getExpression(),env));
    }

    /**
     * evaluates an expression, calling the appropriate method depending on what expression is encountered
     * @param exp expression to be evaluated
     * @param env environment containing all relevant variables
     * @return the integer value of the resolved expression
     */
    public int eval(Expression exp, Environment env) {
        if(exp instanceof Number) {
            return evalNumber((Number) exp, env);
        } else if(exp instanceof Variable) {
            return evalVariable((Variable) exp,env);
        } else {
            return evalBinOp((BinOp) exp,env);
        }
    }

    /**
     * evaluates a number expression, returning the number contained within the AST class
     * @param exp number to be evaluated
     * @param env environment containing all relevant variables
     * @return integer value of the AST wrapped number
     */
    public int evalNumber(Number exp, Environment env) {
        return exp.getValue();
    }

    /**
     * evalues a variable expression, checking the environment for the given variable
     * @param exp Variable to be evaluated
     * @param env environment containing all relevant variables
     * @return integer value of the variable, whose value is already a resolved expression
     */
    public int evalVariable(Variable exp, Environment env) {
        return env.getVariable(exp.getName());
    }

    /**
     * evaluates a BinaryOp expression, completing the desired mathematical operation
     * @param exp BinaryOp to be evaluated
     * @param env environment containing all relevant variables
     * @return integer value of the binary operation
     */
    public int evalBinOp(BinOp exp, Environment env) {
        if(exp.getOp().equals("+")) {
            return eval(exp.getFirstExp(),env) + eval(exp.getSecondExp(),env);
        } else if(exp.getOp().equals("-")) {
            return eval(exp.getFirstExp(),env) - eval(exp.getSecondExp(),env);
        } else if(exp.getOp().equals("*")) {
            return eval(exp.getFirstExp(),env) * eval(exp.getSecondExp(),env);
        } else {
            return eval(exp.getFirstExp(),env) / eval(exp.getSecondExp(),env);
        }
    }

    /**
     * evaluates a condition in the form expr relop expr
     * @param cond condition to be evaluated
     * @param env environment containing all necessary variables
     * @return boolean value of the condition
     */
    public boolean evalCondition(Condition cond, Environment env) {
        switch (cond.getOp()) {
            case "=":
                return(eval(cond.exp1,env)==eval(cond.exp2,env));
            case "<>":
                return(eval(cond.exp1,env)!=eval(cond.exp2,env));
            case "<":
                return(eval(cond.exp1,env)<eval(cond.exp2,env));
            case ">":
                return(eval(cond.exp1,env)>eval(cond.exp2,env));
            case "<=":
                return(eval(cond.exp1,env)<=eval(cond.exp2,env));
            case ">=":
                return(eval(cond.exp1,env)>=eval(cond.exp2,env));

        }
        return true;
    }

    /**
     * evaluates an if statement, executing all statements within the statement if the condition is true
     * @param stmt If statement to be evaluated
     * @param env relevant environment containing all necessary variables
     */
    public void execIf(If stmt, Environment env) {

        if(evalCondition(stmt.getCondition(),env)) {
            exec(stmt.getStatement(), env);
        }

    }

    /**
     * evaluates a while statement, executing all statements within the statement while the condition is true
     * @param stmt While statement to be evaluated
     * @param env relevant environment containing all necessary variables
     */
    public void execWhile(While stmt, Environment env) {

        while(evalCondition(stmt.getCondition(),env)) {
            exec(stmt.getStatement(), env);
        }
    }
}
