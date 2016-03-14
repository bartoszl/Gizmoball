package view;

import controller.*;
import model.GBallModel;
import model.IGBallModel;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.Enumeration;

public class BuildGUI implements IGUI{

	private JFrame frame;
	private JPanel panel;
	private BuildBoard board;
    private Main main;
	private IGBallModel model;
    private ButtonGroup componentGroup;
    private DefaultComboBoxModel gizmoShapes;
    private DefaultComboBoxModel flipperPositions;

	/**
	 * Create the application.
	 */
	public BuildGUI(Main main, IGBallModel model) {
        constructor(main, model);
        this.main = main;
        this.model = model;
        this.board = new BuildBoard(model);
        createFrame();
		initialize();
	}

	/**
	 * Alternate constructor that takes in a JFrame object
	 */
	public BuildGUI(Main main, IGBallModel model, JFrame frame) {
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

    public Board getGridView() {
        return board;
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
		JPanel buildMenu = create_buildMenu();
		buildMenu.setBounds(0, 0, 220, 405);
		frame.getContentPane().add(buildMenu);
		buildMenu.setLayout(null);

		board.setBounds(220, 0, 405, 405);
		frame.getContentPane().add(board);
		AddKeyConnectListener akcl = new AddKeyConnectListener(this, model);
        board.addKeyListener(akcl);
        board.addMouseListener(new AddGizmoListener(this, model));
        board.addMouseListener(new AddBallListener(this, model));
        board.addMouseListener(new AddAbsorberListener(this, model));
        board.addMouseListener(new AddFlipperListener(this, model));
        board.addMouseListener(new RotateComponentListener(this, model));
        board.addMouseListener(new DeleteComponentListener(this, model));
        board.addMouseListener(new AddKeyConnectListener(this, model));
        board.addMouseListener(new AddConnectListener(this, model));
        board.addMouseListener(new MoveGizmoListener(board, model));
        board.addMouseListener(akcl);
        board.addMouseListener(new DeleteConnectionListener(this, model));
        board.addMouseListener(new DeleteKeyConnectListener(this, model));
		board.setBounds(220, 0, 405, 405);
		frame.getContentPane().add(board);
		board.setBounds(220, 0, 405, 405);
		frame.getContentPane().add(board);
		
		JTextField txtOutput = new JTextField();
		txtOutput.setText("[Example Text]");
		txtOutput.setEnabled(false);
		txtOutput.setBounds(0, 405, 634, 20);
		frame.getContentPane().add(txtOutput);
		
		JMenuBar menuBar = create_menuBar();
		frame.setJMenuBar(menuBar);
        frame.setVisible(true);
	}
	
	private JPanel create_buildMenu() {
        panel = new JPanel();

        JRadioButton rdbtnGizmo = new JRadioButton("Gizmo");
        rdbtnGizmo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnGizmo.setBounds(10, 41, 84, 23);
        panel.add(rdbtnGizmo);

        JComboBox cbCompType = new JComboBox();
        cbCompType.setFont(new Font("Tahoma", Font.PLAIN, 11));
        gizmoShapes = new DefaultComboBoxModel(new String[]{"Circle", "Square", "Triangle"});
        cbCompType.setModel(gizmoShapes);
        cbCompType.setBounds(100, 41, 105, 22);
        panel.add(cbCompType);

        JRadioButton rdbtnBall = new JRadioButton("Ball");
        rdbtnBall.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnBall.setBounds(10, 67, 109, 23);
        panel.add(rdbtnBall);

        JRadioButton rdbtnAbsorber = new JRadioButton("Absorber");
        rdbtnAbsorber.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnAbsorber.setBounds(10, 93, 109, 23);
        panel.add(rdbtnAbsorber);

        JRadioButton rdbtnFlipper = new JRadioButton("Flipper");
        rdbtnFlipper.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnFlipper.setBounds(10, 119, 84, 23);
        panel.add(rdbtnFlipper);

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
        panel.add(cbFlipSide);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 153, 195, 2);
        panel.add(separator_1);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(10, 234, 195, 2);
        panel.add(separator_2);

        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(10, 349, 195, 2);
        panel.add(separator_3);

        BuildModeBtnListener btnListener = new BuildModeBtnListener(this, board, model, main);
        
        JButton btnRunMode = new JButton("Run Mode");
        btnRunMode.setBounds(10, 362, 195, 23);
        btnRunMode.setActionCommand("Swap");
        btnRunMode.addActionListener(btnListener);
        panel.add(btnRunMode);

        JToggleButton tglbtnAddComp = new JToggleButton("Add Component");
        tglbtnAddComp.setBounds(10, 11, 195, 23);
        tglbtnAddComp.addActionListener(btnListener);
        panel.add(tglbtnAddComp);

        JToggleButton tglbtnRotate = new JToggleButton("Rotate");
        tglbtnRotate.setBounds(10, 166, 93, 23);
        tglbtnRotate.addActionListener(btnListener);
        panel.add(tglbtnRotate);

        JToggleButton tglbtnDelete = new JToggleButton("Delete");
        tglbtnDelete.setBounds(112, 166, 93, 23);
        tglbtnDelete.addActionListener(btnListener);
        panel.add(tglbtnDelete);

        JToggleButton tglbtnMove = new JToggleButton("Move");
        tglbtnMove.setBounds(10, 200, 93, 23);
        tglbtnMove.addActionListener(btnListener);
        panel.add(tglbtnMove);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(112, 200, 93, 23);
        btnClear.addActionListener(btnListener);
        panel.add(btnClear);

        JToggleButton tglbtnConnect = new JToggleButton("Connect");
        tglbtnConnect.setBounds(10, 247, 93, 23);
        tglbtnConnect.addActionListener(btnListener);
        panel.add(tglbtnConnect);

        JToggleButton tglbtnDisconnect = new JToggleButton("Disconnect");
        tglbtnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 11));
        tglbtnDisconnect.setBounds(112, 247, 93, 23);
        tglbtnDisconnect.addActionListener(btnListener);
        panel.add(tglbtnDisconnect);

        JToggleButton tglbtnKeyConnect = new JToggleButton("Key Connect");
        tglbtnKeyConnect.setBounds(10, 281, 195, 23);
        tglbtnKeyConnect.addActionListener(btnListener);
        panel.add(tglbtnKeyConnect);

        JToggleButton tglbtnKeyDisconnect = new JToggleButton("Key Disconnect");
        tglbtnKeyDisconnect.setBounds(10, 315, 195, 23);
        tglbtnKeyDisconnect.addActionListener(btnListener);
        panel.add(tglbtnKeyDisconnect);

        ButtonGroup mainGroup = new ButtonGroup();
        mainGroup.add(tglbtnAddComp);
        mainGroup.add(tglbtnRotate);
        mainGroup.add(tglbtnMove);
        mainGroup.add(tglbtnDelete);
        mainGroup.add(tglbtnConnect);
        mainGroup.add(tglbtnDisconnect);
        mainGroup.add(tglbtnKeyConnect);
        mainGroup.add(tglbtnKeyDisconnect);

        return panel;
    }
	
	private JMenuBar create_menuBar(){
		JMenuBar menuBar = new JMenuBar();

        //Model Menu on menuBar
		JMenu mnModel = create_ModelMenu();
		menuBar.add(mnModel);

        //Physics Menu on menuBar
		JMenu mnPhysics = create_PhysicsMenu();
		menuBar.add(mnPhysics);
		
		return menuBar;
	}

    private JMenu create_ModelMenu(){
        JMenu mnModel = new JMenu("Model");
        JMenuItem mntmLoad, mntmReload, mntmSave, mntmQuit;

        mntmLoad = new JMenuItem("Load");
        mntmLoad.addActionListener(new BuildModeBtnListener(this,board,model,main));
        mnModel.add(mntmLoad);
        mntmReload = new JMenuItem("Reload");
        mntmReload.addActionListener(new BuildModeBtnListener(this,board,model,main));
        mnModel.add(mntmReload);
        mntmSave = new JMenuItem("Save");
        mntmSave.addActionListener(new BuildModeBtnListener(this,board,model,main));
        mnModel.add(mntmSave);
        JSeparator separator = new JSeparator();
        mnModel.add(separator);
        mntmQuit = new JMenuItem("Quit");
        mntmQuit.addActionListener(new BuildModeBtnListener(this,board,model,main));
        mnModel.add(mntmQuit);

        return mnModel;
    }

    private JMenu create_PhysicsMenu(){
        JMenu mnPhysics = new JMenu("Physics");
        JMenuItem mntmFriction, mntmGravity;

        mntmFriction = new JMenuItem("Friction");
        mntmFriction.addActionListener(new BuildModeBtnListener(this, board, model, main));
        mnPhysics.add(mntmFriction);
        mntmGravity = new JMenuItem("Gravity");
        mntmGravity.addActionListener(new BuildModeBtnListener(this, board, model, main));
        mnPhysics.add(mntmGravity);

        return mnPhysics;
    }

    public String getSelectedButtonText() {
        for (Enumeration<AbstractButton> buttons = componentGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public String getFlipperPosition() { return flipperPositions.getSelectedItem().toString(); }

    public String getGizmoShape() {
        return gizmoShapes.getSelectedItem().toString();
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
