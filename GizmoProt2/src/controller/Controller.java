package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Ball;
import model.Model;
import view.RunGUI;

public class Controller implements ActionListener{
	private RunGUI gui;
	private Model model;
	
	public Controller(RunGUI gui, Model m){
		this.gui=gui;
		this.model = m;
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		// handle close

		switch (command) {
		case "fire ball":
			fireBall();
			break;
		case "exit":
			System.exit(0);
		}
	}

	private void fireBall() {
		//currently it just makes a new ball object and adds it (testing reasons)
		model.moveBall();
	}
}
