package ast;

public class Variable extends Expression{

    private String name;

    public Variable(String str) {
        name = str;
    }

    public String getName() {
        return name;
    }
    
}
