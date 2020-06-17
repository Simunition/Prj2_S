
package project2simse;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import static java.lang.Character.isDigit;
import java.util.Stack;
import javax.swing.*;
  

/*
This class creates a blueprint for the GUI and feeds into the event class. More on that below...
*/
    public class GUI extends JFrame {
        
        private final JLabel inputLabel, resultLabel, evaluationLabel;
        private JLabel resultOutput, evaluationOutput;
        private final JButton constructTree;
        private JTextField input;
        
        public GUI() {
            setLayout(new GridBagLayout());
            GridBagConstraints window = new GridBagConstraints();
            
            window.insets = new Insets (1,1,1,1);
            
            inputLabel = new JLabel("Enter Postfix Expression:");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 0;
            window.gridwidth = 2;
            add(inputLabel, window);
            
            resultLabel = new JLabel("         Infix Expression:");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 3;
            add(resultLabel, window);
            
            resultOutput = new JLabel("");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 2;
            window.gridy = 3;
            add(resultOutput, window);
            
            constructTree = new JButton("Construct Tree");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 2;
            window.gridy = 2;
            add(constructTree, window);
            
            input = new JTextField(20);
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 2;
            window.gridy = 0;
            window.gridwidth = 3;
            add(input, window);
            
            evaluationLabel = new JLabel("                           Result:");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 0;
            window.gridy = 4;
            add(evaluationLabel, window);
            
            evaluationOutput = new JLabel("");
            window.fill = GridBagConstraints.HORIZONTAL;
            window.gridx = 2;
            window.gridy = 4;
            add(evaluationOutput, window);
            
            event mathAction = new event();
            constructTree.addActionListener(mathAction);
        }
        
                /*
                The event class defines what happens when Construct Tree is pressed. The input is received and whitespace is removed.
                Then it's converted into an array of chars. A stack of nodes is created to start constructing 
                the tree char by char.
                If the char is an operand it is pushed into the node stack using the OperandNode method
                If the char is an operator, a switch case determines which operator it is then the appropriate 
                operator method is used to push an operatorNode into the treeStack, along with the top two operands.
                If anything other than an operand or operator is passed an error message appears and an exception is thrown.
                Finally the treeStack is converted to a regular Node which can then be used with the evaluate and 
                inOrderWalk methods to convert postfix to infix notation, and determine the correct result of the operation.
                To create the three address instruction file, I start with an outputFile String, and using the createAddressfile
                Method I populate the outputFile string each time an operator is processed. A * is entered at the end of each of these
                entries which I use to split the string and the file output method uses the string array to properly write the
                output file. 
                */
        public class event implements ActionListener {
            
            public void actionPerformed (ActionEvent mathAction) {
                String operators = mathAction.getActionCommand();
                
                
                if(operators.equals("Construct Tree")) {
                    
                    String outputFile = "";
                    
                    String initialInput = input.getText().replaceAll(" ", "");
                    char [] postfix = initialInput.toCharArray();
                    final JPanel panel = new JPanel();
                    Stack<Node> treeStack = new Stack<>();
                    int count = 0;
                    
                    for (int i = 0; i < postfix.length; i++) {
                        if (isOperand(postfix[i])) {
                            treeStack.push(new OperandNode(Double.valueOf(String.valueOf(postfix[i]))));
                        } else if (isOperator(postfix[i])){
                            Node rightOperand = treeStack.pop();  
                            Node leftOperand = treeStack.pop();                        
                            switch (postfix[i]) {
                                case '+': treeStack.push(new OperatorNode(new AddOperator(), leftOperand, rightOperand, count, "Add"));
                                break;
                                case '-': treeStack.push(new OperatorNode(new SubOperator(), leftOperand, rightOperand, count, "Sub"));
                                break;
                                case '*': treeStack.push(new OperatorNode(new MulOperator(), leftOperand, rightOperand, count, "Mul"));
                                break;
                                case '/': treeStack.push(new OperatorNode(new DivOperator(), leftOperand, rightOperand, count, "Div"));
                                break;
                            }
                            outputFile += treeStack.peek().createAddressFile();
                            count++;
                        } else {
                            JOptionPane.showMessageDialog(panel, "Invalid Token " + postfix[i], "Warning", JOptionPane.WARNING_MESSAGE);
                            throw new RuntimeException("Invalid Token " + postfix[i]);
                        }
                    }
                    
                    Node tree = treeStack.pop();
                    
                    resultOutput.setText(tree.inOrderWalk());
                    evaluationOutput.setText("" + tree.evaluate());
                    String[]outputText = outputFile.split("\\*");
                    fileOutput(outputText);
                } // end construct tree
            }

        }
        
        //Methods to determine if char is 
        //an operator or operand 
        
        public boolean isOperator (char x) {
            if (x == '*' || x == '/' || x == '+' || x == '-') {
                return true;
            } else  {
                return false;
            }
        }
        
        public boolean isOperand (char x) {
            if (isDigit(x)) {
                return true;
            } else  {
                return false;
            }
        }
        
        public void fileOutput (String[] st) {
            BufferedWriter bw = null;
            //FileWriter fr = null;
            try {
                File file = new File("ThreeAddressInstructions.txt");
                FileWriter fr = new FileWriter(file);
                bw = new BufferedWriter(fr);
                for(int i = 0; i < st.length; i++) {
                    bw.append(st[i]);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bw.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        

}


