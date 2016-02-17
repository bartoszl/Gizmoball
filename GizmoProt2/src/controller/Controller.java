package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import model.Ball;
import model.Model;
import view.RunGUI;

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
			System.out.println("Im here");
		} else
			switch (e.getActionCommand()) {
				case "run":
					timer.start();
					break;
				case "tick":
					model.moveBall();
					break;
				case "exit":
					System.exit(0);
			}
	}
}
