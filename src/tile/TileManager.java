package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    private GamePanel gamePanel;
    private Tile[] tile;
    private final int TILE_TYPES_AMOUNT = 10;
    private int tileMap[][];

    public TileManager(GamePanel gamePanel){

        this.gamePanel = gamePanel;
        tile = new Tile[TILE_TYPES_AMOUNT];
        tileMap = new int[gamePanel.getMaxScreenRow()][gamePanel.getMaxScreenColumn()];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    private void getTileImage(){

        try{

            for(int i = 0; i < TILE_TYPES_AMOUNT; i++){

                tile[i] = new Tile();
            }

            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        } catch (IOException exception) {

            exception.printStackTrace();
        }
    }

    private void loadMap(String filePath){

        try{

            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for(int row = 0; row < gamePanel.getMaxScreenRow(); row++){

                String line = bufferedReader.readLine();
                String[] characterArray = line.split(" ");

                for(int column = 0; column < gamePanel.getMaxScreenColumn(); column++){

                    int number = Integer.parseInt(characterArray[column]);

                    tileMap[row][column] = number;
                }
            }

            bufferedReader.close();

        }catch (Exception exception){

            exception.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D){

        for(int row = 0; row < gamePanel.getMaxScreenRow(); row++){

            for(int column = 0; column < gamePanel.getMaxScreenColumn(); column++){

                int x = gamePanel.getTileSize() * column;
                int y = gamePanel.getTileSize() * row;

                int currentTileIndex = tileMap[row][column];

                graphics2D.drawImage(tile[currentTileIndex].image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            }
        }
    }
}
