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

    public boolean isLeftKeyPressed = false;
    public boolean isRightKeyPressed = false;
    private int platformY = 320;
    private int platformWidth = 400;

    //Character
    Character p1 = new Character(50, 0, 30, 30, 100, false, 0);

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
        if (key == KeyEvent.VK_LEFT) {
            isLeftKeyPressed = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            isRightKeyPressed = true;
        } else if (key == KeyEvent.VK_SPACE && !p1.isJumping && isOnPlatform()) {
            p1.startJump();
        }
    }

    public void handleKeyRelease(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isLeftKeyPressed = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            isRightKeyPressed = false;
        }
    }

    

    public void update() {
        if (!isOnPlatform() && !p1.isJumping) {
             // Adjust the speed of falling
            p1.y += 5;
        }

        // Horizontal movement control
        if (isLeftKeyPressed && p1.x > 0) {
             // Adjust the speed of left movement
            p1.x -= 5;
        }
        if (isRightKeyPressed && p1.x < getWidth() - p1.width) {
            // Adjust the speed of right movement
            p1.x += 5;
        }
        
        // Boundaries to keep the player in the map
        p1.x = Math.max(p1.x, 0);
        p1.x = Math.min(p1.x, getWidth() - p1.width);
        p1.y = Math.min(p1.y, getHeight() - p1.height);
    }

    public boolean isOnPlatform() {
        return p1.y + p1.height >= platformY &&
               p1.y + p1.height <= platformY + 5 &&
               p1.x + p1.width >= 0 &&
               p1.x <= platformWidth - p1.width;
    }

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
            g.fillRect(p1.x, p1.y, p1.width, p1.height);
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