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
                        b.repaint();
                        break;
                    }
                }
                for(Flipper flipper : model.getFlippers()) {
                    double fX = flipper.getOrigin().x();
                    double fY = flipper.getOrigin().y();
                    if((fX == x*20 || (fX+20) == (x*20)) &&
                            (fY == y*20) || (fY+20) == (y*20)) {
                        flipper.rotate();
                        b.repaint();
                        break;
                    }
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
