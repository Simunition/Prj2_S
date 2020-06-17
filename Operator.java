/*
This class creates operators that can be passed into the operatorNode used in the event actions class
each operator class includes both the function of the operator and the String for it, which are used 
in the evaluate and walk methods in the OperatorNode class
*/
package project2simse;

public abstract class Operator {
   abstract public double evaluate(double x, double y);
}

class AddOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 + d2;
    }
    
    public String toString() {
        return "+";
    }
    
}
   
class MulOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 * d2;
    }
    
    public String toString() {
        return "*";
    }
    
}
   
class SubOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 - d2;
    }
    
    public String toString() {
        return "-";
    }
    
}
   
class DivOperator extends Operator {
    public double evaluate(double d1, double d2) {
        return d1 / d2;
    }
    
    public String toString() {
        return "/";
    }
    
}


