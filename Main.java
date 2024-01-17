import java.awt.*;
import javax.swing.*;

public class Main {

    // Window size
    public static final int WIDTH = 960;
    public static final int HEIGHT = 520;

    static CardLayout cardLayout;
    static JPanel p = new JPanel(new CardLayout());

    public static void main(String[] args) {

        JFrame f = new JFrame("Smash");
		
		Menu mainMenu = new Menu();
		Smash gameScreen = new Smash();
		Select selectScreen = new Select();
		Tutorial tutorialPage = new Tutorial();
		GameOver p1Win = new GameOver("Player 1");
		GameOver p2Win = new GameOver("Player 2");

        f.add(p, BorderLayout.CENTER);
		
		addCard(mainMenu, "Menu");
		addCard(gameScreen, "Smash");
		addCard(selectScreen, "Select");
		addCard(tutorialPage, "Tutorial");
		addCard(p1Win, "P1Win");
		addCard(p2Win, "P2Win");
		
        
        f.setVisible(true);
        f.setSize(WIDTH, HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

        cardLayout = (CardLayout) p.getLayout();
        showCard("Menu");
    }

    static public void nextCard() {
        cardLayout.next(p);
    }

    static public void showCard(String name) {
        cardLayout.show(p, name);
    }

    static public void addCard(JPanel jPanel, String name) {
        p.add(jPanel, name);
    }
    static public void removeCard(JPanel jPanel) {
        p.remove(jPanel);
    }
}
