package controller;

import model.Ball;
import model.Bumper;
import model.Flipper;
import model.IGBallModel;
import physics.Angle;
import view.Board;
import view.BuildGUI;
import view.IBuildGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteComponentListener implements MouseListener {
    private IBuildGUI bgui;
    private IGBallModel model;

    public DeleteComponentListener(IBuildGUI bgui, IGBallModel model) {
        this.model = model;
        this.bgui = bgui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.DELETE) {
            boolean[][] spaces = model.getOccupiedSpaces();
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            model.deleteElement(x, y);
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
