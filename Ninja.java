/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Ninja extends Character {
	public static int speed = 5, 
	public static final int WIDTH = 50, HEIGHT = 80, X_OFFSET = 20, Y_OFFSET = 20, ATK_DMG = 5, RGD_DMG = 3;
	
	public Ninja(int x, int y, int horizontalFacing) {
		super(x, y, horizontalFacing);
		
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
		for (int i = 0; i < animations[4].length; i++) { // character_runL
			animations[4][i] = new SpriteImage("RunAnimation/tile00" + i + ".png", x, y, 96, 96, true),
		}
		for (int i = 0; i < animations[5].length; i++) { // character_attackL
			animations[5][i] = new SpriteImage("AttackAnimation/tile00" + i + ".png", x, y, 96, 96, true),
		}
		for (int i = 0; i < animations[6].length; i++) { // character_shootL
			animations[6][i] = new SpriteImage("ShootAnimation/tile00" + i + ".png", x, y, 96, 96, true),
		}
		for (int i = 0; i < animations[7].length; i++) { // character_idleL
			animations[7][i] = new SpriteImage("IdleAnimation/tile00" + i + ".png", x, y, 96, 96, true),
		}

	}

    public void draw(Graphics g){
        switch(state){
            case "right":
                animations[0][spriteCounter/animationSpeed].draw(g);
                break;
            case "attackR":
                animations[1][spriteCounter/animationSpeed].draw(g);
                break;
            case "shootR":
                animations[2][spriteCounter/animationSpeed].draw(g);
                break;
            case "idleR":
                animations[3][spriteCounter/animationSpeed].draw(g);
                break;
			case "left":
                animations[4][spriteCounter/animationSpeed].draw(g);
                break;
			case "attackL":
                animations[5][spriteCounter/animationSpeed].draw(g);
                break;
            case "shootL":
                animations[6][spriteCounter/animationSpeed].draw(g);
                break;
			case "idleL":
                animations[7][spriteCounter/animationSpeed].draw(g);
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
