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
    private int[] clicks;
    private boolean first;

    public AddAbsorberListener(BuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
        first = true;
        clicks = new int[4];
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        System.out.println("here");
        if(b.getAction() == Board.Action.ADD && bgui.getSelectedButtonText().equals("Absorber")) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            if(first){
                System.out.println("Abs First Click: x="+x+", y="+y);
                clicks[0] = x;
                clicks[1] = y;
                first = false;
            }
            else {
                System.out.println("Abs Second Click: x="+x+", y="+y);
                clicks[2] = x;
                clicks[3] = y;
                m.addAbsorber("absorber",clicks[0],clicks[1],clicks[2],clicks[3]);
                first = true;
                b.repaint();
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
