/*
Programmers: Hamza Khan & Leo Chen
Program Name: GameOver
Program Date: 2024
Program Description: screen that displays winner
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver extends JPanel implements ActionListener {
	// declare variables
	JButton okay;
	GameImage background;
	JLabel title, subtitle;
	
	// constructor
    public GameOver(String winner) {
		// initialize screen
        this.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.setLayout(null);
		
		this.background = new GameImage("DefaultBackground.png", 0, 0, Main.WIDTH, Main.HEIGHT, false);
		
		// add text to display winner and button to continue
		title = new JLabel("Game Over", SwingConstants.CENTER);
        title.setBounds(Main.WIDTH / 2 - 300, 60, 600, 100);
		title.setFont(new Font("SansSerif", Font.BOLD, 72));
		title.setForeground(Color.BLACK);
        this.add(title);
		
		subtitle = new JLabel("Winner: " + winner, SwingConstants.CENTER);
        subtitle.setBounds(Main.WIDTH / 2 - 150, 200, 300, 50);
		subtitle.setFont(new Font("SansSerif", Font.BOLD, 36));
		subtitle.setForeground(Color.BLACK);
        this.add(subtitle);
		
		okay = new JButton("OK");
        okay.setBounds(Main.WIDTH / 2 - 100, 350, 200, 40);
        okay.setContentAreaFilled(true);
		okay.setFont(Main.PLAIN18);
        okay.setForeground(Color.BLACK);
		okay.setBackground(Color.ORANGE);
        okay.addActionListener(this);
        this.add(okay);
    }
	
	// check if okay button pressed
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okay) {
            Main.showCard("Menu");
        }
    }
	
	// paint method
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		background.draw(g);
	}  
}
