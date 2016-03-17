package view;

import javax.swing.*;

public interface IRunGUI {
    Board getGridView();
    JFrame getFrame();
    JPanel getPanel();
    String getMessage();
    void setMessage(String message);

}
