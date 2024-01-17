/*
Programmers: Hamza Khan & Leo Chen
Program Name: Smash Version 2
Program Date: 1/10/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Samurai extends Character {
	public Samurai(int x, int y, int horizontalFacing) {
		super(x, y, horizontalFacing);
		
		speed = 3;
		width = 50;
		height = 80;
		xOffset = 20;
		yOffset = 20;
		atkDmg = 7;
		rgdDmg = 2;
		
		animations = new SpriteImage[8][6];
		
		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[0].length; j++) {
				animations[i][j] = new SpriteImage("SamuraiAnimations" + i % 4 + "/tile00" + j + ".png", x, y, 96, 96, i >= animations.length / 2);
			}
		}
	}
	
    public void draw(Graphics g){
		int drawX = this.x - xOffset;
		int drawY = this.y - yOffset;
		
        switch(state){
            case "right":
				animations[0][spriteCounter/animationSpeed].update(drawX, drawY);
                animations[0][spriteCounter/animationSpeed].draw(g);
                break;
            case "attackR":
				animations[1][spriteCounter/animationSpeed].update(drawX, drawY);
                animations[1][spriteCounter/animationSpeed].draw(g);
                break;
            case "shootR":
				animations[2][spriteCounter/animationSpeed].update(drawX, drawY);
                animations[2][spriteCounter/animationSpeed].draw(g);
                break;
            case "idleR":
				animations[3][spriteCounter/animationSpeed].update(drawX, drawY);
                animations[3][spriteCounter/animationSpeed].draw(g);
                break;
			case "left":
				animations[4][spriteCounter/animationSpeed].update(drawX, drawY);
                animations[4][spriteCounter/animationSpeed].draw(g);
                break;
			case "attackL":
				animations[5][spriteCounter/animationSpeed].update(drawX, drawY);
                animations[5][spriteCounter/animationSpeed].draw(g);
                break;
            case "shootL":
				animations[6][spriteCounter/animationSpeed].update(drawX, drawY);
                animations[6][spriteCounter/animationSpeed].draw(g);
                break;
			case "idleL":
				animations[7][spriteCounter/animationSpeed].update(drawX, drawY);
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
