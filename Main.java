
package project2simse;

import javax.swing.*;

/*
This class creates an instance of the GUI and sets parameters of the window that is created
including size, title, resizable etc. This is also where the runtime exception is caught and 
prints out the error message
*/

public class Main {
    public static void main(String[] args) throws RuntimeException {
        final JPanel panel = new JPanel();
        try {
            GUI Box = new GUI ();

            Box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Box.setLocationRelativeTo(null);
            Box.setVisible(true);
            Box.setSize(500,200);
            Box.setTitle("Postfix expression converter");
            Box.setResizable(false);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
