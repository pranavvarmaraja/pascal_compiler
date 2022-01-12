package environment;

import java.util.HashMap;

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

    public Environment() {

        variables = new HashMap<String, Integer>();

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
        return variables.get(variable);
    }
    
}
