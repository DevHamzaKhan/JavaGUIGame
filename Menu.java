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

public class Menu extends JPanel implements ActionListener{
    //Base Constructor
	JButton start = new JButton("Start");
    JButton tutorial = new JButton("Tutorial");
    JButton back = new JButton("Exit");
	
    public Menu() {
        this.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.setLayout(null);
        this.setFocusable(false);

        start.setBounds(120, Main.HEIGHT-300, 400, 50);
        start.setContentAreaFilled(false);
        start.setForeground(Color.BLACK);
        start.addActionListener(this);
        start.setFocusable(false);
        this.add(start);

        tutorial.setBounds(120, Main.HEIGHT-200, 400, 50);
        tutorial.setContentAreaFilled(false);
        tutorial.setForeground(Color.BLACK);
        tutorial.addActionListener(this);
        tutorial.setFocusable(false);
        this.add(tutorial);

        back.setBounds(120, Main.HEIGHT-100, 400, 50);
        back.setContentAreaFilled(false);
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        back.setFocusable(false);
        this.add(back);
    }
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            Main.startGame();
        }
    }
}