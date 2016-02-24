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
import model.Flipper.Position;
import model.iGizmo;

public class Controller implements KeyListener, ActionListener{
	private RunGUI gui;
	private Flipper flipper;
	private Movement movement;
	private Timer timer;
	private MagicKeyListener mkl;
	private Angle a;
	
	public Controller(RunGUI gui, Flipper flipper){
		this.a = Angle.DEG_90;
		this.gui = gui;
		this.flipper = flipper;
		this.mkl = new MagicKeyListener(this);
		this.timer = new Timer(50, this);
		this.timer.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("key pressed");
		if(flipper.getPosition() == Position.VERTICAL) {
			a = flipper.getLeft();
			flipper.setMovement(Movement.FORWARDS);
		//Thread t = new Thread(flipper);
		//t.start();
		} else if(flipper.getPosition() == Position.BETWEEN) {
			if(flipper.getMovement() == Movement.BACKWARDS) {
				//reverse it
				flipper.setLeft(Angle.DEG_90.minus(flipper.getLeft()));
			}
			a = flipper.getLeft();
			flipper.setMovement(Movement.FORWARDS);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("key released");
		if(flipper.getPosition() == Position.HORIZONTAL) {
		//probably activate the flipper on release?
			a = Angle.DEG_90.minus(flipper.getLeft());
			flipper.setMovement(Movement.BACKWARDS);
		} else if(flipper.getPosition() == Position.BETWEEN) {
			if(flipper.getMovement() == Movement.FORWARDS) {
				//reverse it
				flipper.setLeft(Angle.DEG_90.minus(flipper.getLeft()));
			}
			a = flipper.getLeft();
			flipper.setMovement(Movement.BACKWARDS);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("key typed: "+e.getKeyChar());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()!=null){
			if(e.getActionCommand().equals("exit"))
				System.exit(0);
		}
		if(flipper.getMovement() == Movement.FORWARDS) {
			//System.out.println("forwards");
			//System.out.println(flipper.getPosition());
			//flipper.setMovement(Movement.FORWARDS);
			a = flipper.movePerTick(a);	
		} else if(flipper.getMovement() == Movement.BACKWARDS) {
			//System.out.println("backwards");
			//System.out.println(flipper.getPosition());
			a = flipper.movePerTick(a);		
		}
	}

}
