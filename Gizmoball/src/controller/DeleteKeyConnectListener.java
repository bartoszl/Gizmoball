package controller;

import model.*;
import view.Board;
import view.IGUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteKeyConnectListener implements MouseListener, KeyListener {
    private IGUI bgui;
    private IGBallModel m;
    private IFlipper f;
    private IAbsorber abs;

    public DeleteKeyConnectListener(IGUI bgui, IGBallModel m) {
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
                        keys += ("'" + KeyEvent.getKeyText(kcf.getKeyID()) + "', ");
                    }
                }
                if(keys.length() > 2) {
                    keys = keys.substring(0, keys.length() - 2);
                }
                abs = null;
                b.requestFocus();
                bgui.setMessageColor(Color.BLACK);
                bgui.setMessage("This flipper is connected to keys "+keys+". Press key to remove connection to it.");
            } else if( m.getAbsorber() != null &&
                    x <= m.getAbsorber().getXBottomRight() && x >= m.getAbsorber().getXTopLeft() &&
                    y <= m.getAbsorber().getYBottomRight() && y >= m.getAbsorber().getYTopLeft()
                    ) {
                for(KeyConnectionAbs kca : m.getKeyConnectionsAbs()) {
                   keys += ("'" + KeyEvent.getKeyText(kca.getKeyID()) + "', ");
                }
                if(keys.length() > 2) {
                    keys = keys.substring(0, keys.length() - 2);
                }
                abs = m.getAbsorber();
                b.requestFocus();
                bgui.setMessageColor(Color.BLACK);
                bgui.setMessage("This absorber is connected to keys "+keys+". Press key to remove connection to it.");
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
        if(b.getAction() == Board.Action.KEY_DISCONNECT) {
            if(f != null) {
                boolean removed = false;
                for(KeyConnectionFlipper kcf : m.getKeyConnectionsFlipper()) {
                    if(kcf.getFlipper().equals(f) && (kcf.getKeyID() == keyEvent.getKeyCode())) {
                        m.getKeyConnectionsFlipper().remove(kcf);
                        bgui.setMessageColor(Color.GREEN);
                        bgui.setMessage("Key '" + KeyEvent.getKeyText(kcf.getKeyID()) + "' is removed!");
                        removed = true;
                        break;
                    }
                }
                if(!removed) {
                    bgui.setMessageColor(Color.RED);
                    bgui.setMessage("Key '" + KeyEvent.getKeyText(keyEvent.getKeyCode()) + "' is not connected " +
                            " to that flipper! ");
                }
            } else if(abs != null) {
                boolean removed = false;
                for(KeyConnectionAbs kca : m.getKeyConnectionsAbs()) {
                    if((kca.getKeyID() == keyEvent.getKeyCode())) {
                        m.getKeyConnectionsAbs().remove(kca);
                        bgui.setMessageColor(Color.GREEN);
                        bgui.setMessage("Key '" + KeyEvent.getKeyText(kca.getKeyID()) + "' is removed!");
                        removed = true;
                        break;
                    }
                }
                if(!removed) {
                    bgui.setMessageColor(Color.RED);
                    bgui.setMessage("Key '" + KeyEvent.getKeyText(keyEvent.getKeyCode()) + "' is not connected " +
                            " to that absorber! ");
                }
            } else {
                bgui.setMessageColor(Color.YELLOW);
                bgui.setMessage("No Component chosen yet");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}
