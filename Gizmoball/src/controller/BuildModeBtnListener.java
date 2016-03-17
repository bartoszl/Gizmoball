package controller;

import model.GBallModel;
import model.IGBallModel;
import model.ModelLoader;
import view.Board;
import view.IGUI;
import view.Main;

import javax.swing.*;
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
        System.out.println("Event: " + e.getActionCommand());
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
                gui.setMessage("Click on board where you want to add Component");
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
                gui.setMessage("Click on Circular Bumper which should trigger the action");
                board.setAction(Board.Action.CONNECT);
                break;
            case "Disconnect":
                gui.setMessage("Click on Flipper or Absorber which you want to disconnect");
                board.setAction(Board.Action.DISCONNECT);
                break;
            case "Clear":
                if(JOptionPane.showConfirmDialog(null, "Are you sure you want to Clear?") == 0) {
                    model.clear();
                    board.repaint();
                    board.setAction(Board.Action.NONE);
                }
                break;
            case "Swap":
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
                break;
            case "Quit":
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit",  JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) System.exit(0);
                break;
            case "Friction":
                boolean invalidX = true, invalidY = true;
                double x = 0, y = 0;
                String inputX = JOptionPane.showInputDialog("Please enter a new value for 'X' Friction, Current X: "
                        + model.getFrictionX()); if(inputX == null) return;
                String inputY = JOptionPane.showInputDialog("Please enter a new value for 'Y' Friction, Current Y: "
                        + model.getFrictionY()); if(inputY == null) return;
                while (invalidX || invalidY) {
                    invalidX = false; invalidY = false;
                    if(inputX == null || inputY == null) break;
                    if(inputX.equals("")){
                        invalidX = true;
                    }
                    else if(inputY.equals("")){
                        invalidY = true;
                    }
                    else {
                        invalidX = false; invalidY = false;
                        try {
                            x = Double.parseDouble(inputX);
                        } catch (NumberFormatException err){
                            invalidX = true;
                        }
                        try {
                            y = Double.parseDouble(inputY);
                        } catch (NumberFormatException err){
                            invalidY = true;
                        }
                        if(!invalidX && !invalidY) {
                            if (x < 0 || x > 10) {
                                invalidX = true;
                            } else if (y < 0 || y > 10) {
                                invalidY = true;
                            } else {
                                model.setFriction(x, y); // May have to add Rounding
                                invalidX = false;
                                invalidY = false;
                            }
                        }
                    }
                    if(invalidX) inputX = JOptionPane.showInputDialog("Please re-enter a valid value for 'X' Friction, Current X: "
                                                                        + model.getFrictionX());
                    if(invalidY) inputY = JOptionPane.showInputDialog("Please re-enter a valid value for 'Y' Friction, Current Y: "
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
                            if (g < 0 || g > 10) {
                                invalidG = true;
                            } else {
                                System.out.println("G: " + g);
                                model.setGravity(g); // May have to add Rounding
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
