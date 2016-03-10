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

    public void setModel(GBallModel model){
        this.model = model;
        gui.getGridView().delete();
        gui.getGridView().setVisible(false);
        gui.getFrame().remove(gui.getFrame().getContentPane());
        gui.getFrame().remove(gui.getFrame().getJMenuBar());
        gui.getFrame().remove(gui.getPanel());
        if(gui instanceof RunGUI) gui = new RunGUI(this, model, gui.getFrame());
        else gui = new BuildGUI(this, model, gui.getFrame());
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
