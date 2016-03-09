package controller;

import model.IGBallModel;
import view.Board;
import view.IGUI;
import view.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RunModeBtnListener implements ActionListener {
    private IGBallModel model;
    private IGUI gui;
    private Main main;

    public RunModeBtnListener(IGUI gui, IGBallModel model, Main main){
    	this.gui = gui;
        this.model = model;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Source: " + e.getActionCommand());
        switch(e.getActionCommand()){
        	case "Start":
        		break;
        	case "Tick":
        		break;
        	case "Stop":
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
