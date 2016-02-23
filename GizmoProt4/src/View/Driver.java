package View;

import View.ModelLoader;
import View.RunGUI;
import model.*;

import java.awt.*;

public class Driver {

    private GBallModel model;
    private RunGUI view;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //ModelLoader loader = new ModelLoader();
                    GBallModel model = new GBallModel();//loader.getModel();
                    RunGUI window = new RunGUI(model);
                    //window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
