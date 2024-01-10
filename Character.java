/*
Programmers: Hamza Khan & Leo Chen
Program Name: Character Version 1
Program Date: 12/21/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

public class Character {
	static public int width = 30, height = 30, jumpHeight = 100;
	
    public int x, y, jumpCounter, health;
	boolean isJumping; 
	
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        this.isJumping = false;
        this.jumpCounter = 0;
		this.health = 100;
    }
	
	public void move(boolean[] keysPressed, boolean isOnPlatform) {
		if (keysPressed[1] && x > 0) {
             // Adjust the speed of left movement
            x -= 5;
        }
        if (keysPressed[3] && x < 400 - width) {
            // Adjust the speed of right movement
            x += 5;
        }
		if (!isOnPlatform && !isJumping) {
			y += 5;
		}
	}
	
    public void jump() {
        isJumping = true;
        new Thread(new Runnable() {
            public void run() {
                while (jumpCounter <= Character.jumpHeight) {
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
}
