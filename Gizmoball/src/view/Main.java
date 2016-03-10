package view;

import java.awt.*;

import javax.swing.JFrame;

import model.*;
import sun.awt.X11.XCirculateRequestEvent;

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
        /*GBallModel m = new GBallModel();
        Writer writer = new Writer();*/
        //m.addCircularBumper(40, 40, 0, "Circle1");
        /*m.addSquareBumper(80, 80, 0, "Square1");
        m.addAbsorber("Absorber1", 100, 100, 200, 200);
        m.addBall("Ball1", 60, 60, 50, 50);
        m.rotateElement(40, 40);*/
        /*CircularBumper cBumper = new CircularBumper(100, 200, 0, "C1");
        Flipper flipper = new Flipper(9, 2, true, Color.RED, "LF92");
        m.addConnection(new Connection(cBumper, flipper));
        writer.writeModelToFile(m, "Save1");*/
        /*Flipper flipper = new Flipper(9, 2, true, Color.RED, "LF92");
        m.addFlipper(180, 40, true, "LF92");
        KeyConnectionFlipper conn = new KeyConnectionFlipper(50, flipper, "up");
        m.addKeyConnectionFlipper(conn);
        writer.writeModelToFile(m, "Save2");
*/
    }
}
