/*
Programmers: Hamza Khan & Leo Chen
Program Name: Platform
Program Date: 2024-01-16
Program Description: creates platform object
*/

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

public class Platform {
	// declare variables
    private int x, y, width, height, type, active, realY; 
	private String imagePath;
    private BufferedImage image;
	
	// constructor
    public Platform(int x, int y, int width, int height, int active, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.active = active;
        this.imagePath = imagePath;
        this.LoadImage(imagePath);
		this.realY = this.y + 20; // adjust hitbox of platform to better align with image
    }
	
	// method for loading platform visuals
    public void LoadImage(String imagePath) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("Images/" + imagePath));
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }
	
	// x getter method
	public int getX() {
		return x;
	}
	
	public int getWidth() {
		return width;
	}
	
	// active getter method
	public int getActive() {
		return active;
	}
	
	// realY getter method
	public int getRealY() {
		return realY;
	}
	
	// draw method
    public void draw(Graphics2D graphic) {
        graphic.drawImage(image, x, y, width, height * 4, null);
    }
}
