package View;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Controller implements ActionListener {

    private RunGUI view;
    private iGBallModel model;

    public Controller(RunGUI view, final iGBallModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Load") {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int valid = fc.showOpenDialog(view.getFrame());
            if(valid == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                ModelLoader ml = new ModelLoader(f);
                view.setModel(ml.getModel());
                view.reload();
            }
        }
    }
}