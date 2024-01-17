public class SpriteImage extends GameImage{
    Character owner;
    public SpriteImage(String imagePath, int x, int y, int width, int height, boolean flip){
        super(imagePath, x, y, width, height, flip);
    }
    public void update(int newX, int newY){
        x = newX;
        y = newY;
    }
}
