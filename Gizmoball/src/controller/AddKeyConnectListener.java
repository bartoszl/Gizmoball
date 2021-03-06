package controller;

import model.*;
import view.Board;
import view.IGUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddKeyConnectListener implements MouseListener, KeyListener {
    private IGUI bgui;
    private IGBallModel m;
    private IFlipper f;
    private IAbsorber abs;
    private MagicKeyListener mkl;

    public AddKeyConnectListener(IGUI bgui, IGBallModel m) {
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
            b.requestFocus();
            if(m.findFlipper(x, y) != null) {
                f = m.findFlipper(x, y);
                bgui.setMessageColor(Color.GREEN);
                bgui.setMessage("Flipper found! Now press key to connect it");
                abs = null;
            } else if( m.getAbsorber() != null &&
                    x <= m.getAbsorber().getXBottomRight() && x >= m.getAbsorber().getXTopLeft() &&
                            y <= m.getAbsorber().getYBottomRight() && y >= m.getAbsorber().getYTopLeft()
                    ) {
                abs = m.getAbsorber();
                bgui.setMessageColor(Color.GREEN);
                bgui.setMessage("Absorber found! Now press key to connect it");
                f = null;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.KEY_CONNECT) {
            if(f != null) {
                if(m.addKeyConnectionFlipper(keyEvent.getKeyCode(), f, "down")) {
                    bgui.setMessageColor(Color.GREEN);
                    bgui.setMessage("Connected key '"+KeyEvent.getKeyText(keyEvent.getKeyCode())+"' to flipper!");
                } else {
                    bgui.setMessageColor(Color.RED);
                    bgui.setMessage("Error! Key might have already been connected");
                }


            } else if(abs != null) {
                if(m.addKeyConnectionAbs(keyEvent.getKeyCode(), abs, "down")) {
                    bgui.setMessageColor(Color.GREEN);
                    m.setConnectedToAbs(false);
                    bgui.setMessage("Connected key '"+KeyEvent.getKeyText(keyEvent.getKeyCode())+"' to absorber!");
                } else {
                    bgui.setMessageColor(Color.RED);
                    bgui.setMessage("Error! Key might have already been connected");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}
