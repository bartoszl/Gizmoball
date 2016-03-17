package controller;

import view.Board;
import view.IBuildGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddComponentListener implements ActionListener {
    private IBuildGUI bgui;

    public AddComponentListener(IBuildGUI bgui) {
        this.bgui = bgui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        bgui.getGridView().setAction(Board.Action.ADD);
    }
}
