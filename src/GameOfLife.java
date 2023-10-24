public class GameOfLife extends Engine {
    Engine e;

    public GameOfLife(int w, int h, int scale)  {
        super(w,h,scale);
    }

    public void update() {
        for (int x = 10 ; x < 90 ; x++)
            drawPixel(x,50, 0xFF00FF);
    }
}
