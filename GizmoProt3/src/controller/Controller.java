package controller;

import model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
	private Model model;
	private Timer timer;
	
	public Controller(Model m){
		this.model = m;
		timer = new Timer(50, this);
	}
	
	@Override
	public final void actionPerformed(final ActionEvent e) {
		// handle close
		if(e.getSource() ==  timer){
			model.moveBall();
		} else
			switch (e.getActionCommand()) {
				case "run":
                    model.setMoving(true);
					timer.start();
					break;
                case "stop":
                    model.setMoving(false);
                    break;
				case "tick":
                    timer.stop();
                    model.setMoving(true);
					model.moveBall();
					break;
				case "exit":
					System.exit(0);
			}
	}
}
