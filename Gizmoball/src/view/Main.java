package view;

import java.awt.*;

import javax.swing.JFrame;

import model.*;

/**
 * Created by Stephen on 01/03/2016.
 */
public class Main {
    private IGUI gui;
    private GBallModel model;
    /**
     * Launch the application.
     */
    public Main() {
    	model = new GBallModel();
    	gui = new RunGUI(this, model);
    }

    public void switchToBuild(JFrame frame){
        gui = new BuildGUI(this, model, frame);
        model.addObserver(gui.getGridView());
    }

    public void switchToRun(JFrame frame){
        gui = new RunGUI(this, model, frame);
        model.addObserver(gui.getGridView());
    }

    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main m = new Main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
