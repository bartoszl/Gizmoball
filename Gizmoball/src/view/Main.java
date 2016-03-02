package view;

import java.awt.*;

/**
 * Created by Stephen on 01/03/2016.
 */
public class Main {
    private static IGUI gui;
    /**
     * Launch the application.
     */
    public Main() { switchToRun(); }

    public void switchToBuild(){
        gui = new BuildGUI(this);
    }

    public void switchToRun(){
        gui = new RunGUI(this);
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
