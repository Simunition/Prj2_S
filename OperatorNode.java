
package project2simse;

/*
This class is used to create parent nodes that include an operator as the parent
and either operands, or another parent operator node as the children
It also includes methods to print out prefix, postfix and infix strings using the properly built tree
as well as a method to actually evaluate the operation
Finally the regPosition variable is used in the three address instruction file print out
and OperatorString is used to print out the correct string prior to the registry address
Finally the getString and createAddressFile methods are used to print out the correct 
String for each operation that takes place in the operation, used in the file output
*/
public class OperatorNode extends Node {

    private Node left, right;
    private Operator operator;
    private String regPosition;
    private String operatorString;
   
    public OperatorNode(Operator operator, Node left,
                        Node right, int regIndex, String operatorString){
        this.operator = operator;
        this.left = left;
        this.right = right;
        this.regPosition = "R" + regIndex;
        this.operatorString = operatorString;
    }
    
    //Actual Result method
    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();
        return operator.evaluate(leftValue, rightValue);
    } 
    
    public String preOrderWalk() {
        String leftValue = left.preOrderWalk();
        String rightValue = right.preOrderWalk();
        return "" + operator + " " + leftValue + " "
                  + rightValue;
    }

    //Construct Tree method
    public String inOrderWalk() {
        String leftValue = left.inOrderWalk();
        String rightValue = right.inOrderWalk();
        return "(" + leftValue + " " + operator + " "
                   + rightValue + ")";
    }

    public String postOrderWalk() {
        String leftValue = left.postOrderWalk();
        String rightValue = right.postOrderWalk();
        return leftValue + " " + rightValue + " " + operator;
    }
    
    public String getString () {
        return regPosition;
    }
    
    public String createAddressFile() {
        return operatorString + " " + regPosition + " " + left.getString() + " " + right.getString() + "\n*";
    }
}
