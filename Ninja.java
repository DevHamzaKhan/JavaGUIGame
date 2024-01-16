<<<<<<< Updated upstream
public class Ninja {
    
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

public class Ninja extends Character {
	public static int speed = 5, 
	public static final int WIDTH = 50, HEIGHT = 80, X_OFFSET = 20, Y_OFFSET = 20, ATK_DMG = 5, RGD_DMG = 3;

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
>>>>>>> Stashed changes
}
