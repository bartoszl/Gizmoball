package controller;

import model.IGBallModel;
import view.Board;
import view.BuildGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddComponentListener implements ActionListener {
    private BuildGUI bgui;

    public AddComponentListener(BuildGUI bgui) {
        this.bgui = bgui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        bgui.getGridView().setAction(Board.Action.ADD);
    }
}
