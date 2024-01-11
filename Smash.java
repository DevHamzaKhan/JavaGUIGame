/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Platformer game where 2 characters can move around and jump onto platforms. There are multiple different maps with effects.
*/

//Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Smash extends JFrame {
    public boolean[] p1KeysPressed = new boolean[4]; // [W, A, S, D, E]
	public boolean[] p2KeysPressed = new boolean[4]; // [I, J, K, L, O]
    public int screen_width = 960;
    public int screen_height = 520;
    public int mode = (int)Math.round((Math.random() * 4));
    Color platform_color;

    //Character
    Character p1 = new Character(50, 0, 1, 5, 5, 5);
	Character p2 = new Character(250, 0, 0, 5, 5, 5);

    Platform [] platforms = {
        new Platform(0, 460, 960, 20, 1),
        new Platform(90, 360, 200, 10, (int)Math.round(Math.random())),
        new Platform(380, 360, 200, 10, (int)Math.round(Math.random())),
        new Platform(670, 360, 200, 10, (int)Math.round(Math.random())),
        new Platform(186, 260, 200, 10, (int)Math.round(Math.random())),
        new Platform(572,  260, 200, 10, (int)Math.round(Math.random())),
        new Platform(380, 160, 200, 10, (int)Math.round(Math.random()))
	};
	
    //Base Constructor
    public Smash() {
        setTitle("Smash Game");
        setSize(screen_width, screen_height);
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

        GameMode();

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
            p1KeysPressed[1] = true;
        } 
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = true;
        } 
		else if (key == KeyEvent.VK_W) {
            p1KeysPressed[0] = true;
			if (p1.isOnPlatform(platforms))
				p1.jump();
        }
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_E) {
			p2.takeDamage(p1.attack());
		} 
		else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = true;
        } 
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = true;
        } 
		else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = true;
			if (p2.isOnPlatform(platforms))
				p2.jump();
        } 
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = true;
		}
		else if (key == KeyEvent.VK_O) {
			p1.takeDamage(p2.attack());
		} 
    }

    public void handleKeyRelease(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            p1KeysPressed[1] = false;
        } 
		else if (key == KeyEvent.VK_D) {
            p1KeysPressed[3] = false;
        } 
		else if (key == KeyEvent.VK_W) {
			p1KeysPressed[0] = false;
		} 
		else if (key == KeyEvent.VK_S) {
			p1KeysPressed[2] = false;
		}
		else if (key == KeyEvent.VK_J) {
            p2KeysPressed[1] = false;
        } 
		else if (key == KeyEvent.VK_L) {
            p2KeysPressed[3] = false;
        }
		else if (key == KeyEvent.VK_I) {
			p2KeysPressed[0] = false;
		}
		else if (key == KeyEvent.VK_K) {
			p2KeysPressed[2] = false;
		}
    }

    

    public void update() {
		// Movement control
        p1.updateCharacter(p1KeysPressed, platforms, mode);
		p2.updateCharacter(p2KeysPressed, platforms, mode);
    }

    public void GameMode(){
            switch (mode) {
                case 0:
                    platform_color = Color.RED;
                    break;
                case 1:
                    p1.speed /= 2;
                    p2.speed /= 2;
                    platform_color = Color.ORANGE;
                    break;
                case 2:
                    p1.speed *= 2;
                    p2.speed *= 2;
                    platform_color = Color.CYAN;
                    break;
                case 3:
                    p1.jumpHeight = 150;
                    p2.jumpHeight = 150;
                    platform_color = Color.BLACK;
                    break;
            }
    }
    // Gameplay Panel
    public class SmashPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D graphics2d = (Graphics2D) g;
            // Draw platform
            g.setColor(platform_color);

            for (Platform p : platforms) {
                if (p.active == 1){
                    g.fillRect(p.x, p.y, p.width, p.height);
				}
            }

            // Draw character
            g.setColor(Color.GREEN);
            g.fillRect(p1.x, p1.y, Character.width, Character.height);
			g.fillRect(p2.x, p2.y, Character.width, Character.height);
			
			// Draw health bars
			if (p1.health >= 0) {
				g.fillRect(30, 20, 2 * p1.health, 30);
			}
			if (p2.health >= 0) {
				int hpBarWidth = 2 * p2.health;
				g.fillRect(screen_width - hpBarWidth - 30, 20, hpBarWidth, 30);
			}
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