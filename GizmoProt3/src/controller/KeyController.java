package controller;

import model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Stephen on 18/02/2016.
 *
 */
public class KeyController implements KeyListener {
    private Model model;

    public KeyController(Model model){
        this.model = model;
    }

    public void keyPressed(final KeyEvent e){
        //if(e.getKeyCode() == KeyEvent.VK_F) model.fireFromAbs();
    }
    public void keyReleased(final KeyEvent e){}
    public void keyTyped(final KeyEvent e){}
}
