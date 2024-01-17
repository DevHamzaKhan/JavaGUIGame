/*
Programmers: Hamza Khan & Leo Chen
Program Name: MainMenu
Program Date: 2024-01-16
Program Description: main menu of program
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel implements ActionListener{
    // declare variables
	JButton start = new JButton("Start");
    JButton tutorial = new JButton("Tutorial");
    JButton exit = new JButton("Exit");
	JButton select = new JButton("Select");
	
	JLabel title;
	
	GameImage background = new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
	
	// constructor
    public Menu() {
		// initialize screen
        this.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.setLayout(null);
        this.setFocusable(false);
		
		// add buttons and title
		title = new JLabel("Duel Bros", SwingConstants.CENTER);
        title.setBounds(Main.WIDTH / 2 - 150, 60, 300, 50);
		title.setFont(Main.BOLD48);
		title.setForeground(Color.BLACK);
		title.setBackground(Color.ORANGE);
        this.add(title);

        start.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-375, 200, 40);
        start.setContentAreaFilled(true);
		start.setFont(Main.PLAIN18);
        start.setForeground(Color.BLACK);
		start.setBackground(Color.ORANGE);
        start.addActionListener(this);
        start.setFocusable(false);
        this.add(start);
		
		select.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-300, 200, 40);
        select.setContentAreaFilled(true);
		select.setFont(Main.PLAIN18);
        select.setForeground(Color.BLACK);
		select.setBackground(Color.ORANGE);
        select.addActionListener(this);
        select.setFocusable(false);
        this.add(select);

        tutorial.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-225, 200, 40);
        tutorial.setContentAreaFilled(true);
		tutorial.setFont(Main.PLAIN18);
        tutorial.setForeground(Color.BLACK);
		tutorial.setBackground(Color.ORANGE);
        tutorial.addActionListener(this);
        tutorial.setFocusable(false);
        this.add(tutorial);

        exit.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-150, 200, 40);
        exit.setContentAreaFilled(true);
		exit.setFont(Main.PLAIN18);
        exit.setForeground(Color.BLACK);
		exit.setBackground(Color.ORANGE);
        exit.addActionListener(this);
        exit.setFocusable(false);
        this.add(exit);
    }
	
	// check buttons and change to corresponding screen
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            Main.showCard("Duel Bros");
        }
		else if (e.getSource() == select) {
			Main.showCard("Select");
		}
		else if (e.getSource() == tutorial) {
			Main.showCard("Tutorial");
		}
		else if (e.getSource() == exit) {
			System.exit(0);
		}
    }
	
	// paint method
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		background.draw(g);
	}  
}
