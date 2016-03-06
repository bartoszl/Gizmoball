package controller;

import model.Bumper;
import model.Flipper;
import model.IGBallModel;
import physics.Angle;
import view.Board;
import view.BuildGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RotateComponentListener implements ActionListener, MouseListener {
    private BuildGUI bgui;
    private IGBallModel model;

    public RotateComponentListener(BuildGUI bgui, IGBallModel model) {
        this.model = model;
        this.bgui = bgui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        bgui.getGridView().setAction(Board.Action.ROTATE);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        boolean[][] spaces = model.getOccupiedSpaces();
        Point mouseP = MouseInfo.getPointerInfo().getLocation();
        Point gridP = b.getLocationOnScreen();
        int x = mouseP.x - gridP.x;
        int y = mouseP.y - gridP.y;
        x -= x%20;
        y -= y%20;
        x /= 20;
        y /= 20;
        if(spaces[x][y]) {
            for(Bumper bumper : model.getGizmos()) {
                if(bumper.getX() == x*20 && bumper.getY() == y*20) {
                    bumper.rotate();
                }
            }
            for(Flipper flipper : model.getFlippers()) {
                if(flipper.getOrigin().x() == x*20 && flipper.getOrigin().y() == y*20) {
                    flipper.rotate(Angle.DEG_90);
                }
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
