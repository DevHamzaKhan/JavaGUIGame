/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 1
Program Date: 12/21/2023
Program Description: Platformer game where character can move around and jump
*/

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Smash extends JFrame {

    public boolean[] p1KeysPressed = new boolean[2]; // [A, D]
	public boolean[] p2KeysPressed = new boolean[2]; // [J, L]
    
	// change when playtform class is created
	private int platformY = 320;
    private int platformWidth = 400;
	//

    //Character
    Character p1 = new Character(50, 0);
	Character p2 = new Character(250, 0);

    //Base Constructor
    public Smash() {
        setTitle("Smash Game");
        setSize(400, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SmashPanel smashPanel = new SmashPanel();
        add(smashPanel);

        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                smashPanel.repaint();
            }
        });
        timer.start();

        //Key listener and Inputs
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
			
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e);
            }
        });

        setFocusable(true);
    }

    public void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            p1KeysPressed[0] = true;
        } else if (key == KeyEvent.VK_D) {
            p1KeysPressed[1] = true;
        } else if (key == KeyEvent.VK_W && isOnPlatform(p1)) {
            p1.jump();
        } else if (key == KeyEvent.VK_J) {
            p2KeysPressed[0] = true;
        } else if (key == KeyEvent.VK_L) {
            p2KeysPressed[1] = true;
        } else if (key == KeyEvent.VK_I && isOnPlatform(p2)) {
            p2.jump();
        } 
    }

    public void handleKeyRelease(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            p1KeysPressed[0] = false;
        } else if (key == KeyEvent.VK_D) {
            p1KeysPressed[1] = false;
        } else if (key == KeyEvent.VK_J) {
            p2KeysPressed[0] = false;
        } else if (key == KeyEvent.VK_L) {
            p2KeysPressed[1] = false;
        }
    }

    

    public void update() {
		// move to character class once platform class is made
        if (!isOnPlatform(p1) && !p1.isJumping) {
             // Adjust the speed of falling
            p1.y += 5;
        }
		if (!isOnPlatform(p2) && !p2.isJumping) {
             // Adjust the speed of falling
            p2.y += 5;
        }
		//

        // Horizontal movement control
        p1.move(p1KeysPressed);
		p2.move(p2KeysPressed);
    }

	// move to character class once platform class is made
    public boolean isOnPlatform(Character Player) {
        return Player.y + Player.height >= platformY &&
               Player.y + Player.height <= platformY + 5 &&
               Player.x + Player.width >= 0 &&
               Player.x <= platformWidth - Player.width;
    }
	//

    // Gameplay Panel
    public class SmashPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw ground
            g.setColor(Color.BLUE);
            g.fillRect(0, getHeight() - 20, getWidth(), 20);

            // Draw platform
            g.setColor(Color.GREEN);
            g.fillRect(0, platformY, platformWidth, 5);

            // Draw character
            g.setColor(Color.RED);
            g.fillRect(p1.x, p1.y, Character.width, Character.height);
			g.fillRect(p2.x, p2.y, Character.width, Character.height);
        }
    }

    // Main game loop
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Smash().setVisible(true);
            }
        });
    }
}