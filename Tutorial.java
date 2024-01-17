/*
Programmers: Hamza Khan & Leo Chen
Program Name: Tutorial
Program Date: 2024-01-16
Program Description: class for tutorial page
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tutorial extends JPanel implements ActionListener{
	// declare variables
	JButton back;
    GameImage tutorialImage; 
	
	// constructor
    public Tutorial() {
		// initialize screen
        this.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.setLayout(null);
        this.setFocusable(false);
		
		tutorialImage = new GameImage("Tutorial.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
		
		// initialize button
		back = new JButton("Back");
        back.setBounds(Main.WIDTH - 150, Main.HEIGHT - 80, 100, 30);
        back.setContentAreaFilled(true);
        back.setForeground(Color.BLACK);
		back.setBackground(Color.ORANGE);
        back.addActionListener(this);
        back.setFocusable(false);
        this.add(back);
    }
	
	// check if back button pressed
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            Main.showCard("Menu");
        }
    }
	
	// paint method
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		tutorialImage.draw(g);
	}  
}
