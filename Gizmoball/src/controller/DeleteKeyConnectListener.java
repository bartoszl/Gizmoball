package controller;

import model.Absorber;
import model.Flipper;
import model.IGBallModel;
import model.KeyConnectionAbs;
import view.Board;
import view.BuildGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteKeyConnectListener implements MouseListener {
    private BuildGUI bgui;
    private IGBallModel m;

    public DeleteKeyConnectListener(BuildGUI bgui, IGBallModel m) {
        this.m = m;
        this.bgui = bgui;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Board b = bgui.getGridView();
        if(b.getAction() == Board.Action.KEY_DISCONNECT) {
            Point mouseP = MouseInfo.getPointerInfo().getLocation();
            Point gridP = b.getLocationOnScreen();
            int x = mouseP.x - gridP.x;
            int y = mouseP.y - gridP.y;
            x -= x%20;
            y -= y%20;
            for(KeyConnectionAbs kca : m.getKeyConnectionsAbs()) {

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
