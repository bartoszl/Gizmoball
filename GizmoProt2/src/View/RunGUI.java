package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;
import Controller.IController;
import model.Absorber;
import model.Ball;
import model.IAbsorber;
import model.IBall;

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

public class RunGUI {

	private JFrame frame;
	private GridView gridView;
	private IController controller;

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
		controller = new Controller(this);
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
		
		JButton btnRun = new JButton("Fire Ball");
		btnRun.addActionListener(controller);
		btnRun.setActionCommand("add ball");
		btnRun.setBounds(10, 10, 127, 58);
		panel.add(btnRun);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(controller);
		btnQuit.setActionCommand("exit");
		btnQuit.setBounds(10, 79, 127, 58);
		panel.add(btnQuit);
		
		gridView = new GridView();
		gridView.setBounds(147, 0, 405, 405);
		frame.getContentPane().add(gridView);
		
		//add the absorber
		addAbsorberToGrid(new Absorber(0,380, 400,400));
	}
	
	public void addBallToGrid(IBall ball){
		gridView.addBall(ball);
		frame.repaint();
	}
	
	public void addAbsorberToGrid(IAbsorber absorber){
		gridView.addAbsorber(absorber);
		frame.repaint();
	}
	
	private class GridView extends JPanel{
		List<IAbsorber> absorbers = new ArrayList<IAbsorber>();
		List<IBall> balls = new ArrayList<IBall>();
		
		public void paintComponent(Graphics g){
			drawGrid(g);
			drawAbsorber(g);
			drawBalls(g);
		}
		
		private void drawGrid(Graphics g){
			for(int i = 0; i <= 20; i++){
				g.drawLine(0, i*20, 400, i*20);
				g.drawLine(i*20, 0, i*20, 400);
			}
		}
		
		private void drawBalls(Graphics g) {
			for(IBall ball: balls){
				int size = (int)ball.getRadius()*2;
				g.setColor(ball.getColor());
				g.fillOval((int)ball.getX(), (int)ball.getY(), size, size);
			}	
		}
		
		public boolean addBall(IBall ball){
			return balls.add(ball);
		}
		
		private void drawAbsorber(Graphics g) {
			for(IAbsorber absorb: absorbers){
				g.setColor(absorb.getColor());
				g.fillRect((int)absorb.getXTopLeft(), (int)absorb.getYTopLeft(), (int)absorb.getWidth(), (int)absorb.getHeight());
			}	
		}
		
		public boolean addAbsorber(IAbsorber absorber){
			return absorbers.add(absorber);
		}
	}
}
