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
	
	void setMessage(String message);

    void setMessageColor(Color color);
	
	String getSelectedComponent();
	
	String getFlipperPosition();
	
	String getGizmoShape();
}
