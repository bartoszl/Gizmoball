package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddBallListener implements MouseListener {
    private IBuildGUI bgui;
    private IGBallModel m;

    public AddBallListener(IBuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.ADD && bgui.getSelectedComponent().equals("Ball")) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            //paint it
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            m.addBall("ball", x, y, 50, 50);
            bgui.setMessage("Ball added!");
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
