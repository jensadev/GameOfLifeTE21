public class GameOfLife extends Engine {
    Engine e;
    Rectangle player;

    public GameOfLife(int w, int h, int scale)  {
        super(w,h,scale);

        this.player = new Rectangle(10, 10, 10, 10, w, h);
    }

    public void update() {
        clearPixel(this.player.x, this.player.y);
        this.player.update();
        drawPixel(this.player.x, this.player.y, this.player.color);
    }
}
