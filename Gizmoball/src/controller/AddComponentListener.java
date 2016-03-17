package controller;

import view.Board;
import view.IGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddComponentListener implements ActionListener {
    private IGUI bgui;

    public AddComponentListener(IGUI bgui) {
        this.bgui = bgui;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        bgui.getGridView().setAction(Board.Action.ADD);
    }
}
