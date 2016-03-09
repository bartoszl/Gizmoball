package controller;

import model.IGBallModel;
import view.Board;
import view.IGUI;
import view.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class RunModeBtnListener implements ActionListener {
    private IGBallModel model;
    private IGUI gui;
    private Main main;
    
    private Timer timer;

    public RunModeBtnListener(IGUI gui, IGBallModel model, Main main){
    	this.gui = gui;
        this.model = model;
        this.main = main;
        this.timer = new Timer(50, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Source: " + e.getActionCommand());
        if(e.getSource()==timer){
        	model.moveBall();
        } else 
		    switch(e.getActionCommand()){
		    	case "Start":
		    		timer.start();
		    		break;
		    	case "Tick":
		    		model.moveBall();
		    		break;
		    	case "Stop":
		    		timer.stop();
		    		break;
		    	case "Swap":
		    		gui.getGridView().delete();
		    		gui.getGridView().setVisible(false);
		            gui.getFrame().remove(gui.getFrame().getContentPane());
		            gui.getFrame().remove(gui.getFrame().getJMenuBar());
		            gui.getFrame().remove(gui.getPanel());
		            System.gc();
		            main.switchToBuild(gui.getFrame());
		    		break;
				default:
					break;
		    }
    }
}
