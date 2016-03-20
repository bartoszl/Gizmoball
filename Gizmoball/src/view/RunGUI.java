package view;

import javax.swing.*;

import controller.RunModeBtnListener;
import controller.RunModeKeyListener;
import model.IGBallModel;

import java.awt.*;

class RunGUI implements IGUI {

	private JFrame frame;
	private JPanel panel;
    private JTextField txtOutput;
	private RunBoard board;
    private Main main;
    private IGBallModel model;
    private RunModeBtnListener runModeBtnListener;

	/**
	 * Create the application.
	 */
	RunGUI(Main main, IGBallModel model) {
		this.main = main;
		this.model = model;
		createFrame();
        initialize();
	}

	/**
	 * Alternate constructor that takes in a JFrame object
	 */
	RunGUI(Main main, IGBallModel model, JFrame frame) {
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

        panel = createRunMenu();
        panel.setBounds(0, 0, 200, 405);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        board = new RunBoard(model);
        board.addKeyListener(new RunModeKeyListener(this, model, main));
        board.setBounds(220, 0, 405, 405);
        frame.getContentPane().add(board);

        txtOutput = new JTextField();
        txtOutput.setEditable(false);
        txtOutput.setText("Run Mode");

        txtOutput.setBounds(0, 405, 634, 20);
        frame.getContentPane().add(txtOutput);

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    private JPanel createRunMenu() {
        JPanel newPanel = new JPanel();

        JButton btnStart = new JButton("Start");
        btnStart.setBounds(10, 11, 180, 50);
        btnStart.addActionListener(runModeBtnListener);
        newPanel.add(btnStart);

        JButton btnTick = new JButton("Tick");
        btnTick.setBounds(10, 72, 180, 50);
        btnTick.addActionListener(runModeBtnListener);
        newPanel.add(btnTick);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(10, 133, 180, 50);
        btnReset.addActionListener(runModeBtnListener);
        newPanel.add(btnReset);

        JButton btnStop = new JButton("Stop");
        btnStop.setBounds(10, 194, 180, 50);
        btnStop.addActionListener(runModeBtnListener);
        newPanel.add(btnStop);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 331, 180, 2);
        newPanel.add(separator);

        JButton btnBuildMode = new JButton("Build Mode");
        btnBuildMode.setBounds(10, 344, 180, 50);
        btnBuildMode.setActionCommand("Swap");
        btnBuildMode.addActionListener(runModeBtnListener);
        newPanel.add(btnBuildMode);

        return newPanel;
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

    @Override
    public void setMessage(String message) { txtOutput.setText(message); }

    @Override
    public void setMessageColor(Color color) { txtOutput.setForeground(color); }

    @Override
    public String getSelectedComponent() { return null; }

    @Override
    public String getFlipperPosition() { return null; }

    @Override
    public String getGizmoShape() { return null; }
}