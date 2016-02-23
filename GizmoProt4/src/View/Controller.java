package View;

import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private RunGUI view;
    private iGBallModel model;

    public Controller(RunGUI view, final iGBallModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Load") {
            System.out.println("Load buttom is being clicked.");
        }
    }
}
