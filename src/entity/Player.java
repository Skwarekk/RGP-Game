package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    private void getPlayerImage(){

        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
        }catch (IOException exception){

            exception.printStackTrace();
        }
    }

    public void update(){

        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){
            if(keyHandler.upPressed){

                direction = "up";
                y -= speed;
            } else if(keyHandler.downPressed){

                direction = "down";
                y += speed;
            } else if(keyHandler.leftPressed){

                direction = "left";
                x -= speed;
            } else if(keyHandler.rightPressed){

                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if(spriteCounter > animationInterval){

                spriteNumber = switch (spriteNumber){

                    case 1 -> 2;
                    case 2 -> 1;
                    default -> 0;
                };

                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D){

        BufferedImage image = switch (direction) {
            case "up" -> switch (spriteNumber) {
                case 1 -> up1;
                case 2 -> up2;
                default -> null;
            };
            case "down" -> switch (spriteNumber) {
                case 1 -> down1;
                case 2 -> down2;
                default -> null;
            };
            case "left" -> switch (spriteNumber) {
                case 1 -> left1;
                case 2 -> left2;
                default -> null;
            };
            case "right" -> switch (spriteNumber) {
                case 1 -> right1;
                case 2 -> right2;
                default -> null;
            };
            default -> null;
        };

        graphics2D.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
