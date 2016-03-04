package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import model.IGBallModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Color;

public class RunGUI implements IGUI{

	private JFrame frame;
	private JPanel panel;
	private RunBoard board;
    private Main main;
    private IGBallModel model;

	/**
	 * Create the application.
	 */
	public RunGUI(Main main, IGBallModel model) {
		this.main = main;
		this.model = model;
		createFrame();
        initialize();
	}

	/**
	 * Alternate constructor that takes in a JFrame object
	 */
	public RunGUI(Main main, IGBallModel model, JFrame frame) {
        this.main = main;
        this.model = model;
        this.frame = frame;
		initialize();
		frame.repaint();
	}
	
	/**
	 * Initialize the frame.
	 */
	private void createFrame(){
		frame = new JFrame("GizmoBall - Build Mode");
		frame.setBounds(100, 100, 650, 485);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        panel = new JPanel();
        panel.setBounds(0, 0, 200, 405);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(10, 11, 180, 50);
        panel.add(btnStart);

        JButton btnStop = new JButton("Stop");
        btnStop.setBounds(10, 133, 180, 50);
        panel.add(btnStop);

        JButton btnTick = new JButton("Tick");
        btnTick.setBounds(10, 72, 180, 50);
        panel.add(btnTick);

        JButton btnBuildMode = new JButton("Build Mode");
        btnBuildMode.setBounds(10, 344, 180, 50);
        panel.add(btnBuildMode);
        btnBuildMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	board.delete();
            	board=null;
            	frame.remove(frame.getContentPane());
            	frame.remove(frame.getJMenuBar());
            	frame.remove(panel);
                main.switchToBuild(frame);
            }
        });

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 331, 180, 2);
        panel.add(separator_1);

        board = new RunBoard(model);
        board.setBounds(200, 0, 434, 405);
        frame.getContentPane().add(board);

        JTextPane textPane = new JTextPane();
        textPane.setBounds(0, 405, 634, 20);
        frame.getContentPane().add(textPane);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("Model");
        menuBar.add(mnFile);

        JMenuItem mntmNewMenuItem = new JMenuItem("Load");
        mnFile.add(mntmNewMenuItem);

        JMenuItem mntmReloadModel = new JMenuItem("Reload");
        mnFile.add(mntmReloadModel);

        JMenuItem mntmSaveModel = new JMenuItem("Save");
        mnFile.add(mntmSaveModel);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        JMenuItem mntmQuit = new JMenuItem("Quit");
        mnFile.add(mntmQuit);

        frame.setVisible(true);
    }

    @Override
    public Board getGridView() {
        return board;
    }
}