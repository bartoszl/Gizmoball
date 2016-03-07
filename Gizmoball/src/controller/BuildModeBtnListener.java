package controller;

import model.Ball;
import model.Bumper;
import model.Flipper;
import model.IGBallModel;
import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class BuildModeBtnListener implements ActionListener {
    private Board board;
    private IGBallModel model;

    public BuildModeBtnListener(Board board, IGBallModel model) {
        this.board = board;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Move":
                board.setAction(Board.Action.MOVE);
                break;
            case "Rotate":
                board.setAction(Board.Action.ROTATE);
                break;
            case "Add Component":
                board.setAction(Board.Action.ADD);
                break;
            case "Delete":
                board.setAction(Board.Action.DELETE);
                break;
            case "Clear":
                model.setGizmos(new ArrayList<Bumper>());
                model.setAbsorber(null);
                model.setFlippers(new ArrayList<Flipper>());
                model.setBalls(new ArrayList<Ball>());
                board.repaint();
                board.setAction(Board.Action.NONE);
                break;
            default:
                board.setAction(Board.Action.NONE);
        }
    }
}
