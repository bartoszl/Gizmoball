package controller;

import model.*;
import physics.Angle;
import view.Board;
import view.IGUI;
import view.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.*;

public class RunModeBtnListener implements ActionListener, KeyListener {
    private IGBallModel model;
    private IGUI gui;
    private Main main;
    private MagicKeyListener mkl;
    
    private Timer timer;

    public RunModeBtnListener(IGUI gui, IGBallModel model, Main main){
    	this.gui = gui;
        this.model = model;
        this.main = main;
        this.timer = new Timer(50, this);
        this.mkl = new MagicKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer) {
        	model.moveBall();
            model.moveFlippers();
        } else 
		    switch(e.getActionCommand()){
		    	case "Start":
		    		timer.start();
                    gui.getGridView().requestFocus();
		    		break;
		    	case "Tick":
		    		model.moveBall();
		    		break;
		    	case "Stop":
		    		timer.stop();
		    		break;
		    	case "Swap":
		    		timer.stop();
                    model.resetBalls();
		    		gui.getGridView().delete();
		    		gui.getGridView().setVisible(false);
		            gui.getFrame().remove(gui.getFrame().getContentPane());
		            gui.getFrame().remove(gui.getFrame().getJMenuBar());
		            gui.getFrame().remove(gui.getPanel());
		            System.gc();
		            main.switchToBuild(gui.getFrame());
		    		break;
                case "Load":
                    JFileChooser fc = new JFileChooser();
                    fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int valid = fc.showOpenDialog(gui.getFrame());
                    if(valid == JFileChooser.APPROVE_OPTION) {
                        File f = fc.getSelectedFile();
                        ModelLoader ml = new ModelLoader(f);
                        gui.setModel(ml.getModel());
                    }
                    break;
                case "Reload":
                    break;
                case "Save":
                    break;
                case "Quit":
                    System.exit(0);
				default:
					break;
		    }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        //loop through key flipper connections
        for(KeyConnectionFlipper kcf : model.getKeyConnectionsFlipper()) {
            if(kcf.getKeyID() == keyCode) {
                Flipper flipper = kcf.getFlipper();
                if(flipper.getPosition() == IFlipper.Position.VERTICAL) {
                    flipper.setMovement(IFlipper.Movement.FORWARDS);
                } else if(flipper.getPosition() == IFlipper.Position.BETWEEN) {
                    if(flipper.getMovement() == IFlipper.Movement.BACKWARDS) {
                        //reverse it
                        flipper.setLeft(Angle.DEG_90.minus(flipper.getLeft()));
                    }
                    flipper.setMovement(IFlipper.Movement.FORWARDS);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        for(KeyConnectionFlipper kcf : model.getKeyConnectionsFlipper()) {
            if(kcf.getKeyID() == keyCode) {
                Flipper flipper = kcf.getFlipper();
                if(flipper.getPosition() == IFlipper.Position.HORIZONTAL) {
                    flipper.setMovement(IFlipper.Movement.BACKWARDS);
                } else if(flipper.getPosition() == IFlipper.Position.BETWEEN) {
                    if(flipper.getMovement() == IFlipper.Movement.FORWARDS) {
                        //reverse it
                        flipper.setLeft(Angle.DEG_90.minus(flipper.getLeft()));
                    }
                    flipper.setMovement(IFlipper.Movement.BACKWARDS);
                }
            }
        }
    }
}
