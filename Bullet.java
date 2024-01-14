import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle{
	static int width = 6, height = 4;
    int direction, speed;

    public Bullet(int x, int y, int direction, int speed) {
		super(x, y, Bullet.width, Bullet.height);
        this.direction = direction;
		this.speed = speed;
    }
}