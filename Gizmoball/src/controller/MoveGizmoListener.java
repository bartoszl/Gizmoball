package controller;

import model.IGBallModel;
import view.Board;
import view.IGUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MoveGizmoListener implements MouseListener {
    private IGUI bgui;
    private IGBallModel model;
    private int[] clicks;
    private boolean first;

    public MoveGizmoListener(IGUI bgui, IGBallModel model){
        this.bgui = bgui;
        this.model = model;
        first = true;
        clicks = new int[4];
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Board board = bgui.getGridView();
        Point mouseP = MouseInfo.getPointerInfo().getLocation();
        Point gridP = board.getLocationOnScreen();
        int x = mouseP.x - gridP.x;
        int y = mouseP.y - gridP.y;
        if(board.getAction() == Board.Action.MOVE) {
            if(first){
                clicks[0] = x;
                clicks[1] = y;
                bgui.setMessageColor(Color.GREEN);
                bgui.setMessage("Component found!");
                first = false;
            } else {
                clicks[2] = x;
                clicks[3] = y;
                if(model.moveElement(clicks[0],clicks[1],clicks[2],clicks[3])) {
                    bgui.setMessageColor(Color.GREEN);
                    bgui.setMessage("Component moved!");
                    first = true;
                    board.repaint();
                } else {
                    bgui.setMessageColor(Color.RED);
                    bgui.setMessage("Invalid Position for that Component");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
