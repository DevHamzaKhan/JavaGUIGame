/*
Programmers: Hamza Khan & Leo Chen
Program Name: Ninja
Program Date: 2024-01-16
Program Description: Character class for Ninja character
*/

public class Ninja extends Character {
	// constructor
	public Ninja(int x, int y, int horizontalFacing) {
		super(x, y, horizontalFacing);
		
		// Ninja stats
		speed = 5;
		width = 50;
		height = 80;
		xOffset = 20;
		yOffset = 20;
		atkDmg = 5;
		rgdDmg = 3;
		
		// load Ninja animations
		animations = new SpriteImage[8][6];
		for (int i = 0; i < animations.length; i++) {
			for (int j = 0; j < animations[0].length; j++) {
				animations[i][j] = new SpriteImage("NinjaAnimations" + i % 4 + "/tile00" + j + ".png", x, y, 96, 96, i >= animations.length / 2);
			}
		}
	}
}
