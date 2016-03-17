package controller;

import model.*;
import view.Board;
import view.BuildGUI;
import view.IBuildGUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddKeyConnectListener implements MouseListener, KeyListener {
    private IBuildGUI bgui;
    private IGBallModel m;
    private int mouseX;
    private int mouseY;
    private Flipper f;
    private Absorber abs;
    private MagicKeyListener mkl;

    public AddKeyConnectListener(IBuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
        this.mkl = new MagicKeyListener(this);
    }



    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.KEY_CONNECT) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            x -= x%20;
            y -= y%20;
            mouseX = x;
            mouseY = y;
            b.requestFocus();
            if(m.findFlipper(x, y) != null) {
                f = m.findFlipper(x, y);
                bgui.setMessage("Flipper found! Now press key to connect it");
            } else if( m.getAbsorber() != null &&
                    x <= m.getAbsorber().getXBottomRight() && x >= m.getAbsorber().getXTopLeft() &&
                            y <= m.getAbsorber().getYBottomRight() && y >= m.getAbsorber().getYTopLeft()
                    ) {
                abs = m.getAbsorber();
                bgui.setMessage("Absorber found! Now press key to connect it");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.KEY_CONNECT) {
            if(f != null) {
                m.addKeyConnectionFlipper(keyEvent.getKeyCode(), f, "down");
                bgui.setMessage("Connected key '"+KeyEvent.getKeyText(keyEvent.getKeyCode())+"' to flipper!");
                f = null;
            } else if(abs != null) {
                m.addKeyConnectionAbs(keyEvent.getKeyCode(), abs, "down");
                m.setConnectedToAbs(false);
                bgui.setMessage("Connected key '"+KeyEvent.getKeyText(keyEvent.getKeyCode())+"' to absorber!");
                abs = null;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
