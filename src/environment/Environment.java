package environment;

import java.util.HashMap;

import ast.ProcedureDeclaration;

/**
 * Environment is a PASCAL environment for Compilers and Interpreters (2021-2022) lab exercise 3
 * The environment stores all variables declared within a Pascal program and getVariable() can be called to get the value of a given variable
 * @author Pranav Varmaraja
 * @version 01/10/2022
 * Usage:
 * Environment env = new Environment();
 * env.setVariable(var,value)
 * env.getVariable(var,value)
 *
 */
public class Environment {

    HashMap<String, Integer> variables;
    HashMap<String, ProcedureDeclaration> procedures;
    Environment parent;

    public Environment() {

        variables = new HashMap<String, Integer>();
        procedures = new HashMap<String, ProcedureDeclaration>();
        parent = null;

    }

    public Environment(Environment env) {

        variables = new HashMap<String, Integer>();
        procedures = new HashMap<String, ProcedureDeclaration>();
        parent = env;

    }

    /**
     * adds the entry <variable, value> to the HashMap containing all variables and their values in the environment
     * @param variable variable name to be added to HashMap keyset
     * @param value variable value to be added to HashMap values
     */
    public void setVariable(String variable, int value) {

        variables.put(variable, value);

    }

    /**
     * returns the value of variable variable as given by the HashMap variables
     * @param variable variable whose value is to be queried
     * @return int value of the variable
     */
    public int getVariable(String variable) {

        if(variables.keySet().contains(variable)){
            return variables.get(variable);
        } else {
            return getParent().getVariable(variable);
        }
    }

    public void setProcedure(String name, ProcedureDeclaration declaration) {
        Environment rootEnv = this;
        if(rootEnv.getParent()==null) {
            procedures.put(name,declaration);
            return;
        }
        rootEnv = rootEnv.getParent();
        rootEnv.setProcedure(name, declaration);

        
    }

    public ProcedureDeclaration getProcedure(String name) {
        Environment rootEnv = this;
        if(rootEnv.getParent()==null) {
            return procedures.get(name);
        }
        rootEnv = rootEnv.getParent();
        return rootEnv.getProcedure(name);
    }

    public Environment getParent() {
        return parent;

    }
    

}
