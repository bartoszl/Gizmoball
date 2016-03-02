package view;

import java.awt.*;

import model.GBallModel;
import model.IGBallModel;

/**
 * Created by Stephen on 01/03/2016.
 */
public class Main {
    private IGUI gui;
    private IGBallModel model;
    /**
     * Launch the application.
     */
    public Main() {
    	model = new GBallModel();
    	switchToRun();
    }

    public void switchToBuild(){
        gui = new BuildGUI(this, model);
    }

    public void switchToRun(){
        gui = new RunGUI(this, model);
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
