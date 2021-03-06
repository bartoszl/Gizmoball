package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.Model;

public class RunListener implements ActionListener {

    private Timer timer;
    private Model model;

    public RunListener(Model m) {
        model = m;
        timer = new Timer(50, this);
    }

    public final void actionPerformed(final ActionEvent e) {

        if (e.getSource() == timer) {
            model.moveBall();
        } else
            switch (e.getActionCommand()) {
                case "Start":
                    timer.start();
                    model.setMoving(true);
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Tick":
                    model.moveBall();
                    break;
                case "Quit":
                    System.exit(0);
                    break;
            }
    }
}
