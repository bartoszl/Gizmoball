package controller;

import model.*;
import view.*;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddGizmoListener implements MouseListener{
    private BuildGUI bgui;
    private IGBallModel m;

    public AddGizmoListener(BuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.ADD && bgui.getSelectedButtonText().equals("Gizmo")) {
            String gizmoShape = bgui.getGizmoShape();
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            switch(gizmoShape) {
                case "Circle":
                    m.addCircularBumper(x, y, 0, "circle");
                    bgui.setMessage("Circle added!");
                    break;
                case "Triangle":
                    m.addTriangularBumper(x, y, 0, "triangle");
                    bgui.setMessage("Triangle added!");
                    break;
                case "Square":
                    m.addSquareBumper(x, y, 0, "square");
                    bgui.setMessage("Square added!");
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
