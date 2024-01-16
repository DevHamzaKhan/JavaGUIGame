/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Platformer game where 2 characters can move around and jump onto platforms. There are multiple different maps with effects.
*/

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tutorial extends JPanel implements ActionListener{
	JButton back;
	
    GameImage tutorialImage = new GameImage("Tutorial.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
	
    public Tutorial() {
        this.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.setLayout(null);
        this.setFocusable(false);
		
		back = new JButton("Back");
        back.setBounds(Main.WIDTH - 100, Main.HEIGHT - 70, 100, 30);
        back.setContentAreaFilled(true);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        back.setFocusable(false);
        this.add(back);
    }
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            Main.showCard("Menu");
        }
    }
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		tutorialImage.draw(g);
	}  
}