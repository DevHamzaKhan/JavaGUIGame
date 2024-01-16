<<<<<<< Updated upstream
public class Archer extends Character{
    Character owner;
    public Archer(int x, int y, int horizontalFacing, int speed, int gravity, int jump, int width, int height, int xOffset, int yOffset){
        super(x, y, horizontalFacing, speed, gravity, jump, width, height, xOffset, yOffset);
        
=======
/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Archer extends Character {
	public static int speed = 7, 
	public static final int WIDTH = 50, HEIGHT = 75, X_OFFSET = 40, Y_OFFSET = 60, ATK_DMG = 3, RGD_DMG = 5;
	public SpriteImage[][] animations;
	
    public Archer(int x, int y, int horizontalFacing) {
        this.x = x;
        this.y = y;
		this.horizontalFacing = horizontalFacing;
        this.speed = speed;
        this.isJumping = false;
		this.canAttack = true;
		this.canShoot = true;
        this.jumpCounter = 0;
		this.health = 100;
		
		if (horizontalFacing == 0) {
			state = "idleL";
		}
		else {
			state = "idleR";
		}
		
		animations = new SpriteImage[8][6];
		
		for (int i = 0; i < animations[0].length; i++) { // character_runR
			animations[0][i] = new SpriteImage("RunAnimation/tile00" + i + ".png", x, y, 96, 96, false),
		}
		for (int i = 0; i < animations[1].length; i++) { // character_attackR
			animations[1][i] = new SpriteImage("AttackAnimation/tile00" + i + ".png", x, y, 96, 96, false),
		}
		for (int i = 0; i < animations[2].length; i++) { // character_shootR
			animations[2][i] = new SpriteImage("ShootAnimation/tile00" + i + ".png", x, y, 96, 96, false),
		}
		for (int i = 0; i < animations[3].length; i++) { // character_idleR
			animations[3][i] = new SpriteImage("IdleAnimation/tile00" + i + ".png", x, y, 96, 96, false),
		}
		SpriteImage [] character_runL = {
			new SpriteImage(p1, "RunAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "RunAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "RunAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "RunAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "RunAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "RunAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
		};
		SpriteImage [] character_attackL = {
			new SpriteImage(p1, "AttackAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "AttackAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "AttackAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "AttackAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "AttackAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "AttackAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
		};
		SpriteImage [] character_shootL = {
			new SpriteImage(p1, "ShootAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "ShootAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "ShootAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "ShootAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "ShootAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "ShootAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
		};
		SpriteImage [] character_idleL = {
			new SpriteImage(p1, "IdleAnimation/tile000.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "IdleAnimation/tile001.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "IdleAnimation/tile002.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "IdleAnimation/tile003.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "IdleAnimation/tile004.png", p1.x, p1.y, 96, 96, true),
			new SpriteImage(p1, "IdleAnimation/tile005.png", p1.x, p1.y, 96, 96, true)
		};

    }

    public void draw(Graphics g){
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
>>>>>>> Stashed changes
    }
}
