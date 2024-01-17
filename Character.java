/*
Programmers: Hamza Khan & Leo Chen
Program Name: Character
Program Date: 2024-01-16
Program Description: parent class for characters
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Character {
	// declare variables and initialize constants
	private int x, y, horizontalFacing;
	private int jumpCounter, jumpHeight, health, spriteCounter, animationSpeed, frame, facing;
	private int state; // 0 = runR, 1 = attackR, 2 = shootR, 3 = idleR, 4 = runL, etc.
	private boolean isJumping, canAttack, canShoot;
	protected int speed, width, height, xOffset, yOffset, atkDmg, rgdDmg; // character specific stats
	protected SpriteImage[][] animations;
	private Music attackSFX, shotSFX;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	private static final int BURN_SPEED = 150;
	
	// constructor
	public Character(int x, int y, int horizontalFacing) {
		// set starting position
        this.x = x;
        this.y = y;
		this.horizontalFacing = horizontalFacing;
		
        isJumping = false;
		canAttack = true;
		canShoot = true;
        jumpCounter = 0;
		jumpHeight = 100;
		health = 100;
		spriteCounter = 0;
		animationSpeed = 4;
		frame = 0;
		
		if (horizontalFacing == 0)
			state = 7;
		else
			state = 3;
    }
	
	// method for fire map burn
	public void burn(){
        if ((frame % BURN_SPEED) == 0){
            health -= 1;
        }
        frame += 1;
    }
	
	// jump method
    public void jump() {
        isJumping = true;
        new Thread(new Runnable() { // use thread to allow for simultaneous handling
            public void run() {
                while (jumpCounter <= jumpHeight) { // increase height until jumpCountrer reaches jumpHeight (jumpCounter tracks distanced travelled upwards)
                    y -= 5;
                    jumpCounter += 5;
					
                    try {
                        Thread.sleep(10);
                    } 
					catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
				// reset jump after jump is completed
                jumpCounter = 0;
                isJumping = false;
            }
        }).start();
    }
	
	// method for updating character
	public void updateCharacter(boolean[] keysPressed, Platform[] platforms, ArrayList<Bullet> enemyBullets) {
		// move left
		if (keysPressed[1] && x > 0) {
            x -= speed;
			horizontalFacing = 0;
        }
		
		// move right
        if (keysPressed[3] && x < 960 - width) {
            x += speed;
			horizontalFacing = 1;
        }
		
		// fall while not jumping and not on platform or while pressing down key
		if ((!isOnPlatform(platforms) || keysPressed[2]) && !isJumping && y + height < platforms[0].getRealY()) {
			y += 5;
		}
		
		// update facing
		if (keysPressed[0]) {
			facing = 1;
		}
		else if (keysPressed[2]) {
			facing = 2;
		}
		else {
			facing = 0;
		}
		
		// update enemy bullets to check if any collide with character
		updateBullets(enemyBullets);
	}
	
	// method for attack
	public Rectangle attack() {
		// initialize and start sound effect
		attackSFX = new Music("attack.wav", 1);
        attackSFX.start();
		
		// allow one attack per click of key
		canAttack = false;
		
		// attack in facing direction
		if (facing == 1) {
			return new Rectangle(x, y - height, width, height);
		}
		else if (facing == 2) {
			return new Rectangle(x, y + height, width, height);
		}
		else if (horizontalFacing == 0) {
            state = 5;
			return new Rectangle(x - width, y, width, height);
		}
		else {
            state = 1;
			return new Rectangle(x + width, y, width, height);
		}
	}
	
	// method for shooting
	public void shoot() {
		// initialize and start sound effect
		shotSFX = new Music("shot.wav", 1);
        shotSFX.start();
		
		// allow one shot per click of key
		canShoot = false;
		
		// shoot in horizontal facing direction by adding bullet to bullets
		if (horizontalFacing == 0)
			bullets.add(new Bullet(this.x - Bullet.WIDTH, this.y + height / 2 - Bullet.HEIGHT / 2, horizontalFacing, rgdDmg));
		else
			bullets.add(new Bullet(this.x + width, this.y + height / 2 - Bullet.HEIGHT / 2, horizontalFacing, rgdDmg));
	}
	
	// method for getting hitbox
	private Rectangle getHitbox() {
		return new Rectangle(x, y, width, height);
	}
	
	// check if enemy attack hits and take damage if it does
	public void takeDamage(Rectangle attack, int damage) {
		if (getHitbox().intersects(attack)){
			health -= damage;
		}
	}
	
	// check if enemy shot hits and take damage if it does; return boolean to help with bullet disposable
	public boolean takeDamage(Bullet attack, int damage) {
		if (getHitbox().intersects(attack)){
			health -= damage;
			return true;
		}
		
		return false;
	}
	
	// method for updating bullet position
	public void updateBullets(ArrayList<Bullet> enemyBullets) {
		for (int i = 0; i < enemyBullets.size(); i++) {
			Bullet bullet = enemyBullets.get(i);
			
			// update bullet
			if (bullet.getDirection() == 0) {
				bullet.x -= 5;
			}
			else {
				bullet.x += 5;
			}
			
			// check if bullet hits and remove bullet if it does
			if (takeDamage(bullet, bullet.getDamage())) {
				enemyBullets.remove(i);
			}
			// remove bullets that are outside of the screen
			else if (bullet.x < 0 || bullet.x > Main.WIDTH) {
				enemyBullets.remove(i);
			}
		}
	}
	
	// method for checking if character is on a platform
	public boolean isOnPlatform(Platform[] platforms){
        for (Platform p : platforms) {
            if (p.getActive() == 1){
                if (y + height >= p.getRealY() &&
                    y + height <= p.getRealY() + 5 &&
                    x + width >= p.getX() + 15 &&
                    x + width <= p.getX() + p.getWidth() + 30) {
                        return true;
                }
            }
        }
        return false;
    }
	
	// health getter method
	public int getHealth() {
		return health;
	}
	
	// horizontalFacing getter method
	public int getHorizontalFacing() {
		return horizontalFacing;
	}
	
	// canAttack getter method
	public boolean getCanAttack() {
		return canAttack;
	}
	
	// canShoot getter method
	public boolean getCanShoot() {
		return canShoot;
	}
	
	// atkDmg getter method
	public int getAtkDmg() {
		return atkDmg;
	}
	
	// state getter method
	public int getState() {
		return state;
	}
	
	// bullets getter method
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	// speed getter method
	public int getSpeed() {
		return speed;
	}
	
	// jumpHeight setter method
	public void setJumpHeight(int jumpHeight) {
		this.jumpHeight = jumpHeight;
	}
	
	// canAttack setter method
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}
	
	// canShoot setter method
	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}
	
	// state setter method
	public void setState(int state) {
		this.state = state;
	}
	
	// spriteCounter setter method
	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}
	
	// speed setter method
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	// draw method
    public void draw(Graphics g){
		// initialize variables for sprite
		int drawX = this.x - xOffset;
		int drawY = this.y - yOffset;
		
		// update and draw sprite
		animations[state][spriteCounter/animationSpeed].update(drawX, drawY);
        animations[state][spriteCounter/animationSpeed].draw(g);
		
		// change animation after certain animations
        spriteCounter += 1;
        if (spriteCounter == (5 * animationSpeed)){
			// change to corresponding idle after attacking or shooting
            if (state == 5){
                state = 7;
            } else if (state == 1){
                state = 3;
            } else if (state == 6){
                state = 7;
            } else if (state == 2){
                state = 3;
            }
            spriteCounter = 0;
        }
    }
}
