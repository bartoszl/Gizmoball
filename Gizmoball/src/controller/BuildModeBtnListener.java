package controller;

import model.IGBallModel;
import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BuildModeBtnListener implements ActionListener {
    private Board board;
    private IGBallModel model;

    public BuildModeBtnListener(Board board, IGBallModel model) {
        this.board = board;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Move")){
            board.setMoving(true);
        }
    }
}
