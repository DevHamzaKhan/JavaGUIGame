public class SpriteImage extends GameImage{
    Character owner;
    public SpriteImage(Character owner, String imagePath, int x, int y, int width, int height){
        super(imagePath, x, y, width, height);
        this.owner = owner;
    }
}
