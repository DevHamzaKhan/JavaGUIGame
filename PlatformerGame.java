import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.util.ArrayList;

import java.util.List;



public class PlatformerGame extends JFrame {

    private static final int WIDTH = 800;

    private static final int HEIGHT = 600;

    private static final int CHARACTER_WIDTH = 30;

    private static final int CHARACTER_HEIGHT = 30;

    private static final int PLATFORM_WIDTH = 100;

    private static final int PLATFORM_HEIGHT = 20;

    private static final int GRAVITY = 2;



    private int characterX = 50;

    private int characterY = HEIGHT - CHARACTER_HEIGHT;

    private int platformY = HEIGHT - PLATFORM_HEIGHT;



    private boolean isJumping = false;

    private boolean isMovingLeft = false;

    private boolean isMovingRight = false;

    private int jumpCount = 0;



    private List<Rectangle> platforms;



    public PlatformerGame() {

        setTitle("Platformer Game");

        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        platforms = createPlatforms();



        GamePanel gamePanel = new GamePanel();

        add(gamePanel);



        addKeyListener(new KeyListener() {

            @Override

            public void keyTyped(KeyEvent e) {

            }



            @Override

            public void keyPressed(KeyEvent e) {

                handleKeyPress(e);

            }



            @Override

            public void keyReleased(KeyEvent e) {

                handleKeyRelease(e);

            }

        });



        Timer timer = new Timer(10, new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                update();

                gamePanel.repaint();

            }

        });

        timer.start();

    }



    private List<Rectangle> createPlatforms() {

        List<Rectangle> platforms = new ArrayList<>();

        platforms.add(new Rectangle(50, HEIGHT - 100, 150, PLATFORM_HEIGHT));

        platforms.add(new Rectangle(250, HEIGHT - 200, 150, PLATFORM_HEIGHT));

        platforms.add(new Rectangle(450, HEIGHT - 300, 150, PLATFORM_HEIGHT));

        return platforms;

    }



    private void handleKeyPress(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            isMovingLeft = true;

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            isMovingRight = true;

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && jumpCount < 2) {

            jump();

        }

    }



    private void handleKeyRelease(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            isMovingLeft = false;

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            isMovingRight = false;

        }

    }



    private void moveLeft() {

        characterX -= 5;

        if (characterX < 0) {

            characterX = 0;

        }

    }



    private void moveRight() {

        characterX += 5;

        if (characterX > WIDTH - CHARACTER_WIDTH) {

            characterX = WIDTH - CHARACTER_WIDTH;

        }

    }



    private void jump() {

        isJumping = true;

        jumpCount++;

        Timer jumpTimer = new Timer(10, new ActionListener() {

            private int jumpHeight = 0;



            @Override

            public void actionPerformed(ActionEvent e) {

                characterY -= 5;

                jumpHeight += 5;

                if (jumpHeight >= 100) {

                    isJumping = false;

                    ((Timer) e.getSource()).stop();

                }

            }

        });

        jumpTimer.start();

    }



    private void update() {

        if (isMovingLeft) {

            moveLeft();

        }

        if (isMovingRight) {

            moveRight();

        }



        if (isJumping || characterY < platformY - CHARACTER_HEIGHT) {

            characterY += GRAVITY;

        } else {

            characterY = platformY - CHARACTER_HEIGHT;

            jumpCount = 0;

        }



        checkCollision();

    }



    private void checkCollision() {

        Rectangle characterRect = new Rectangle(characterX, characterY, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        for (Rectangle platform : platforms) {

            if (characterRect.intersects(platform)) {

                characterY = platform.y - CHARACTER_HEIGHT;

                jumpCount = 0;

            }

        }

    }



    private class GamePanel extends JPanel {

        @Override

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            drawCharacter(g);

            drawPlatforms(g);

        }



        private void drawCharacter(Graphics g) {

            g.setColor(Color.BLUE);

            g.fillRect(characterX, characterY, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        }



        private void drawPlatforms(Graphics g) {

            g.setColor(Color.GREEN);

            for (Rectangle platform : platforms) {

                g.fillRect(platform.x, platform.y, platform.width, platform.height);

            }

        }

    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override

            public void run() {

                new PlatformerGame().setVisible(true);

            }

        });

    }

}