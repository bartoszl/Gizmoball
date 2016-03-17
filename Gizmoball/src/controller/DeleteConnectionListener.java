package controller;

import model.*;
import view.Board;
import view.BuildGUI;
import view.IBuildGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteConnectionListener implements MouseListener {
    private IBuildGUI bgui;
    private IGBallModel m;

    public DeleteConnectionListener(IBuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.DISCONNECT) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            x -= x%20;
            y -= y%20;
            Flipper f = m.findFlipper(x, y);
            if(f != null) {
                for(Connection c : m.getConnections()) {
                    if(c.getFlipper().equals(f)) {
                        System.out.println("Disconnected");
                        m.getConnections().remove(c);
                        break;
                    }
                }
            } else if(m.getAbsorber() != null &&
                    x < m.getAbsorber().getXBottomRight() && x > m.getAbsorber().getXTopLeft() &&
                    y < m.getAbsorber().getYBottomRight() && y > m.getAbsorber().getYTopLeft()
                    ) {
                m.getAbsorber().setConnectedToItself(false);
                System.out.println("Disconnected!");
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
