package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import controller.RunModeBtnListener;
import controller.RunModeKeyListener;
import model.GBallModel;
import model.IGBallModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class RunGUI implements IGUI{

	private JFrame frame;
	private JPanel panel;
	private RunBoard board;
    private Main main;
    private IGBallModel model;
    private RunModeBtnListener runModeBtnListener;

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
		frame = new JFrame("GizmoBall");
		frame.setBounds(100, 100, 650, 485);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        runModeBtnListener = new RunModeBtnListener(this, model, main);
        panel = new JPanel();
        panel.setBounds(0, 0, 200, 405);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        final JButton btnStart = new JButton("Start");
        btnStart.setBounds(10, 11, 180, 50);
        btnStart.addActionListener(runModeBtnListener);
        panel.add(btnStart);

        JButton btnStop = new JButton("Stop");
        btnStop.setBounds(10, 133, 180, 50);
        btnStop.addActionListener(runModeBtnListener);
        panel.add(btnStop);

        JButton btnTick = new JButton("Tick");
        btnTick.setBounds(10, 72, 180, 50);
        btnTick.addActionListener(runModeBtnListener);
        panel.add(btnTick);

        JButton btnBuildMode = new JButton("Build Mode");
        btnBuildMode.setBounds(10, 344, 180, 50);
        btnBuildMode.setActionCommand("Swap");
        btnBuildMode.addActionListener(runModeBtnListener);
        panel.add(btnBuildMode);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 331, 180, 2);
        panel.add(separator_1);

        board = new RunBoard(model);
        board.addKeyListener(new RunModeKeyListener(this, model, main));
        board.setBounds(220, 0, 405, 405);
        frame.getContentPane().add(board);

        JTextPane textPane = new JTextPane();
        textPane.setBounds(0, 405, 634, 20);
        frame.getContentPane().add(textPane);

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu mnFile = new JMenu("Model");
        menuBar.add(mnFile);

        JMenuItem mntmLoadModel = new JMenuItem("Load");
        mntmLoadModel.addActionListener(runModeBtnListener);
        mnFile.add(mntmLoadModel);

        JMenuItem mntmReloadModel = new JMenuItem("Reload");
        mntmReloadModel.addActionListener(runModeBtnListener);
        mnFile.add(mntmReloadModel);

        JMenuItem mntmSaveModel = new JMenuItem("Save");
        mntmSaveModel.addActionListener(runModeBtnListener);
        mnFile.add(mntmSaveModel);

        JSeparator separator = new JSeparator();
        mnFile.add(separator);

        JMenuItem mntmQuit = new JMenuItem("Quit");
        mntmQuit.addActionListener(runModeBtnListener);
        mnFile.add(mntmQuit);

        return menuBar;
    }

    @Override
    public Board getGridView() {
        return board;
    }

	@Override
	public JFrame getFrame() {
		return frame;
	}
	
	@Override
	public JPanel getPanel() {
		return panel;
	}
}