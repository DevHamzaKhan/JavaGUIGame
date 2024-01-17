import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameImage {
    BufferedImage image;
    int x, y, width, height;
    boolean flip;

    public GameImage(String imagePath, int x, int y, int width, int height, boolean flip) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.flip = flip;
        loadImage("Images/" + imagePath);
    }

    public void loadImage(String imagePath) {
        try {
            URL resource = getClass().getResource(imagePath);
            if (resource != null) {
                image = ImageIO.read(resource);
                if (flip == true){
                    image = horizontalFlip(image);
                }
            } else {
                throw new IOException("Resource not found: " + imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    public static BufferedImage horizontalFlip(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        
        BufferedImage flippedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(img, width, 0, 0, height, 0, 0, width, height, null);
        g.dispose();
        
        return flippedImage;
    }
}