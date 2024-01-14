/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Character {
    public String state = "idleR";
	public int width, height; 
    public int jumpHeight = 100, spriteCounter = 0, animationSpeed = 4;
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
    public int x, y, jumpCounter, health, horizontalFacing, facing, speed, gravity, jump;
	boolean isJumping; 
	
    public Character(int x, int y, int horizontalFacing, int speed, int gravity, int jump, int width, int height) {
        this.x = x;
        this.y = y;
		this.horizontalFacing = horizontalFacing;
        this.speed = speed;
        this.gravity = gravity;
        this.jump = jump;
        this.width = width;
        this.height = height;
        this.isJumping = false;
        this.jumpCounter = 0;
		this.health = 100;
    }
	
	public void updateCharacter(boolean[] keysPressed, Platform[] platforms, ArrayList<Bullet> enemyBullets) {
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
		
		updateBullets(enemyBullets);
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
			return new Rectangle(x, y - height, width, height);
		}
		else if (facing == 2) {
			return new Rectangle(x, y + height, width, height);
		}
		else if (horizontalFacing == 0) {
            state = "attackL";
			return new Rectangle(x - width, y, width, height);
		}
		else {
            state = "attackR";
			return new Rectangle(x + width, y, width, height);
		}
	}
	
	public void shoot() {
		if (horizontalFacing == 0) {
			bullets.add(new Bullet(this.x - Bullet.width, this.y + this.height / 2 - Bullet.height / 2, horizontalFacing, 5));
		}
		else {
			bullets.add(new Bullet(this.x + this.width, this.y + this.height / 2 - Bullet.height / 2, horizontalFacing, 5));
		}
	}
	
	public void takeDamage(Rectangle attack) {
		Rectangle hitbox = new Rectangle(x, y, width, height);
		
		if (hitbox.intersects(attack)){
			health -= 10;
		}
	}
	
	public boolean takeDamage(Bullet attack) {
		Rectangle hitbox = new Rectangle(x, y, width, height);
		
		if (hitbox.intersects(attack)){
			health -= 10;
			return true;
		}
		
		return false;
	}
	
	public void updateBullets(ArrayList<Bullet> enemyBullets) {
		Rectangle hitbox = new Rectangle(x, y, width, height);
		
		for (int i = 0; i < enemyBullets.size(); i++) {
			Bullet bullet = enemyBullets.get(i);
			
			if (bullet.direction == 0) {
				bullet.x -= bullet.speed;
			}
			else {
				bullet.x += bullet.speed;
			}
			
			if (takeDamage(bullet)) {
				enemyBullets.remove(i);
			}
		}
	}
	
	public boolean isOnPlatform(Platform[] platforms){
        for (Platform p : platforms) {
            if (p.active == 1){
                if (y + height >= p.y &&
                    y + height <= p.y + 5 &&
                    x + width >= p.x &&
                    x <= p.x + p.width) {
                        return true;
                }
            }
        }
        return false;
    }
    public void draw(Graphics g, GameImage [] walkLeft, GameImage [] walkRight, GameImage [] attackLeft, GameImage [] attackRight, GameImage [] idleLeft, GameImage [] idleRight, GameImage [] shootLeft, GameImage [] shootRight ){
        switch(state){
            case "right":
                walkRight[spriteCounter/animationSpeed].draw(g);
                break;
            case "left":
                walkLeft[spriteCounter/animationSpeed].draw(g);
                break;
            case "attackR":
                attackRight[spriteCounter/animationSpeed].draw(g);
                break;
            case "attackL":
                attackLeft[spriteCounter/animationSpeed].draw(g);
                break;
            case "idleR":
                idleRight[spriteCounter/animationSpeed].draw(g);
                break;
            case "idleL":
                idleLeft[spriteCounter/animationSpeed].draw(g);
                break;
            case "shootR":
                shootRight[spriteCounter/animationSpeed].draw(g);
                break;
            case "shootL":
                shootLeft[spriteCounter/animationSpeed].draw(g);
                break;
        }
        spriteCounter += 1;
        if (spriteCounter == (5*animationSpeed)){
            if (state.equals("attackL")){
                state = "idleL";
            } else if (state.equals("attackR")){
                state = "idleR";
            } else if (state.equals("shootL")){
                state = "idleL";
            } else if (state.equals("shootR")){
                state = "idleR";
            }
            spriteCounter = 0;
        }
    }
}
