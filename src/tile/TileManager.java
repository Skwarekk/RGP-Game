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
        tileMap = new int[gamePanel.getMaxWorldRow()][gamePanel.getMaxWorldColumn()];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    private void getTileImage(){

        try{

            for(int i = 0; i < TILE_TYPES_AMOUNT; i++){

                tile[i] = new Tile();
            }

            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException exception) {

            exception.printStackTrace();
        }
    }

    private void loadMap(String filePath){

        try{

            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            for(int row = 0; row < gamePanel.getMaxWorldRow(); row++){

                String line = bufferedReader.readLine();
                String[] characterArray = line.split(" ");

                for(int column = 0; column < gamePanel.getMaxWorldColumn(); column++){

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

        for(int wordlRow = 0; wordlRow < gamePanel.getMaxWorldRow(); wordlRow++){

            for(int worldColumn = 0; worldColumn < gamePanel.getMaxWorldColumn(); worldColumn++){

                int currentTileIndex = tileMap[wordlRow][worldColumn];

                int worldX = worldColumn * gamePanel.getTileSize();
                int worldY = wordlRow * gamePanel.getTileSize();
                int screenX = worldX - gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX();
                int screenY = worldY - gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY();

                if(
                    worldX + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldX() - gamePanel.getPlayer().getScreenX() &&
                    worldX - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldX() + gamePanel.getPlayer().getScreenX() &&
                    worldY + gamePanel.getTileSize() > gamePanel.getPlayer().getWorldY() - gamePanel.getPlayer().getScreenY() &&
                    worldY - gamePanel.getTileSize() < gamePanel.getPlayer().getWorldY() + gamePanel.getPlayer().getScreenY()
                ){

                    graphics2D.drawImage(tile[currentTileIndex].image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                }
            }
        }
    }
}
