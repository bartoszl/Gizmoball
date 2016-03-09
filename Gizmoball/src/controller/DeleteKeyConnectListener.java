package controller;

import model.*;
import view.Board;
import view.BuildGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteKeyConnectListener implements MouseListener {
    private BuildGUI bgui;
    private IGBallModel m;

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
            Flipper f = m.findFlipper(x, y);
            if(f != null) {
                for(KeyConnectionFlipper kcf: m.getKeyConnectionsFlipper()) {
                    if(kcf.getFlipper().equals(f)) {
                        System.out.println("Disconnected");
                        m.getKeyConnectionsFlipper().remove(kcf);
                        break;
                    }
                }
            } else if( m.getAbsorber() != null &&
                    x < m.getAbsorber().getXBottomRight() && x > m.getAbsorber().getXTopLeft() &&
                    y < m.getAbsorber().getYBottomRight() && y > m.getAbsorber().getYTopLeft()
                    ) {
                System.out.println("Disconnected");
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
}
