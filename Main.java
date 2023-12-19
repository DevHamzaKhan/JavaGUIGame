import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements ActionListener, KeyListener {

    private int characterX = 50;
    private int characterY = 300;
    private double velocityY = 0;
    private double gravity = 0.5;

    private int characterSpeed = 5;
    private int jumpHeight = 10;
    private int maxJumpCount = 2;
    private int jumpCount = 0;

    private Timer timer;

    public Main() {
        setTitle("Smooth Platformer Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timer = new Timer(1000 / 60, this);
        timer.start();

        addKeyListener(this);

        setFocusable(true);
        requestFocus();
    }

    private void update() {
        // Apply gravity
        velocityY += gravity;
        characterY += velocityY;

        // Ground collision
        if (characterY > 300) {
            characterY = 300;
            velocityY = 0;
            jumpCount = 0;
        }
    }

    private void handleKeyPress(int key) {
        if (key == KeyEvent.VK_LEFT) {
            characterX -= characterSpeed;
        } else if (key == KeyEvent.VK_RIGHT) {
            characterX += characterSpeed;
        } else if (key == KeyEvent.VK_SPACE && jumpCount < maxJumpCount) {
            jump();
        }
    }

    private void jump() {
        velocityY = -jumpHeight;
        jumpCount++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Optional: Handle key releases if needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Optional: Handle key typed events if needed
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCharacter(g);
    }

    private void drawCharacter(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLUE);
        g.fillRect(characterX, (int) characterY, 30, 30);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}