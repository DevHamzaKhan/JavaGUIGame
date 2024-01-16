/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Character {
    public String state;
	public int x, y, horizontalFacing, health;
	boolean isJumping, canAttack, canShoot;
    public int jumpHeight, spriteCounter, animationSpeed, burnSpeed,, frame;
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public Music[] sfx = {new Music("shot.wav", 1), new Music("attack.wav", 1)};
	
    public int x, y, jumpCounter, health, horizontalFacing, facing, speed, gravity, jump;
	public boolean isJumping, canAttack, canShoot; 
	
   public Character(int x, int y, int horizontalFacing) {
        this.x = x;
        this.y = y;
		this.horizontalFacing = horizontalFacing;
        this.isJumping = false;
		this.canAttack = true;
		this.canShoot = true;
        this.jumpCounter = 0;
		this.health = 100;
		
		this.jumpHeight = 100;
		this.spriteCounter = 0;
		this.animationSpeed = 4;
		this.burnSpeed = 150;
		this.frame = 0;
		
		if (horizontalFacing == 0) {
			state = "idleL";
		}
		else {
			state = "idleR";
		}
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
		if ((!isOnPlatform(platforms) || keysPressed[2]) && !isJumping && (y + height) < platforms[0].realY) {
			y += 5;
		}
		
		if (keysPressed[0]) {
			facing = 1;
		}
		else if (keysPressed[2]) {
			facing = 2;
		}
		else {
			facing = 0;
		}
		
		updateBullets(enemyBullets);
	}
	public void burn(){
        if ((frame % burnSpeed) == 0){
            health -= 1;
        }
        frame += 1;
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
        sfx[1].start();
		canAttack = false;

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
        sfx[0].start();
		canShoot = false;
		if (horizontalFacing == 0) {
			bullets.add(new Bullet(this.x - Bullet.width, this.y + this.height / 2 - Bullet.height / 2, horizontalFacing, 5));
		}
		else {
			bullets.add(new Bullet(this.x + this.width, this.y + this.height / 2 - Bullet.height / 2, horizontalFacing, 5));
		}
	}
	
	public void takeDamage(Rectangle attack, int damage) {
		Rectangle hitbox = new Rectangle(x, y, width, height);
		
		if (hitbox.intersects(attack)){
			health -= damage;
		}
	}
	
	public boolean takeDamage(Bullet attack, int damage) {
		Rectangle hitbox = new Rectangle(x, y, width, height);
		
		if (hitbox.intersects(attack)){
			health -= damage;
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
			else if (bullet.x < 0 || bullet.x > Main.WIDTH) {
				enemyBullets.remove(i);
			}
		}
	}
	
	public boolean isOnPlatform(Platform[] platforms){
        for (Platform p : platforms) {
            if (p.active == 1){
                if (y + height >= p.realY &&
                    y + height <= p.realY + 5 &&
                    x + width >= p.x + 15 &&
                    x + width <= p.x + p.width + 30) {
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
