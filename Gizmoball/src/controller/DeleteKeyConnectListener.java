package controller;

import model.*;
import view.Board;
import view.BuildGUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteKeyConnectListener implements MouseListener, KeyListener {
    private BuildGUI bgui;
    private IGBallModel m;
    private IFlipper f;
    private IAbsorber abs;

    public DeleteKeyConnectListener(BuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.KEY_DISCONNECT) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            x -= x%20;
            y -= y%20;
            f = m.findFlipper(x, y);
            String keys = "";
            if(f != null) {
                for(KeyConnectionFlipper kcf: m.getKeyConnectionsFlipper()) {
                    if(kcf.getFlipper().equals(f)) {
                        keys += (KeyEvent.getKeyText(kcf.getKeyID()) + ", ");
                        //m.getKeyConnectionsFlipper().remove(kcf);
                        //break;
                    }
                }
                b.requestFocus();
                bgui.setMessage("This flipper is connected to keys "+keys+". Press key to remove connection to it.");
            } else if( m.getAbsorber() != null &&
                    x <= m.getAbsorber().getXBottomRight() && x >= m.getAbsorber().getXTopLeft() &&
                    y <= m.getAbsorber().getYBottomRight() && y >= m.getAbsorber().getYTopLeft()
                    ) {
                System.out.println("Disconnected");
                m.setConnectedToAbs(true);
                m.getKeyConnectionsAbs().clear();
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
        if(b.getAction() == Board.Action.KEY_DISCONNECT) {
            if(f != null) {
                boolean removed = false;
                for(KeyConnectionFlipper kcf : m.getKeyConnectionsFlipper()) {
                    if(kcf.getFlipper().equals(f) && (kcf.getKeyID() == keyEvent.getKeyCode())) {
                        m.getKeyConnectionsFlipper().remove(kcf);
                        bgui.setMessage("Key " + KeyEvent.getKeyText(kcf.getKeyID()) + " is removed!");
                        f = null;
                        removed = true;
                        break;
                    }
                }

                if(!removed) {
                    bgui.setMessage("Key " + KeyEvent.getKeyText(keyEvent.getKeyCode()) + " is not connected " +
                            " to that flipper! ");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
