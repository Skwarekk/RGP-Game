package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class GamePanel extends JPanel {

    // SCREEN SETTINGS
    private final int ORIGINAL_TILE_SIZE = 16; // 16 x 16 tile
    private final int SCALE = 3;

    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    private final int MAX_SCREEN_COLUMN = 16;
    private final int MAX_SCREEN_ROW = 12;

    private final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN; // 768 px
    private final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 px

    public GamePanel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
