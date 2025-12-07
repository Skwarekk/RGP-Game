package main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    private final int ORIGINAL_TILE_SIZE = 16; // 16 x 16 tile
    private final int SCALE = 3;

    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    private final int MAX_SCREEN_COLUMN = 16;
    private final int MAX_SCREEN_ROW = 12;

    private final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN; // 768 px
    private final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 px

    // FPS
    private final int FPS = 60;
    private final long SECOND = 1000000000;

    private TileManager tileManager = new TileManager(this);
    private KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread;
    private Player player = new Player(this, keyHandler);

    public GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) SECOND / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCounter = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){

                update();
                repaint();
                delta--;
                drawCounter++;
            }

            if(timer >= SECOND){

                System.out.println("FPS: " + drawCounter);
                drawCounter = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        player.update();
    }

    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);
        player.draw(graphics2D);

        graphics2D.dispose();
    }

    // Getters

    public int getTileSize(){
        return TILE_SIZE;
    }

    public int getMaxScreenRow(){
        return MAX_SCREEN_ROW;
    }

    public int getMaxScreenColumn(){
        return MAX_SCREEN_COLUMN;
    }

    public int getScreenWidth(){
        return SCREEN_WIDTH;
    }

    public int getScreenHeight(){
        return SCREEN_HEIGHT;
    }
}
