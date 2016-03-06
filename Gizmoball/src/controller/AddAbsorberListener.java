package controller;

import model.*;
import view.*;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddAbsorberListener implements MouseListener {
    private BuildGUI bgui;
    private IGBallModel m;

    public AddAbsorberListener(BuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.ADD && bgui.getSelectedButtonText().equals("Absorber")) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            //paint it
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            m.addAbsorber("absorber", x, y, 7, 7);
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