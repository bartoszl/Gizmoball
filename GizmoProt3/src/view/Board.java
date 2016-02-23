package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

class Board extends JPanel implements Observer {
    private int width, height;
    private Model gm;

    public Board(Model m) {
        width = 400;
        height = 400;
        m.addObserver(this);
        gm = m;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw all square bumpers
        for (SquareBumper sBumper : gm.getSquareBumpers()) {
            g2.setColor(sBumper.getColor());
            g2.fillRect((int) sBumper.getX(), (int) sBumper.getY(), 20, 20);
        }

        Ball b = gm.getBall();

        // Draw the ball
        if (b != null) {
            g2.setColor(b.getColor());
            int x = (int) (b.getX() - b.getRadius());
            int y = (int) (b.getY() - b.getRadius());
            int width = (int) (2 * b.getRadius());
            g2.fillOval(x, y, width, width);
        }

        // Draw all triangle bumpers
        for (TriangularBumper tBumper : gm.getTriangularBumpers()) {
            g2.setColor(tBumper.getColor());
            g2.fillPolygon(tBumper.getXCoords(), tBumper.getYCoords(), 3);
        }

        //Draw all circular bumpers
        for (CircularBumper cBumper : gm.getCircularBumpers()) {
            g2.setColor(cBumper.getColor());
            int x = (int) (cBumper.getX() - cBumper.getRadius());
            int y = (int) (cBumper.getY() - cBumper.getRadius());
            int width = (int) (2 * cBumper.getRadius());
            g2.fillOval(x, y, width, width);
        }

        // Draw the absorber
        Absorber absorber = gm.getAbsorber();
        g.setColor(absorber.getColor());
        g.fillRect((int) absorber.getXTopLeft(), (int) absorber.getYTopLeft(), (int) absorber.getWidth(), (int) absorber.getHeight());
    }

    public void update(Observable o, Object arg) {
        repaint();
    }
}
