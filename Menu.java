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
    JButton exit = new JButton("Exit");
	JButton select = new JButton("Select");
	JLabel title;
	
	GameImage background = new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
	
    public Menu() {
        this.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.setLayout(null);
        this.setFocusable(false);
		
		title = new JLabel("Smash", SwingConstants.CENTER);
        title.setBounds(Main.WIDTH / 2 - 100, 60, 200, 50);
		title.setFont(new Font("SansSerif", Font.BOLD, 48));
        this.add(title);

        start.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-375, 200, 40);
        start.setContentAreaFilled(true);
        start.setForeground(Color.BLACK);
        start.addActionListener(this);
        start.setFocusable(false);
        this.add(start);
		
		select.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-300, 200, 40);
        select.setContentAreaFilled(true);
        select.setForeground(Color.BLACK);
        select.addActionListener(this);
        select.setFocusable(false);
        this.add(select);

        tutorial.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-225, 200, 40);
        tutorial.setContentAreaFilled(true);
        tutorial.setForeground(Color.BLACK);
        tutorial.addActionListener(this);
        tutorial.setFocusable(false);
        this.add(tutorial);

        exit.setBounds(Main.WIDTH / 2 - 100, Main.HEIGHT-150, 200, 40);
        exit.setContentAreaFilled(true);
        exit.setForeground(Color.BLACK);
        exit.addActionListener(this);
        exit.setFocusable(false);
        this.add(exit);
    }
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            Main.showCard("Smash");
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
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		background.draw(g);
	}  
}