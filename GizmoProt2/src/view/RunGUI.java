package view;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		btnRun.setBounds(10, 10, 127, 58);
		panel.add(btnRun);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(10, 79, 127, 58);
		panel.add(btnQuit);
		
		JPanel gridView = new GridView();
		gridView.setBounds(147, 0, 405, 405);
		frame.getContentPane().add(gridView);
	}
	
	private class GridView extends JPanel{
		public void paintComponent(Graphics g){
			for(int i = 0; i <= 20; i++){
				g.drawLine(0, i*20, 400, i*20);
				g.drawLine(i*20, 0, i*20, 400);
			}
		}	
	}
}
