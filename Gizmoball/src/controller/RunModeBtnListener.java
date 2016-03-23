package controller;

import model.*;
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
        this.timer = new Timer(10, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer) {
        	model.moveModel();
        } else {
            switch (e.getActionCommand()) {
                case "Start":
                    gui.getGridView().requestFocus();
                    timer.start();
                    break;
                case "Tick":
                    model.moveModel();
                    timer.stop();
                    break;
                case "Reset":
                    model.reset();
                    model.moveModel();
                    timer.stop();
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Swap":
                    model.playSound(false);
                    timer.stop();
                    model.reset();
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
                    if (valid == JFileChooser.APPROVE_OPTION) {
                        File f = fc.getSelectedFile();
                        ModelLoader ml = new ModelLoader(f);
                        GBallModel m = ml.getModel();
                        m.setLoadFile(f);
                        model.playSound(false);
                        m.setSound(model.getSound());
                        gui.getGridView().delete();
                        gui.getGridView().setVisible(false);
                        gui.getFrame().remove(gui.getFrame().getContentPane());
                        gui.getFrame().remove(gui.getFrame().getJMenuBar());
                        gui.getFrame().remove(gui.getPanel());
                        main.setModel(m);
                    }
                    break;
                case "Reload":
                    File f = model.getLoadFile();
                    if(f != null) {
                        ModelLoader ml = new ModelLoader(f);
                        GBallModel m = ml.getModel();
                        m.setLoadFile(f);
                        model.playSound(false);
                        m.setSound(model.getSound());
                        gui.getGridView().delete();
                        gui.getGridView().setVisible(false);
                        gui.getFrame().remove(gui.getFrame().getContentPane());
                        gui.getFrame().remove(gui.getFrame().getJMenuBar());
                        gui.getFrame().remove(gui.getPanel());
                        main.setModel(m);
                    }
                    break;
                case "Save":
                    JFileChooser saveFC = new JFileChooser();
                    saveFC.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int saveValid = saveFC.showSaveDialog(gui.getFrame());
                    if (saveValid == JFileChooser.APPROVE_OPTION) {
                        File saveFile = saveFC.getSelectedFile();
                        Writer writer = new Writer();
                        writer.writeModelToFile(model, saveFile.getName());
                    }
                    break;
                case "Quit":
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) System.exit(0);
                    break;
                case "Play / Pause":
                    if(model.getSound()==null)
                        JOptionPane.showMessageDialog(null, "No Audio File Loaded!", "Error", JOptionPane.WARNING_MESSAGE);
                    else
                        model.playSound(true);
                    break;
                case "Load Sound":
                    JFileChooser sc = new JFileChooser();
                    sc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int choice = sc.showOpenDialog(gui.getFrame());
                    if (choice == JFileChooser.APPROVE_OPTION) {
                        File sound = sc.getSelectedFile();
                        model.setSound(sound);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
