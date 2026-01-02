package entity;

import java.awt.image.BufferedImage;

public class Entity {

    protected int worldX, worldY;
    protected int speed;

    protected String direction;

    protected BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;

    protected int spriteCounter = 0;
    protected int spriteNumber = 1;

    protected int animationInterval = 12;

    public int getWorldX() { return worldX; }

    public int getWorldY() { return worldY; }
}