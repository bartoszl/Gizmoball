package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.RunGUI;
import model.Ball;

public class Controller implements IController{
	private RunGUI gui;
	
	public Controller(RunGUI gui){
		this.gui=gui;
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// handle close

		switch (command) {
		case "add ball":
			addBall();
			break;
		case "exit":
			System.exit(0);
		}
	}

	private void addBall() {
		//currently it just makes a new ball object and adds it (testing reasons)
		gui.addBallToGrid(new Ball(200,200,100,0));
	}
}
