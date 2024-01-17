/*
Programmers: Hamza Khan & Leo Chen
Program Name: SpriteImage
Program Date: 2024-01-16
Program Description: class sprite images
*/

public class SpriteImage extends GameImage{
	// constructor
    public SpriteImage(String imagePath, int x, int y, int width, int height, boolean flip){
        super("Animations/" + imagePath, x, y, width, height, flip);
    }
	
	// method for updating sprite
    public void update(int newX, int newY){
        x = newX;
        y = newY;
    }
}
