package controller;

import model.IGBallModel;
import view.Board;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MoveGizmoListener implements MouseListener {
    private Board board;
    private IGBallModel model;
    private int[] clicks;
    private boolean first;

    public MoveGizmoListener(Board board, IGBallModel model){
        this.board = board;
        this.model = model;
        first = true;
        clicks = new int[4];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point mouseP = MouseInfo.getPointerInfo().getLocation();
        Point gridP = board.getLocationOnScreen();
        int x = mouseP.x - gridP.x;
        int y = mouseP.y - gridP.y;
        if(board.getAction() == Board.Action.MOVE) {
            if(first){
                clicks[0] = x;
                clicks[1] = y;
                first = false;
            }
            else {
                clicks[2] = x;
                clicks[3] = y;
                model.moveElement(clicks[0],clicks[1],clicks[2],clicks[3]);
                first = true;
                board.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
