package ast;
import environment.Environment;

public class Evaluator {

    public Evaluator() {
    }

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

    public void execAssignment(Assignment stmt, Environment env) {
        env.setVariable(stmt.getVariable(), eval(stmt.getExpression(),env));
    }

    public void execBlock(Block stmt, Environment env) {
        for(Statement statement: stmt.getStatements()) {
            exec(statement,env);
        }
    }

    public void execWriteln(Writeln stmt, Environment env) {
        System.out.println(eval(stmt.getExpression(),env));
    }

    public int eval(Expression exp, Environment env) {
        if(exp instanceof Number) {
            return evalNumber((Number) exp, env);
        } else if(exp instanceof Variable) {
            return evalVariable((Variable) exp,env);
        } else {
            return evalBinOp((BinOp) exp,env);
        }
    }

    public int evalNumber(Number exp, Environment env) {
        return exp.getValue();
    }

    public int evalVariable(Variable exp, Environment env) {
        return env.getVariable(exp.getName());
    }

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

    public void execIf(If stmt, Environment env) {

        if(evalCondition(stmt.getCondition(),env)) {
            exec(stmt.getStatement(), env);
        }

    }

    public void execWhile(While stmt, Environment env) {

        while(evalCondition(stmt.getCondition(),env)) {
            exec(stmt.getStatement(), env);
        }
    }
}
