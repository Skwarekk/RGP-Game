package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        setKeyPressed(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {

       setKeyPressed(e.getKeyCode(), false);
    }

    private void setKeyPressed(int keyCode, boolean value){

        if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){

            upPressed = value;
        }

        if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){

            downPressed = value;
        }

        if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){

            leftPressed = value;
        }

        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){

            rightPressed = value;
        }
    }
}
