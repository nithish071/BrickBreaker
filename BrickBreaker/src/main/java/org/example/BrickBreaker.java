package org.example;
import javax.swing.JFrame;
public class BrickBreaker {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePlay gamePlay = new GamePlay(); // Whole game will be implemented inside this class
        frame.setBounds(10,10,700,600);
        frame.setTitle("BrickBreaker");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePlay);
    }
}
