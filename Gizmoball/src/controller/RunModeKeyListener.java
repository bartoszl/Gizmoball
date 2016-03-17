package controller;

import model.*;
import view.IBuildGUI;
import view.IRunGUI;
import view.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by zveket on 15/03/16.
 */
public class RunModeKeyListener implements KeyListener {
    private IGBallModel model;
    private IRunGUI gui;
    private Main main;
    private MagicKeyListener mkl;


    public RunModeKeyListener(IRunGUI gui, IGBallModel model, Main main){
        this.gui = gui;
        this.model = model;
        this.main = main;
        this.mkl = new MagicKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("press");
        int keyCode = keyEvent.getKeyCode();
        //loop through key flipper connections
        for(KeyConnectionFlipper kcf : model.getKeyConnectionsFlipper()) {
            if(kcf.getKeyID() == keyCode) {
                Flipper flipper = kcf.getFlipper();
                flipper.press();
            }
        }
        //and absorber key connections
        for(KeyConnectionAbs kca : model.getKeyConnectionsAbs()) {
            if(kca.getKeyID() == keyCode) {
                Absorber abs = kca.getAbs();
                abs.fire();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        System.out.println("release");
        int keyCode = keyEvent.getKeyCode();
        for(KeyConnectionFlipper kcf : model.getKeyConnectionsFlipper()) {
            if(kcf.getKeyID() == keyCode) {
                Flipper flipper = kcf.getFlipper();
                flipper.release();
            }
        }
    }
}
