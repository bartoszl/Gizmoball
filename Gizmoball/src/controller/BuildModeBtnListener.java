package controller;

import model.Ball;
import model.Bumper;
import model.Flipper;
import model.IGBallModel;
import view.Board;

import javax.swing.*;
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
        System.out.println("Event: " + e.getActionCommand());
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
                if(JOptionPane.showConfirmDialog(null, "Are you sure you want to Clear?") == 0) {
                    model.setGizmos(new ArrayList<Bumper>());
                    model.setAbsorber(null);
                    model.setFlippers(new ArrayList<Flipper>());
                    model.setBalls(new ArrayList<Ball>());
                    board.repaint();
                    board.setAction(Board.Action.NONE);
                }
                break;
            case "Friction":
                double inputX = Double.parseDouble(JOptionPane.showInputDialog("Please enter a new value for 'X' Friction, Current X: "
                                                    + model.getFrictionX()));
                double inputY = Double.parseDouble(JOptionPane.showInputDialog("Please enter a new value for 'Y' Friction, Current Y: "
                                                    + model.getFrictionY()));
                if(inputX > 0 && inputX < 10 && inputY > 0 && inputY < 10){
                    //Both inputs are within a "Range"
                    model.setFriction(inputX, inputY);
                }
                else{
                    //Ask again for input
                }
                break;
            case "Gravity":
                double newG = Double.parseDouble(JOptionPane.showInputDialog("Please enter a new value for Gravity, Current Gravity: "
                        + model.getGravity()));
                if(newG > 0 && newG < 10){
                    //Input is within a "Range"
                    model.setGravity(newG);
                }
                else{
                    //Ask again for input
                }
                break;
            default:
                board.setAction(Board.Action.NONE);
        }
    }
}
