/*
Programmers: Hamza Khan & Leo Chen
Program Name: Character Version 1
Program Date: 12/21/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

import java.awt.Rectangle;

public class Character {
	static public int width = 30, height = 30, jumpHeight = 100;
	
    public int x, y, jumpCounter, health, horizontalFacing, facing;
	boolean isJumping; 
	
    public Character(int x, int y, int horizontalFacing) {
        this.x = x;
        this.y = y;
		this.horizontalFacing = horizontalFacing;
        this.isJumping = false;
        this.jumpCounter = 0;
		this.health = 100;
    }
	
	public void updateCharacter(boolean[] keysPressed, Platform[] platforms) {
		if (keysPressed[1] && x > 0) {
             // Adjust the speed of left movement
            x -= 5;
			horizontalFacing = 0;
        }
        if (keysPressed[3] && x < 400 - width) {
            // Adjust the speed of right movement
            x += 5;
			horizontalFacing = 1;
        }
		if ((!isOnPlatform(platforms) || keysPressed[2]) && !isJumping) {
			y += 5;
		}
		
		if (keysPressed[0]) {
			facing = 1;
		}
		else if (keysPressed[1]) {
			facing = 2;
		}
		else {
			facing = 0;
		}
	}
	
    public void jump() {
        isJumping = true;
        new Thread(new Runnable() {
            public void run() {
                while (jumpCounter <= Character.jumpHeight) {
                    // Adjust the speed of falling
                    y -= 5;
                    // Adjust the speed of jumping
                    jumpCounter += 5;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                jumpCounter = 0;
                isJumping = false;
            }
        }).start();
    }
	
	public Rectangle attack() {
		if (facing == 1) {
			return new Rectangle(x, y - Character.height, Character.width, Character.height);
		}
		else if (facing == 2) {
			return new Rectangle(x, y + Character.height, Character.width, Character.height);
		}
		else if (horizontalFacing == 0) {
			return new Rectangle(x - Character.width, y, Character.width, Character.height);
		}
		else {
			return new Rectangle(x + Character.width, y, Character.width, Character.height);
		}
		
	}
	
	public void takeDamage(Rectangle attack) {
		Rectangle hitbox = new Rectangle(x, y, Character.width, Character.height);
		
		if (hitbox.intersects(attack)){
			health -= 10;
		}
	}
	
	public boolean isOnPlatform(Platform[] platforms){
        for (Platform p : platforms) {
            if (y + Character.height >= p.y &&
                y + Character.height <= p.y + 5 &&
                x + Character.width >= p.x &&
                x + Character.width <= p.x + p.width) {
                    return true;
            }
        }
        return false;
    }
}
