public class Platform {
    int x; int y; int width; int height; int type; boolean active;
    public Platform(int x, int y, int width, int height, int type, boolean active) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.active = active;
    }
    public boolean isOnPlatform(Character c) {
        return c.y + Character.height >= this.y &&
               c.y + Character.height <= this.y + 5 &&
               c.x + Character.width >= 0 &&
               c.x <= this.width - Character.width;
    }
}
