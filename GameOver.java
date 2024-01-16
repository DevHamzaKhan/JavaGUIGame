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

public class GameOver extends JPanel implements ActionListener{
	JButton okay;
	GameImage background;
	JLabel title, subtitle;
	
    public GameOver(String winner, GameImage background) {
        this.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
        this.setLayout(null);
        this.setFocusable(true);
		
		this.background = background;
		
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
        okay.setBounds(Main.WIDTH / 2 - 100, 370, 200, 40);
        okay.setContentAreaFilled(true);
        okay.setForeground(Color.BLACK);
        okay.addActionListener(this);
        okay.setFocusable(true);
        this.add(okay);
    }
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okay) {
            Main.showCard("Menu");
			Main.removeCard(this);
        }
    }
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		
		background.draw(g);
	}  
}