package environment;

import java.util.HashMap;

public class Environment {

    HashMap<String, Integer> variables;

    public Environment() {

        variables = new HashMap<String, Integer>();

    }

    public void setVariable(String variable, int value) {

        variables.put(variable, value);

    }

    public int getVariable(String variable) {
        return variables.get(variable);
    }
    
}
