package controller;

import model.IGBallModel;
import view.Board;
import view.BuildGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RotateComponentListener implements MouseListener {
    private BuildGUI bgui;
    private IGBallModel model;

    public RotateComponentListener(BuildGUI bgui, IGBallModel model) {
        this.model = model;
        this.bgui = bgui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.ROTATE) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            if(model.rotateElement(x, y)) {
                bgui.setMessageColor(Color.GREEN);
                bgui.setMessage("Component rotated!");
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
}
