package View;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

import javax.swing.JButton;

/**
 * @author Stephen Dundas
 * 
 * Demonstrate key-press triggering of a flipper on the 
 * screen. When a key is pressed, the flipper should rotate 
 * 90 degrees; after the key is released, the flipper should 
 * rotate back to its original position. You should be able 
 * to trigger it a second or third time by pressing the key 
 * again after it has returned to the original position. (You 
 * need not demonstrate connecting the key to the flipper in 
 * build mode.)
 */

public class RunGUI {

	private JFrame frame;
	private static Controller control;
	private static RunGUI window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new RunGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		control = new Controller(window);
	}

	/**
	 * Create the application.
	 */
	public RunGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gizmo Proto 1");
		frame.setBounds(100, 100, 264, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 147, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnRun = new JButton("Flip");
		btnRun.setBounds(10, 10, 127, 36);
		panel.add(btnRun);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(10, 57, 127, 36);
		panel.add(btnQuit);
		
		JPanel panel_1 = new GridView();
		panel_1.setBounds(147, 0, 225, 225);
		frame.getContentPane().add(panel_1);
	}
	
	private class GridView extends JPanel{
		public void paintComponent(Graphics g){
			for(int i = 0; i <= 5; i++){
				g.drawLine(0, i*20, 100, i*20);
				g.drawLine(i*20, 0, i*20, 100);
			}
		}	
	}
}
