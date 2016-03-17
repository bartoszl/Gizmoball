package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddFlipperListener implements MouseListener {
    private IGUI bgui;
    private IGBallModel m;

    public AddFlipperListener(IGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.ADD && bgui.getSelectedComponent().equals("Flipper")) {
            //paint it
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            switch(bgui.getFlipperPosition()) {
                case "Left":
                    m.addFlipper(x, y, true, "flipper");
                    bgui.setMessage("Flipper added!");
                    break;
                case "Right":
                    m.addFlipper(x, y, false, "flipper");
                    bgui.setMessageColor(Color.GREEN);
                    bgui.setMessage("Flipper added!");
                    break;
                default:
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
