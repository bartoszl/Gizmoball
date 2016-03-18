package controller;

import model.*;
import view.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddGizmoListener implements MouseListener{
    private IGUI bgui;
    private IGBallModel m;

    public AddGizmoListener(IGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.ADD && bgui.getSelectedComponent().equals("Gizmo")) {
            String gizmoShape = bgui.getGizmoShape();
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            boolean added = false;
            switch(gizmoShape) {
                case "Circle":
                    added = m.addCircularBumper(x, y, 0, "circle");
                    break;
                case "Triangle":
                    added = m.addTriangularBumper(x, y, 0, "triangle");
                    break;
                case "Square":
                    added = m.addSquareBumper(x, y, 0, "square");
                    break;
                default:
            }
            if(added) {
                bgui.setMessageColor(Color.GREEN);
                bgui.setMessage(gizmoShape+" added!");
            } else {
                bgui.setMessageColor(Color.RED);
                bgui.setMessage("That space is occupied, "+gizmoShape+" cannot be added");
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
