import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Rectangle{
	static int width = 10, height = 6;
    int direction, damage;

    public Bullet(int x, int y, int direction, int damage) {
		super(x, y, Bullet.width, Bullet.height);
        this.direction = direction;
		this.damage = damage;
    }
}