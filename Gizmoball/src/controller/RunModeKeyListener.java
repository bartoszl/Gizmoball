package controller;

import model.*;
import view.IGUI;
import view.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by zveket on 15/03/16.
 */
public class RunModeKeyListener implements KeyListener {
    private IGBallModel model;
    private MagicKeyListener mkl;
//    private IGUI gui;
//    private Main main;

    public RunModeKeyListener(IGUI gui, IGBallModel model, Main main){
        this.model = model;
        this.mkl = new MagicKeyListener(this);
//        this.gui = gui;
//        this.main = main;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        mkl.keyPressed(keyEvent);
        int keyCode = keyEvent.getKeyCode();
        for(KeyConnectionFlipper kcf : model.getKeyConnectionsFlipper()) {
            if(kcf.getKeyID() == keyCode) {
                IFlipper flipper = kcf.getFlipper();
                flipper.press();
            }
        }
        for(KeyConnectionAbs kca : model.getKeyConnectionsAbs()) {
            if(kca.getKeyID() == keyCode) {
                IAbsorber abs = kca.getAbs();
                abs.fire();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        mkl.keyReleased(keyEvent);
        int keyCode = keyEvent.getKeyCode();
        for(KeyConnectionFlipper kcf : model.getKeyConnectionsFlipper()) {
            if(kcf.getKeyID() == keyCode) {
                IFlipper flipper = kcf.getFlipper();
                flipper.release();
            }
        }
    }
}
