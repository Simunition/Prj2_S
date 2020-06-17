/*
This creates a node type that accepts operands and can be a part of a 
binary tree that also includes OperatorNodes because they are both subclasses
of Node
*/
package project2simse;

public class OperandNode extends Node {
    private double value;
    
    public OperandNode(double value) {
        this.value = value;
    }
    
    public double evaluate() {
        return value;
    }
    
    public String preOrderWalk() {
        return String.valueOf(value);
    }

    public String inOrderWalk() {
        return String.valueOf(value);
    }

    public String postOrderWalk() {
        return String.valueOf(value);
    }
    
    public String getString() {
        return String.valueOf(value);
    }
    
    public String createAddressFile() {
        return String.valueOf(value);
    }
}
