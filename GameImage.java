import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameImage {
    BufferedImage image;
    int x, y, width, height;

    public GameImage(String imagePath, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        loadImage(imagePath);
    }

    public void loadImage(String imagePath) {
        try {
            URL resource = getClass().getResource(imagePath);
            if (resource != null) {
                image = ImageIO.read(resource);
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
}