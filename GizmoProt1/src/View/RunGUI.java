package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.Controller;
import model.Flipper;


/**
 * @author Stephen Dundas and Martin Peev
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
	private Controller control;
	private static RunGUI window;
	private Flipper flip;
	

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
	}

	/**
	 * Create the application.
	 */
	public RunGUI() {
		flip = new Flipper(9,9,true,new Color(0,255,0), "asd");
		control = new Controller(window, flip);	
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gizmo Proto 1");
		frame.setBounds(100, 100, 407, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addKeyListener(control);
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(control);
		quit.setActionCommand("exit");
		mnNewMenu.add(quit);
		
		JPanel panel_1 = new Board(flip);
		panel_1.setBounds(0, 0, 405, 405);
		frame.getContentPane().add(panel_1);
	}
}
