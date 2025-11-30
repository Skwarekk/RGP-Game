package main;

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

    private Thread gameThread;

    public GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while(gameThread != null){

            // 1. UPDATE: update game logic such as character position
            update();

            // 2. DRAW: draw the screen with the updated game logic
            repaint();
        }
    }

    public void update(){}

    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);
    }
}
