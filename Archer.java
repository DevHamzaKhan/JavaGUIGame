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
