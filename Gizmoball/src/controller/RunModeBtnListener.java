package controller;

import model.IGBallModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RunModeBtnListener implements ActionListener {
    private IGBallModel model;

    public RunModeBtnListener(IGBallModel model){
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Source: " + e.getActionCommand());
        if(e.getActionCommand().equals("Start")){

        }
        else if(e.getActionCommand().equals("Tick")){

        }
        else if(e.getActionCommand().equals("Stop")){

        }
    }
}
