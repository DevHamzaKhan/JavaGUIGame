import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
    Character owner;
    int x, y, velocity, width = 5, height = 5;

    public Bullet(int velocity, Character owner) {
        this.velocity = velocity;
        this.owner = owner;
    }

    public void DrawBullet(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }

    public void Shoot() {
        if (owner != null) {
            System.out.println("Bullet Shot");
            if (owner.horizontalFacing == 0) {
                x += velocity;
            } else {
                x -= velocity; 
            }
            System.out.println("Bullet Coordinates: (" + x + ", " + y + ")");
        }
    }

    public void CheckHit(Character C) {
        if (x < C.x + C.width && x + width > C.x && y < C.y + C.height && y + height > C.y) {
            C.health -= 5;
        }
    }
}