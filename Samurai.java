/*
Programmers: Hamza Khan & Leo Chen
Program Name: Samurai
Program Date: 2024-01-16
Program Description: Character class for Samurai character
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Samurai extends Character {
	// constructor
	public Samurai(int x, int y, int horizontalFacing) {
		super(x, y, horizontalFacing);
		
		// Samurai stats
		speed = 3;
		width = 50;
		height = 80;
		xOffset = 20;
		yOffset = 20;
		atkDmg = 7;
		rgdDmg = 2;
		
		// load Samurai animations
		animations = new SpriteImage[8][6];
		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[0].length; j++) {
				animations[i][j] = new SpriteImage("SamuraiAnimations" + i % 4 + "/tile00" + j + ".png", x, y, 96, 96, i >= animations.length / 2);
			}
		}
	}
}
