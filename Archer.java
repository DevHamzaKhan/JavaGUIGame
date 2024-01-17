/*
Programmers: Hamza Khan & Leo Chen
Program Name: Archer
Program Date: 2024-01-16
Program Description: Character class for Archer character
*/

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Archer extends Character {
	// constructor
	public Archer(int x, int y, int horizontalFacing) {
		super(x, y, horizontalFacing);
		
		// Archer stats
		speed = 7;
		width = 50;
		height = 75;
		xOffset = 40;
		yOffset = 60;
		atkDmg = 3;
		rgdDmg = 5;
		
		// load Archer animations
		animations = new SpriteImage[8][6];
		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[0].length; j++) {
				animations[i][j] = new SpriteImage("ArcherAnimations" + i % 4 + "/tile00" + j + ".png", x, y, 128, 128, i >= animations.length / 2);
			}
		}
	}
}
