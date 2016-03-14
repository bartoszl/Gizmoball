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
                        GBallModel m = ml.getModel();
                        m.setLoadFile(f);
                        main.setModel(m);
                    }
                    break;
                case "Reload":
                    model.resetBalls();
                    model.moveBall();
                    timer.stop();
                    break;
                case "Save":
                    break;
                case "Quit":
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit",  JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) System.exit(0);
                    break;
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
                flipper.press();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        for(KeyConnectionFlipper kcf : model.getKeyConnectionsFlipper()) {
            if(kcf.getKeyID() == keyCode) {
                Flipper flipper = kcf.getFlipper();
                flipper.release();
            }
        }
    }
}
