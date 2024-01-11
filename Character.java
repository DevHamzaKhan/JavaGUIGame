/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

import java.awt.Rectangle;

public class Character {
	static public int width = 30, height = 30;
    public int jumpHeight = 100;
	
    public int x, y, jumpCounter, health, horizontalFacing, facing, speed, gravity, jump;
	boolean isJumping; 
	
    public Character(int x, int y, int horizontalFacing, int speed, int gravity, int jump) {
        this.x = x;
        this.y = y;
		this.horizontalFacing = horizontalFacing;
        this.speed = speed;
        this.gravity = gravity;
        this.jump = jump;
        this.isJumping = false;
        this.jumpCounter = 0;
		this.health = 100;
    }
	
	public void updateCharacter(boolean[] keysPressed, Platform[] platforms, int mode) {
		if (keysPressed[1] && x > 0) {
             // Adjust the speed of left movement
            x -= speed;
			horizontalFacing = 0;
        }
        if (keysPressed[3] && x < 960 - width) {
            // Adjust the speed of right movement
            x += speed;
			horizontalFacing = 1;
        }
		if ((!isOnPlatform(platforms) || keysPressed[2]) && !isJumping && (y + width) < platforms[0].y) {
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
                while (jumpCounter <= (jumpHeight)) {
                    // Adjust the speed of falling
                    y -= gravity;
                    // Adjust the speed of jumping
                    jumpCounter += jump;
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
            if (p.active == 1){
                if (y + Character.height >= p.y &&
                    y + Character.height <= p.y + 5 &&
                    x + Character.width >= p.x &&
                    x <= p.x + p.width) {
                        return true;
                }
            }
        }
        return false;
    }
}
