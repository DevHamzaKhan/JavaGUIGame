/*
Programmers: Hamza Khan & Leo Chen
Program Name: Bullet
Program Date: 2024-01-16
Program Description: class for creating bullets
*/

import java.awt.Rectangle;

public class Bullet extends Rectangle{
	// declare variables and initialize constants
	public static final int WIDTH = 10, HEIGHT = 6;
    private int direction, damage;
	
	// constructor
    public Bullet(int x, int y, int direction, int damage) {
		super(x, y, Bullet.WIDTH, Bullet.HEIGHT);
        this.direction = direction;
		this.damage = damage;
    }
	
	// direction getter method
	public int getDirection() {
		return direction;
	}
	
	// damage getter method
	public int getDamage() {
		return damage;
	}
}
