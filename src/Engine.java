import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

public class Engine extends Canvas implements Runnable{
    private int ups = 60;
    private int WIDTH;
    private int HEIGHT;
    private int scale;
    private String title = "GameOfLife";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image;
    private int[] pixels;

    public Engine(int w, int h, int scale) {
        WIDTH = w;
        HEIGHT = h;
        this.scale = scale;
        setSize(WIDTH*scale,HEIGHT*scale);
        JFrame frame = new JFrame(title);
        BufferedImage icon = null;
        try {
            icon = ImageIO.read(getClass().getResource("icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setIconImage(icon);
        frame.add(this);

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        //this.addKeyListener(new MyKeyListener());
        //this.addMouseMotionListener(new MyMouseMotionListener());
        //this.addMouseListener(new MyMouseListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        this.requestFocus();
        frame.setVisible(true);
    }

    public void update() {

    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        // Rita ut den nya bilden
        g.drawImage(image, 0, 0, WIDTH*scale, HEIGHT*scale, null);
        g.dispose();
        bs.show();
    }

    public void drawPixel(int x, int y, int color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            pixels[y*WIDTH + x] = color;
        }
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        double ns = 1000000000.0 / ups;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                // Uppdatera koordinaterna
                update();
                // Rita ut bilden med updaterad data
                render();
                delta--;
            }
        }
        stop();
    }
}
