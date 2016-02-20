package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import View.RunGUI;
import model.Flipper;
import model.iGizmo;

public class Controller implements KeyListener, ActionListener{
	private RunGUI gui;
	private iGizmo flipper;
	
	public Controller(RunGUI gui){
		this.gui=gui;
		flipper = new Flipper(2, 2, true, new Color(0,255,0));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed: "+e.getKeyChar());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//probably activate the flipper on release?
		System.out.println("key released: "+e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("key typed: "+e.getKeyChar());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("exit"))
			System.exit(0);
	}

}
