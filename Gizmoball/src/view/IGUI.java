package view;


import model.GBallModel;

import javax.swing.*;

/**
 * Created by Stephen on 01/03/2016.
 */
public interface IGUI {
	Board getGridView();
	JFrame getFrame();
	JPanel getPanel();
	String getMessage();
	void setMessage(String message);
	String getSelectedComponent();

	String getFlipperPosition();

	String getGizmoShape();
}
