import java.util.HashMap;
public class Rectangle {
    private int WIDTH;
    private int HEIGHT;
    private int speedX = 3;
    private int speedY = 2;
    private int resistance = 1;
    public int x;
    public int y;
    public int color = 0xFF00FF;
    HashMap<String, Integer> direction = new HashMap<String, Integer>();

    public Rectangle(int w, int h, int x, int y, int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.x = x;
        this.y = y;
        this.direction.put("x", 1);
        this.direction.put("y", 1);
    }

    public void update () {
        if (this.x > WIDTH) {
            this.direction.put("x", -1);
            this.speedX += 1;
        } else if (this.x <= 0) {
            this.direction.put("x", 1);
            this.speedX += 1;
        }
        if (this.y > HEIGHT) {
            this.direction.put("y", -1);
            this.speedY += 1;
        } else if (this.y <= 0) {
            this.direction.put("y", 1);
            this.speedY += 1;
        }


        this.x += this.speedX * this.direction.get("x");
        this.y += this.speedY * this.direction.get("y");
    }
}
