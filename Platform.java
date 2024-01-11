/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Basic Platform creation with all attributes and variable to check if active platform.
*/
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public class Platform {
    int x; int y; int width; int height; int type; int active;
    BufferedImage image;

    public Platform(int x, int y, int width, int height, int active) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.active = active;
        this.LoadImage("platform.png");
    }

    public void LoadImage(String imagePath) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            System.out.println("LoadImage");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D graphic) {
        graphic.drawImage(image, x, y, width, height, null);
        System.out.println("print graphic");
    }
}
