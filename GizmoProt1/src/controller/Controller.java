package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import physics.Angle;
import View.RunGUI;
import model.Flipper;
import model.Flipper.Movement;
import model.iGizmo;

public class Controller implements KeyListener, ActionListener{
	private RunGUI gui;
	private Flipper flipper;
	private Movement movement;
	private Timer timer;
	
	public Controller(RunGUI gui, Flipper flipper){
		this.gui=gui;
		this.flipper = flipper;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Thread t = new Thread(flipper);
		//t.start();
		System.out.println("forwards");
		flipper.setMovement(Movement.FORWARDS);
		Angle a = Angle.DEG_90;
		while(flipper.getMovement() == Movement.FORWARDS) {
			a = flipper.movePerTick(a);
			/*try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//probably activate the flipper on release?
		System.out.println("backwards");
		flipper.setMovement(Movement.BACKWARDS);
		//Angle a = Angle.DEG_90.minus(flipper.getLeft());
		Angle a = Angle.DEG_90;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(flipper.getMovement() == Movement.BACKWARDS) {
			a = flipper.movePerTick(a);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("key typed: "+e.getKeyChar());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("exit"))
			System.exit(0);
	}

}
