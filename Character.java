/*
Programmers: Hamza Khan & Leo Chen
Program Name: Character Version 1
Program Date: 12/21/2023
Program Description: Bases of the character class in the platformer fighting game, will expand on later adding more components
*/

public class Character {
    int x; int y; int width; int height; int jumpHeight; boolean isJumping; int jumpCounter;
    public Character(int x, int y, int width, int height, int jumpHeight, boolean isJumping, int jumpCounter) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.jumpHeight = jumpHeight;
        this.isJumping = isJumping;
        this.jumpCounter = jumpCounter;
    }
    public void startJump() {
        isJumping = true;
        new Thread(new Runnable() {
            public void run() {
                while (jumpCounter <= jumpHeight) {
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
                while (jumpCounter > 0) {
                    y += 5;
                    jumpCounter -= 5;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                y = Math.max(y, 0);
                isJumping = false;
            }
        }).start();
    }
}
