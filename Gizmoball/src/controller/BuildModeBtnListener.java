package controller;

import model.GBallModel;
import model.IGBallModel;
import model.ModelLoader;
import model.Writer;
import view.Board;
import view.IGUI;
import view.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;


public class BuildModeBtnListener implements ActionListener {
    private Board board;
    private IGBallModel model;
    private IGUI gui;
    private Main main;

    public BuildModeBtnListener(IGUI gui, Board board, IGBallModel model, Main main) {
    	this.gui = gui;
        this.board = board;
        this.model = model;
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setMessageColor(Color.BLACK);
        switch (e.getActionCommand()) {
            case "Move":
                gui.setMessage("Click on a Component which you want to move");
                board.setAction(Board.Action.MOVE);
                break;
            case "Rotate":
                gui.setMessage("Click on a Component which you want to rotate");
                board.setAction(Board.Action.ROTATE);
                break;
            case "Add Component":
                gui.setMessage("Click where you want to add Component");
                board.setAction(Board.Action.ADD);
                break;
            case "Delete":
                gui.setMessage("Click on a Component which you want to delete");
                board.setAction(Board.Action.DELETE);
                break;
            case "Key Connect":
                gui.setMessage("Click on Flipper or Absorber which you want to key connect");
                board.setAction(Board.Action.KEY_CONNECT);
                break;
            case "Key Disconnect":
                gui.setMessage("Click on Flipper or Absorber which you want to key disconnect");
                board.setAction(Board.Action.KEY_DISCONNECT);
                break;
            case "Connect":
                gui.setMessage("Click on Bumper which should trigger the action");
                board.setAction(Board.Action.CONNECT);
                break;
            case "Disconnect":
                gui.setMessage("Click on Teleporter, Flipper or Absorber which you want to disconnect");
                board.setAction(Board.Action.DISCONNECT);
                break;
            case "Clear":
                if(JOptionPane.showConfirmDialog(null, "Are you sure you want to Clear?") == 0) {
                    model.clear();
                    board.repaint();
                }
                break;
            case "Swap":
                model.playSound(false);
                board.delete();
                MouseListener[] mListeners = board.getMouseListeners();
                for(int i=0; i<mListeners.length;i++){
                    board.removeMouseListener(mListeners[i]);
                }
                board.setVisible(false);
                board = null;
                gui.getFrame().remove(gui.getFrame().getContentPane());
                gui.getFrame().remove(gui.getFrame().getJMenuBar());
                gui.getFrame().remove(gui.getPanel());
                System.gc();
                main.switchToRun(gui.getFrame());
                break;
            case "Load":
                JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int valid = fc.showOpenDialog(gui.getFrame());
                if(valid == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
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
                }
                break;
            case "Reload":
                File f = model.getLoadFile();
                if(f != null) {
                    ModelLoader ml = new ModelLoader(f);
                    GBallModel m = ml.getModel();
                    m.setLoadFile(f);
                    main.setModel(m);
                }
                break;
            case "Save":
                JFileChooser saveFC = new JFileChooser();
                saveFC.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int saveValid = saveFC.showSaveDialog(gui.getFrame());
                if(saveValid == JFileChooser.APPROVE_OPTION) {
                    File saveFile = saveFC.getSelectedFile();
                    Writer writer = new Writer();
                    writer.writeModelToFile(model, saveFile.getName());
                }
                break;
            case "Quit":
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) System.exit(0);
                break;
            case "Friction":
                boolean invalidMU = true, invalidMU2 = true;
                double mu = 0, mu2 = 0;
                String inputMU = JOptionPane.showInputDialog("Please enter a new value for 'mu' Friction, Current mu: "
                        + model.getFrictionX()); if(inputMU == null) return;
                String inputMU2 = JOptionPane.showInputDialog("Please enter a new value for 'mu2' Friction, Current mu2: "
                        + model.getFrictionY()); if(inputMU2 == null) return;
                while (invalidMU || invalidMU2) {
                    invalidMU = false; invalidMU2 = false;
                    if(inputMU == null || inputMU2 == null) break;
                    if(inputMU.equals("")){
                        invalidMU = true;
                    }
                    else if(inputMU2.equals("")){
                        invalidMU2 = true;
                    }
                    else {
                        invalidMU = false; invalidMU2 = false;
                        try {
                            mu = Double.parseDouble(inputMU);
                        } catch (NumberFormatException err){
                            invalidMU = true;
                        }
                        try {
                            mu2 = Double.parseDouble(inputMU2);
                        } catch (NumberFormatException err){
                            invalidMU2 = true;
                        }
                        if(!invalidMU && !invalidMU2) {
                            if (mu < 0 || mu > 0.1) {
                                invalidMU = true;
                            } else if (mu2 < 0 || mu2 > 0.1) {
                                invalidMU2 = true;
                            } else {
                                model.setFriction(mu, mu2);
                                gui.setMessageColor(Color.GREEN);
                                gui.setMessage("Friction has been set!");
                                invalidMU = false;
                                invalidMU2 = false;
                            }
                        }
                    }
                    if(invalidMU) inputMU = JOptionPane.showInputDialog("Please re-enter a valid value for 'mu' Friction, Current mu: "
                                                                        + model.getFrictionX());
                    if(invalidMU2) inputMU2 = JOptionPane.showInputDialog("Please re-enter a valid value for 'mu2' Friction, Current mu2: "
                                                                        + model.getFrictionY());
                }
                break;
            case "Gravity":
                boolean invalidG = true;
                double g = 0;
                String inputG = JOptionPane.showInputDialog("Please enter a new value for Gravity, Current Gravity: "
                        + model.getGravity()); if(inputG == null) return;
                while (invalidG) {
                    if (inputG == null) break;
                    if (inputG.equals("")) {
                        invalidG = true;
                    } else {
                        invalidG = false;
                        try {
                            g = Double.parseDouble(inputG);
                        } catch (NumberFormatException err) {
                            invalidG = true;
                        }
                        if (!invalidG) {
                            if (g < 0 || g > 50) {
                                invalidG = true;
                            } else {
                                model.setGravity(g);
                                gui.setMessageColor(Color.GREEN);
                                gui.setMessage("Gravity has been set!");
                                invalidG = false;
                            }
                        }
                    }
                    if (invalidG) inputG = JOptionPane.showInputDialog("Please re-enter a valid value for Gravity, Current Gravity: "
                                + model.getGravity());
                }
                break;
            default:
                board.setAction(Board.Action.NONE);
        }
    }
}
