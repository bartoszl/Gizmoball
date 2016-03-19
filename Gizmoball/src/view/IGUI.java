package view;


import javax.swing.*;
import java.awt.*;

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
	
	void setMessageColor(Color color);
	
	String getFlipperPosition();
	
	String getGizmoShape();
}
