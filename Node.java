/*
Creates a superclass Node that is a parent to OperandNode and OperatorNode so that they
can both be part of the same binary Tree. Also implements the various abstract methods that are
required by both sub classes
*/
package project2simse;

public abstract class Node {
    public abstract double evaluate();
    public abstract String preOrderWalk();
    public abstract String inOrderWalk();
    public abstract String postOrderWalk();
    public abstract String getString();
    public abstract String createAddressFile();
}