/*
Programmers: Hamza Khan & Leo Chen
Program Name: Main
Program Date: 2024-01-16
Program Description: platformer fighter game with multiple characters, ranged and melee combat, and random maps and modes
*/

import java.awt.*;
import javax.swing.*;

public class Main {
	// set screen size
    public static final int WIDTH = 960;
    public static final int HEIGHT = 520;
	
	// initialize screen layout
    static CardLayout cardLayout;
    static JPanel p = new JPanel(new CardLayout());

    public static void main(String[] args) {
        JFrame f = new JFrame("Duel Bros");
		
		// create screens
		Menu mainMenu = new Menu();
		Game gameScreen = new Game();
		Select selectScreen = new Select();
		Tutorial tutorialPage = new Tutorial();
		GameOver p1Win = new GameOver("Player 1");
		GameOver p2Win = new GameOver("Player 2");
		
        f.add(p, BorderLayout.CENTER);
		
		// add screens as cards
		addCard(mainMenu, "Menu");
		addCard(gameScreen, "Duel Bros");
		addCard(selectScreen, "Select");
		addCard(tutorialPage, "Tutorial");
		addCard(p1Win, "P1Win");
		addCard(p2Win, "P2Win");
		
        // initialize screen
        f.setVisible(true);
        f.setSize(WIDTH, HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
		
		// show starting menu
        cardLayout = (CardLayout) p.getLayout();
        showCard("Menu");
    }
	
	// method for changing cards
    static public void showCard(String name) {
        cardLayout.show(p, name);
    }
	
	// method for adding cards
    static public void addCard(JPanel jPanel, String name) {
        p.add(jPanel, name);
    }
	
	// create fonts
	public static final Font BOLD48 = new Font("SansSerif", Font.BOLD, 48);
    public static final Font BOLD24 = new Font("SansSerif", Font.BOLD, 24);
    public static final Font PLAIN24 = new Font("SansSerif", Font.PLAIN, 24);
	public static final Font PLAIN18 = new Font("SansSerif", Font.PLAIN, 18);
}
