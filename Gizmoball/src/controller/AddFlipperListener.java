package controller;

import model.*;
import view.*;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddFlipperListener implements MouseListener {
    private Board b;
    private IGBallModel m;

    public AddFlipperListener(Board b, IGBallModel m) {
        this.m = m;
        this.b = b;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point mouseP = MouseInfo.getPointerInfo().getLocation();
        Point gridP = b.getLocationOnScreen();
        if(b.getAdding() == Board.Adding.FLIPPER) {
            //paint it
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            m.addFlipper(x, y, false, "flipper");
            b.setAdding(Board.Adding.NONE);
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
