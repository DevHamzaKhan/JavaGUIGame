/*
Programmers: Hamza Khan & Leo Chen
Program Name: GameImage
Program Date: 2024-01-16
Program Description: class for creating images
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameImage {
	// declare variables
    private BufferedImage image;
    protected int x, y, width, height;
    private boolean flip;
	
	// constructor
    public GameImage(String imagePath, int x, int y, int width, int height, boolean flip) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.flip = flip;
        loadImage("Images/" + imagePath);
    }

	// method for loading image
    public void loadImage(String imagePath) {
        try {
            URL resource = getClass().getResource(imagePath);
            if (resource != null) {
                image = ImageIO.read(resource);
                if (flip == true){
                    image = horizontalFlip(image); // flip horizontally if necessary
                }
            } else {
                throw new IOException("Resource not found: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	// draw method
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

	// method for flipping image horizontally
    public static BufferedImage horizontalFlip(BufferedImage img) {
		// declare variables
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();
		
		// flip image
        BufferedImage flippedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(img, newWidth, 0, 0, newHeight, 0, 0, newWidth, newHeight, null);
        g.dispose();
        
        return flippedImage;
    }
}
