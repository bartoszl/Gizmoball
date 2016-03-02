package view;

import model.Absorber;
import model.Ball;
import model.CircularBumper;
import model.IGBallModel;

import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class BuildGUI implements IGUI{

	public JFrame frame;
    private Main main;
	private ActionListener controller;
	private IGBallModel model;

	/**
	 * Create the application.
	 */
	public BuildGUI(Main main, IGBallModel model) {
        this.main = main;
        this.model = model;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("GizmoBall - Build Mode");
		frame.setBounds(100, 100, 650, 485);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel buildMenu = create_buildMenu();
		buildMenu.setBounds(0, 0, 220, 405);
		frame.getContentPane().add(buildMenu);
		buildMenu.setLayout(null);
		
		Board gridView = new Board(true, model);
		gridView.setBounds(220, 0, 405, 405);
		frame.getContentPane().add(gridView);
		
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
        JPanel panel = new JPanel();

        JRadioButton rdbtnGizmo = new JRadioButton("Gizmo");
        rdbtnGizmo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        rdbtnGizmo.setBounds(10, 41, 84, 23);
        panel.add(rdbtnGizmo);

        JComboBox cbCompType = new JComboBox();
        cbCompType.setFont(new Font("Tahoma", Font.PLAIN, 11));
        cbCompType.setModel(new DefaultComboBoxModel(new String[]{"Circle", "Square", "Triangle"}));
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

        ButtonGroup componentGroup = new ButtonGroup();
        componentGroup.add(rdbtnGizmo);
        componentGroup.add(rdbtnBall);
        componentGroup.add(rdbtnAbsorber);
        componentGroup.add(rdbtnFlipper);
        rdbtnGizmo.setSelected(true);

        JComboBox cbFlipSide = new JComboBox();
        cbFlipSide.setFont(new Font("Tahoma", Font.PLAIN, 11));
        cbFlipSide.setModel(new DefaultComboBoxModel(new String[]{"Right", "Left"}));
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

        JButton btnRunMode = new JButton("Run Mode");
        btnRunMode.setBounds(10, 362, 195, 23);
        panel.add(btnRunMode);
        btnRunMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.switchToRun();
                frame.dispose();
            }
        });

        JToggleButton tglbtnAddComp = new JToggleButton("Add Component");
        tglbtnAddComp.setBounds(10, 11, 195, 23);
        panel.add(tglbtnAddComp);

        JToggleButton tglbtnRotate = new JToggleButton("Rotate");
        tglbtnRotate.setBounds(10, 166, 93, 23);
        panel.add(tglbtnRotate);

        JToggleButton tglbtnDelete = new JToggleButton("Delete");
        tglbtnDelete.setBounds(112, 166, 93, 23);
        panel.add(tglbtnDelete);

        JToggleButton tglbtnMove = new JToggleButton("Move");
        tglbtnMove.setBounds(10, 200, 93, 23);
        panel.add(tglbtnMove);

        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(112, 200, 93, 23);
        panel.add(btnClear);

        JToggleButton tglbtnConnect = new JToggleButton("Connect");
        tglbtnConnect.setBounds(10, 247, 93, 23);
        panel.add(tglbtnConnect);

        JToggleButton tglbtnDisconnect = new JToggleButton("Disconnect");
        tglbtnDisconnect.setFont(new Font("Tahoma", Font.PLAIN, 11));
        tglbtnDisconnect.setBounds(112, 247, 93, 23);
        panel.add(tglbtnDisconnect);

        JToggleButton tglbtnKeyConnect = new JToggleButton("Key Connect");
        tglbtnKeyConnect.setBounds(10, 281, 195, 23);
        panel.add(tglbtnKeyConnect);

        JToggleButton tglbtnKeyDisconnect = new JToggleButton("Key Disconnect");
        tglbtnKeyDisconnect.setBounds(10, 315, 195, 23);
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
        mntmLoad.addActionListener(controller);
        mnModel.add(mntmLoad);
        mntmReload = new JMenuItem("Reload");
        mntmReload.addActionListener(controller);
        mnModel.add(mntmReload);
        mntmSave = new JMenuItem("Save");
        mntmSave.addActionListener(controller);
        mnModel.add(mntmSave);
        JSeparator separator = new JSeparator();
        mnModel.add(separator);
        mntmQuit = new JMenuItem("Quit");
        mntmQuit.addActionListener(controller);
        mnModel.add(mntmQuit);

        return mnModel;
    }

    private JMenu create_PhysicsMenu(){
        JMenu mnPhysics = new JMenu("Physics");
        JMenuItem mntmFriction, mntmGravity;

        mntmFriction = new JMenuItem("Friction");
        mnPhysics.add(mntmFriction);
        mntmGravity = new JMenuItem("Gravity");
        mnPhysics.add(mntmGravity);

        return mnPhysics;
    }
}
