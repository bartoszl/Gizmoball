package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;
import model.Absorber;
import model.Ball;
import model.IAbsorber;
import model.IBall;
import model.Model;

import javax.swing.JButton;

/**
 * @author Stephen Dundas
 *
 * Demonstrate a working absorber, ball motion, gravity, 
 * and friction. In running mode, with no bumpers or 
 * flippers on the screen and the ball sitting still in 
 * the absorber, you should be able to press a key, observe 
 * the ball shoot up out of the absorber, slow down as it 
 * rises, fall back to the absorber, and return to its 
 * original position. Also demonstrate that you can shoot 
 * it out a second time. (Note that you do not yet need to 
 * support configurable gravity or friction constants.)
 */

public class RunGUI{

	private JFrame frame;
	private Board board;
	private ActionListener controller;
	private Model model;
	//private Board board;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RunGUI window = new RunGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RunGUI() {
		
		this.model = new Model();
		//this.model.addObserver(this);
		controller = new Controller(model);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gizmo Proto 2");
		frame.setBounds(100, 100, 564, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 147, 441);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(controller);
		btnRun.setActionCommand("run");
		btnRun.setBounds(10, 10, 127, 58);
		panel.add(btnRun);
		
		JButton btnTick = new JButton("Tick");
		btnTick.addActionListener(controller);
		btnTick.setActionCommand("tick");
		btnTick.setBounds(10, 79, 127, 58);
		panel.add(btnTick);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(controller);
		btnQuit.setActionCommand("exit");
		btnQuit.setBounds(10, 148, 127, 58);
		panel.add(btnQuit);
		
		board = new Board(model);
		board.setBounds(147, 0, 405, 405);
		frame.getContentPane().add(board);
	}
	
	public void repaint(){
		frame.repaint();
	}
}
