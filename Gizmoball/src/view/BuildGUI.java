package view;

import controller.*;
import model.IGBallModel;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class BuildGUI implements IGUI {

	private JFrame frame;
	private JPanel panel;
    private JTextField txtOutput;
	private BuildBoard board;
    private Main main;
	private IGBallModel model;
    private ButtonGroup componentGroup;
    private DefaultComboBoxModel gizmoShapes;
    private DefaultComboBoxModel flipperPositions;
    private BuildModeBtnListener buildModeBtnListener;

	/**
	 * Create the application.
	 */
	public BuildGUI(Main main, IGBallModel model) {
        constructor(main, model);
        createFrame();
		initialize();
	}

	/**
	 * Alternate constructor that takes in a JFrame object
	 */
	BuildGUI(Main main, IGBallModel model, JFrame frame) {
		constructor(main, model);
        this.frame = frame;
		initialize();
		frame.repaint();
	}
	
	/**
	 * Method that merges common constructor code to avoid duplication.
	 */
	private void constructor(Main main, IGBallModel model){
        this.main = main;
        this.model = model;
        this.board = new BuildBoard(model);
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
        buildModeBtnListener = new BuildModeBtnListener(this,board,model,main);

		panel = createBuildMenu();
        panel.setBounds(0, 0, 220, 405);
		frame.getContentPane().add(panel);
        panel.setLayout(null);

		board.setBounds(220, 0, 405, 405);
		frame.getContentPane().add(board);
		AddKeyConnectListener akcl = new AddKeyConnectListener(this, model);
        DeleteKeyConnectListener dkcl = new DeleteKeyConnectListener(this, model);
        board.addKeyListener(akcl);
        board.addKeyListener(dkcl);
        board.addMouseListener(new AddGizmoListener(this, model));
        board.addMouseListener(new AddBallListener(this, model));
        board.addMouseListener(new AddAbsorberListener(this, model));
        board.addMouseListener(new AddFlipperListener(this, model));
        board.addMouseListener(new RotateComponentListener(this, model));
        board.addMouseListener(new DeleteComponentListener(this, model));
        board.addMouseListener(new AddConnectListener(this, model));
        board.addMouseListener(new MoveGizmoListener(this, model));
        board.addMouseListener(new DeleteConnectionListener(this, model));
        board.addMouseListener(akcl);
        board.addMouseListener(dkcl);
        board.addMouseListener(new DeleteKeyConnectListener(this, model));
		board.setBounds(220, 0, 405, 405);
		frame.getContentPane().add(board);
		board.setBounds(220, 0, 405, 405);
		frame.getContentPane().add(board);

        txtOutput = new JTextField();
        txtOutput.setEditable(false);
        txtOutput.setText("Build Mode");
        Font font = txtOutput.getFont();
        txtOutput.setFont(font.deriveFont(Font.BOLD));
		txtOutput.setBounds(0, 405, 634, 20);
		frame.getContentPane().add(txtOutput);
		
		JMenuBar menuBar = createMenuBar();
		frame.setJMenuBar(menuBar);
        frame.setVisible(true);
	}
	
	private JPanel createBuildMenu() {
        JPanel newPanel = new JPanel();

        JRadioButton rdbtnGizmo = new JRadioButton("Gizmo");
        rdbtnGizmo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnGizmo.setBounds(10, 41, 84, 23);
        newPanel.add(rdbtnGizmo);

        JComboBox cbCompType = new JComboBox();
        cbCompType.setFont(new Font("Tahoma", Font.PLAIN, 11));
        gizmoShapes = new DefaultComboBoxModel(new String[]{"Circle", "Square", "Triangle"});
        cbCompType.setModel(gizmoShapes);
        cbCompType.setBounds(100, 41, 105, 22);
        newPanel.add(cbCompType);

        JRadioButton rdbtnBall = new JRadioButton("Ball");
        rdbtnBall.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnBall.setBounds(10, 67, 109, 23);
        newPanel.add(rdbtnBall);

        JRadioButton rdbtnAbsorber = new JRadioButton("Absorber");
        rdbtnAbsorber.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnAbsorber.setBounds(10, 93, 109, 23);
        newPanel.add(rdbtnAbsorber);

        JRadioButton rdbtnFlipper = new JRadioButton("Flipper");
        rdbtnFlipper.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnFlipper.setBounds(10, 119, 84, 23);
        newPanel.add(rdbtnFlipper);

        componentGroup = new ButtonGroup();
        componentGroup.add(rdbtnGizmo);
        componentGroup.add(rdbtnBall);
        componentGroup.add(rdbtnAbsorber);
        componentGroup.add(rdbtnFlipper);
        rdbtnGizmo.setSelected(true);

        JComboBox cbFlipSide = new JComboBox();
        cbFlipSide.setFont(new Font("Tahoma", Font.PLAIN, 11));
        flipperPositions = new DefaultComboBoxModel(new String[]{"Left", "Right"});
        cbFlipSide.setModel(flipperPositions);
        cbFlipSide.setBounds(100, 119, 105, 22);
        newPanel.add(cbFlipSide);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 153, 195, 2);
        newPanel.add(separator_1);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(10, 234, 195, 2);
        newPanel.add(separator_2);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(10, 349, 195, 2);
        newPanel.add(separator_3);
        
        JButton btnRunMode = new JButton("Run Mode");
        btnRunMode.setBounds(10, 362, 195, 23);
        btnRunMode.setActionCommand("Swap");
        btnRunMode.addActionListener(buildModeBtnListener);
        newPanel.add(btnRunMode);

        JToggleButton tglbtnAddComp = new JToggleButton("Add Component");
        tglbtnAddComp.setBounds(10, 11, 195, 23);
        tglbtnAddComp.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnAddComp);

        JToggleButton tglbtnRotate = new JToggleButton("Rotate");
        tglbtnRotate.setBounds(10, 166, 93, 23);
        tglbtnRotate.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnRotate);

        JToggleButton tglbtnDelete = new JToggleButton("Delete");
        tglbtnDelete.setBounds(112, 166, 93, 23);
        tglbtnDelete.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnDelete);

        JToggleButton tglbtnMove = new JToggleButton("Move");
        tglbtnMove.setBounds(10, 200, 93, 23);
        tglbtnMove.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnMove);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(112, 200, 93, 23);
        btnClear.addActionListener(buildModeBtnListener);
        newPanel.add(btnClear);

        JToggleButton tglbtnConnect = new JToggleButton("Connect");
        tglbtnConnect.setBounds(10, 247, 93, 23);
        tglbtnConnect.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnConnect);

        JToggleButton tglbtnDisconnect = new JToggleButton("Disconnect");
        tglbtnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 11));
        tglbtnDisconnect.setBounds(112, 247, 93, 23);
        tglbtnDisconnect.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnDisconnect);

        JToggleButton tglbtnKeyConnect = new JToggleButton("Key Connect");
        tglbtnKeyConnect.setBounds(10, 281, 195, 23);
        tglbtnKeyConnect.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnKeyConnect);

        JToggleButton tglbtnKeyDisconnect = new JToggleButton("Key Disconnect");
        tglbtnKeyDisconnect.setBounds(10, 315, 195, 23);
        tglbtnKeyDisconnect.addActionListener(buildModeBtnListener);
        newPanel.add(tglbtnKeyDisconnect);

        ButtonGroup mainGroup = new ButtonGroup();
        mainGroup.add(tglbtnAddComp);
        mainGroup.add(tglbtnRotate);
        mainGroup.add(tglbtnMove);
        mainGroup.add(tglbtnDelete);
        mainGroup.add(tglbtnConnect);
        mainGroup.add(tglbtnDisconnect);
        mainGroup.add(tglbtnKeyConnect);
        mainGroup.add(tglbtnKeyDisconnect);

        return newPanel;
    }
	
	private JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();

		JMenu mnModel = createModelMenu();
		menuBar.add(mnModel);

		JMenu mnPhysics = createPhysicsMenu();
		menuBar.add(mnPhysics);
		
		return menuBar;
	}

    private JMenu createModelMenu(){
        JMenu mnModel = new JMenu("Model");
        JMenuItem mntmLoad, mntmReload, mntmSave, mntmQuit;

        mntmLoad = new JMenuItem("Load");
        mntmLoad.addActionListener(buildModeBtnListener);
        mnModel.add(mntmLoad);
        mntmReload = new JMenuItem("Reload");
        mntmReload.addActionListener(buildModeBtnListener);
        mnModel.add(mntmReload);
        mntmSave = new JMenuItem("Save");
        mntmSave.addActionListener(buildModeBtnListener);
        mnModel.add(mntmSave);
        JSeparator separator = new JSeparator();
        mnModel.add(separator);
        mntmQuit = new JMenuItem("Quit");
        mntmQuit.addActionListener(buildModeBtnListener);
        mnModel.add(mntmQuit);

        return mnModel;
    }

    private JMenu createPhysicsMenu(){
        JMenu mnPhysics = new JMenu("Physics");
        JMenuItem mntmFriction, mntmGravity;

        mntmFriction = new JMenuItem("Friction");
        mntmFriction.addActionListener(buildModeBtnListener);
        mnPhysics.add(mntmFriction);
        mntmGravity = new JMenuItem("Gravity");
        mntmGravity.addActionListener(buildModeBtnListener);
        mnPhysics.add(mntmGravity);

        return mnPhysics;
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
    public void setMessage(String message) {
        txtOutput.setText(message);
    }

    @Override
    public void setMessageColor(Color color) {
        txtOutput.setForeground(color);
    }

    @Override
    public String getSelectedComponent() {
        for (Enumeration<AbstractButton> buttons = componentGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) return button.getText();
        }
        return null;
    }

    @Override
    public String getFlipperPosition() { return flipperPositions.getSelectedItem().toString(); }

    @Override
    public String getGizmoShape() {
        return gizmoShapes.getSelectedItem().toString();
    }
}
