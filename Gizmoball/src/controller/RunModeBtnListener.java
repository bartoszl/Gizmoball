package controller;

import model.GBallModel;
import model.IGBallModel;
import model.ModelLoader;
import view.Board;
import view.IGUI;
import view.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

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
		    		timer.stop();
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
}
