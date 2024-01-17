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
	public int x, y, horizontalFacing, facing, jumpCounter, health;
	boolean isJumping, canAttack, canShoot;
    public int jumpHeight, spriteCounter, animationSpeed, burnSpeed, frame;
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public SpriteImage[][] animations;
	public Music attackSFX, shotSFX;
	public int speed, width, height, xOffset, yOffset, atkDmg, rgdDmg;
	
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
	
	public Rectangle attack() {
		attackSFX = new Music("attack.wav", 1);
        attackSFX.start();
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
		shotSFX = new Music("shot.wav", 1);
        shotSFX.start();
		canShoot = false;
		if (horizontalFacing == 0) {
			bullets.add(new Bullet(this.x - Bullet.width, this.y + height / 2 - Bullet.height / 2, horizontalFacing, rgdDmg));
		}
		else {
			bullets.add(new Bullet(this.x + width, this.y + height / 2 - Bullet.height / 2, horizontalFacing, rgdDmg));
		}
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(x, y, width, height);
	}
	
	public void takeDamage(Rectangle attack, int damage) {
		if (getHitbox().intersects(attack)){
			health -= damage;
		}
	}
	
	public boolean takeDamage(Bullet attack, int damage) {
		if (getHitbox().intersects(attack)){
			health -= damage;
			return true;
		}
		
		return false;
	}
	
	public void updateBullets(ArrayList<Bullet> enemyBullets) {
		for (int i = 0; i < enemyBullets.size(); i++) {
			Bullet bullet = enemyBullets.get(i);
			
			if (bullet.direction == 0) {
				bullet.x -= 5;
			}
			else {
				bullet.x += 5;
			}
			
			if (takeDamage(bullet, bullet.damage)) {
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
	
	public int getHealth() {
		return health;
	}
	
	public abstract void draw(Graphics g);
}
