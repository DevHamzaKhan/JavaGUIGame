/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 1
Program Date: 12/21/2023
Program Description: Platformer game where character can move around and jump
*/

//Imports
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Smash extends JFrame {

    public boolean[] p1KeysPressed = new boolean[4]; // [W, A, S, D, E]
	public boolean[] p2KeysPressed = new boolean[4]; // [I, J, K, L, O]

    //Character
    Character p1 = new Character(50, 0);
	Character p2 = new Character(250, 0);

<<<<<<< HEAD
    Platform [] platforms = {
        new Platform(0, 320, 400, 5, 1, true),
        new Platform(100, 200, 200, 5, 1, true)
=======
    Platform[] platforms = {
        new Platform(0, 320, 400, 5, 1, true)
>>>>>>> 9250c32e5d47c3c194ca57bdc405c34172e5cd09
    };

    //Base Constructor
    public Smash() {
        setTitle("Smash Game");
        setSize(960, 520);
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
    public boolean isOnPlatform(Character character, Platform [] platforms){
        for (Platform p : platforms) {
            if (character.y + Character.height >= p.y &&
                character.y + Character.height <= p.y + 5 &&
                character.x + Character.width >= 0 &&
                character.x <= p.width - Character.width == true) {
                    return true;
            }
        }
        return false;
    }
    public void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = true;
<<<<<<< HEAD
        } else if (key == KeyEvent.VK_W && isOnPlatform(p1, platforms)) {
            p1.jump();
=======
        } else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = true;
        } else if (key == KeyEvent.VK_W) {
            p1KeysPressed[0] = true;
			if (platforms[0].isOnPlatform(p1))
				p1.jump();
>>>>>>> 9250c32e5d47c3c194ca57bdc405c34172e5cd09
        } else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = true;
<<<<<<< HEAD
        } else if (key == KeyEvent.VK_I && isOnPlatform(p2, platforms)) {
            p2.jump();
=======
        } else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = true;
        } else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = true;
			if (platforms[0].isOnPlatform(p2))
				p2.jump();
>>>>>>> 9250c32e5d47c3c194ca57bdc405c34172e5cd09
        } 
    }

    public void handleKeyRelease(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = false;
        } else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = false;
        } else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = false;
        } else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = false;
        }
    }

    

    public void update() {
<<<<<<< HEAD
		// move to character class once platform class is made
        if (!isOnPlatform(p1, platforms) && !p1.isJumping) {
             // Adjust the speed of falling
            p1.y += 5;
        }
		if (!isOnPlatform(p2, platforms) && !p2.isJumping) {
             // Adjust the speed of falling
            p2.y += 5;
        }
		//

        // Horizontal movement control
        p1.move(p1KeysPressed);
		p2.move(p2KeysPressed);
=======
		// Movement control
        p1.move(p1KeysPressed, platforms[0].isOnPlatform(p1));
		p2.move(p2KeysPressed, platforms[0].isOnPlatform(p2));
>>>>>>> 9250c32e5d47c3c194ca57bdc405c34172e5cd09
    }


    // Gameplay Panel
    public class SmashPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Background Image
            //g.drawImage(backgroundImage, 0, 0, this);

            // Draw platform
            g.setColor(Color.GREEN);
            g.fillRect(platforms[0].x, platforms[0].y, platforms[0].width, platforms[0].height);
<<<<<<< HEAD
            g.setColor(Color.RED);
            g.fillRect(platforms[1].x, platforms[1].y, platforms[1].width, platforms[1].height);
=======
>>>>>>> 9250c32e5d47c3c194ca57bdc405c34172e5cd09

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