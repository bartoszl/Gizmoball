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
        String choice = bgui.getSelectedButtonText();
        switch(choice) {
            case "Gizmo":
                String gizmoShape = bgui.getGizmoShape();
                switch(gizmoShape) {
                    case "Circle":
                        bgui.getGridView().setAdding(Board.Adding.CIRCLE);
                        break;
                    case "Triangle":
                        bgui.getGridView().setAdding(Board.Adding.TRIANGLE);
                        break;
                    case "Square":
                        bgui.getGridView().setAdding(Board.Adding.SQUARE);
                        break;
                    default:
                }

                break;
            case "Ball":
                bgui.getGridView().setAdding(Board.Adding.BALL);
                break;
            case "Absorber":
                bgui.getGridView().setAdding(Board.Adding.ABSORBER);
                break;
            case "Flipper":
                bgui.getGridView().setAdding(Board.Adding.FLIPPER);
                break;
            default:
                bgui.getGridView().setAdding(Board.Adding.NONE);
        }
    }
}
