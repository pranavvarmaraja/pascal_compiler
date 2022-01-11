package ast;

public class Number extends Expression {

    private int value;
    
    public Number(int val) {
        
        value = val;
        
    }

    public int getValue() {
        return value;
    }
    
}
