package View;

import model.*;

import java.awt.*;

public class Controller {

    private RunGUI view;
    private iGBallModel model;

    public Controller(RunGUI view, final iGBallModel model) {
        this.view = view;
        this.model = model;
    }
}
