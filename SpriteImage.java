public class SpriteImage extends GameImage{
    Character owner;
    public SpriteImage(String imagePath, int x, int y, int width, int height, boolean flip, Character owner){
        super(imagePath, x, y, width, height, flip);
        this.owner = owner;
    }
    public void update(){
        x = owner.x;
        y = owner.y;
    }
}
