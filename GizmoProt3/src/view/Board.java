package View;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.util.*;

class Board extends JPanel implements Observer {
    private Model model;
/*    java.util.List<Attributes> squareBumpers = new ArrayList<Attributes>();
    java.util.List<Attributes> circularBumpers = new ArrayList<Attributes>();
    java.util.List<Triangle> triangularBumpers = new ArrayList<Triangle>();
    java.util.List<Attributes> leftFlippers = new ArrayList<Attributes>();
    java.util.List<Attributes> rightFlippers = new ArrayList<Attributes>();*/

    private int width, height;
    private Model gm;

    public Board(Model m) {
        //this.model = m;
        //m.addObserver(this);
        //addComponents();
        width = 400;
        height = 400;
        m.addObserver(this);
        gm = m;
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw all square bumpers
        for (SquareBumper sBumper : gm.getSquareBumpers()) {
            g2.fillRect((int) sBumper.getX(), (int) sBumper.getY(), 20, 20);
        }

        Ball b = gm.getBall();

        if (b != null) {
            g2.setColor(b.getColor());
            int x = (int) (b.getX() - b.getRadius());
            int y = (int) (b.getY() - b.getRadius());
            int width = (int) (2 * b.getRadius());
            g2.fillOval(x, y, width, width);
        }

        // Draw all triangle bumpers
        for (TriangularBumper tBumper : gm.getTriangularBumpers()) {
            g2.fillPolygon(tBumper.getXCoords(), tBumper.getYCoords(), 3);
        }

        // Draw all circular bumpers
        for (CircularBumper cBumper : gm.getCircularBumpers()) {
            int x = (int) (b.getX() - b.getRadius());
            int y = (int) (b.getY() - b.getRadius());
            int width = (int) (2 * b.getRadius());
            g2.fillOval(x, y, width, width);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}

    /*public void addComponents(){
        for(iGizmo g : model.getGizmos()){
            if(g.getName().toCharArray()[0]=='s'){
                addSquareBumper(g.getX(), g.getY(), Color.red);
            } else if(g.getName().toCharArray()[0]=='t'){
                addTriangularBumper(g.getX(), g.getY(), Color.blue, g.getRotation());
            } else if(g.getName().toCharArray()[0]=='f'){
                if(g.getOrientation()== FlipperOrientation.LEFT)
                    addLeftFlipper(g.getX(), g.getY(), Color.yellow, g.getRotation());
                else
                    addRightFlipper(g.getX(), g.getY(), Color.yellow, g.getRotation());
            } else if(g.getName().toCharArray()[0]=='c') {
                addCircularBumper(g.getX(), g.getY(), Color.green);
            }
        }
    }*/

    /*public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawAbsorber(g);
        drawSquareBumpers(g);
        drawCircularBumpers(g);
        drawTriangularBumpers(g);
        drawLeftFlippers(g);
        drawRightFlippers(g);
        drawGrid(g);
        drawBalls(g);
    }*/

/*
    private void drawGrid(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i <= 20; i++) {
            g.drawLine(0, i * 20, 400, i * 20);
            g.drawLine(i * 20, 0, i * 20, 400);
        }
//        g.drawLine(0,0,400,0);
//        g.drawLine(0,0,0,400);
    }


    private void drawBalls(Graphics g) {
        IBall ball = model.getBall();
        int size = (int) ball.getRadius() * 2;
        g.setColor(ball.getColor());
        g.fillOval((int) ball.getX(), (int) ball.getY(), size, size);
        g.setColor(Color.black);
        g.drawOval((int) ball.getX(), (int) ball.getY(), size, size);
    }

//    private void drawAbsorber(Graphics g) {
//        IAbsorber absorb = model.getAbsorber();
//        g.setColor(absorb.getColor());
//        g.fillRect((int) absorb.getXTopLeft(), (int) absorb.getYTopLeft(), (int) absorb.getWidth(), (int) absorb.getHeight());
//    }

    private void drawSquareBumpers(Graphics g) {
        for (Attributes sqBump : squareBumpers) {
            g.setColor(sqBump.getColor());
            g.fillRect(sqBump.getXcoordinate(), sqBump.getYcoordinate(), 20, 20);
        }
    }

    public boolean addSquareBumper(int x, int y, Color color) {
        return squareBumpers.add(new Attributes(x*20, y*20, color, 0));
    }

    private void drawCircularBumpers(Graphics g) {
        for (Attributes cBump : circularBumpers) {
            g.setColor(cBump.getColor());
            g.fillOval(cBump.getXcoordinate(), cBump.getYcoordinate(), 20, 20);
        }
    }

    public boolean addCircularBumper(int x, int y, Color color) {
        return circularBumpers.add(new Attributes(x*20, y*20, color, 0));
    }

    private void drawTriangularBumpers(Graphics g) {
        for (Triangle tBump : triangularBumpers) {
            g.setColor(tBump.getColor());
            g.fillPolygon(tBump.getXcoordinates(), tBump.getYcoordinates(), 3);
        }
    }

    public boolean addTriangularBumper(int x, int y, Color color, int rotation) {
        return triangularBumpers.add(new Triangle(x*20, y*20, color, rotation));
    }

    private void drawLeftFlippers(Graphics g) {
        for (Attributes lFlip : leftFlippers) {
            g.setColor(lFlip.getColor());
            switch (lFlip.getRotation()) {
                case 0:
                    g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate(), 10, 10);
                    g.fillRect(lFlip.getXcoordinate(), lFlip.getYcoordinate() + 5, 10, 30);
                    g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate() + 30, 10, 10);
                    break;
                case 1:
                    g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate() + 30, 10, 10);
                    g.fillRect(lFlip.getXcoordinate() + 5, lFlip.getYcoordinate() + 30, 30, 10);
                    g.fillOval(lFlip.getXcoordinate() + 30, lFlip.getYcoordinate() + 30, 10, 10);
                    break;
                case 2:
                    g.fillOval(lFlip.getXcoordinate() + 30, lFlip.getYcoordinate(), 10, 10);
                    g.fillRect(lFlip.getXcoordinate() + 30, lFlip.getYcoordinate() + 5, 10, 30);
                    g.fillOval(lFlip.getXcoordinate() + 30, lFlip.getYcoordinate() + 30, 10, 10);
                    break;
                case 3:
                    g.fillOval(lFlip.getXcoordinate(), lFlip.getYcoordinate(), 10, 10);
                    g.fillRect(lFlip.getXcoordinate() + 5, lFlip.getYcoordinate(), 30, 10);
                    g.fillOval(lFlip.getXcoordinate() + 30, lFlip.getYcoordinate(), 10, 10);
                    break;
            }

        }
    }

    public boolean addLeftFlipper(int x, int y, Color color, int rotation) {
        return leftFlippers.add(new Attributes(x*20, y*20, color, rotation));
    }

    private void drawRightFlippers(Graphics g) {
        for (Attributes rFlip : rightFlippers) {
            g.setColor(rFlip.getColor());
            switch (rFlip.getRotation()) {
                case 0:
                    g.fillOval(rFlip.getXcoordinate() + 30, rFlip.getYcoordinate(), 10, 10);
                    g.fillRect(rFlip.getXcoordinate() + 30, rFlip.getYcoordinate() + 5, 10, 30);
                    g.fillOval(rFlip.getXcoordinate() + 30, rFlip.getYcoordinate() + 30, 10, 10);
                    break;
                case 1:
                    g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate(), 10, 10);
                    g.fillRect(rFlip.getXcoordinate() + 5, rFlip.getYcoordinate(), 30, 10);
                    g.fillOval(rFlip.getXcoordinate() + 30, rFlip.getYcoordinate(), 10, 10);
                    break;
                case 2:
                    g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate(), 10, 10);
                    g.fillRect(rFlip.getXcoordinate(), rFlip.getYcoordinate() + 5, 10, 30);
                    g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate() + 30, 10, 10);
                    break;
                case 3:
                    g.fillOval(rFlip.getXcoordinate(), rFlip.getYcoordinate() + 30, 10, 10);
                    g.fillRect(rFlip.getXcoordinate() + 5, rFlip.getYcoordinate() + 30, 30, 10);
                    g.fillOval(rFlip.getXcoordinate() + 30, rFlip.getYcoordinate() + 30, 10, 10);
                    break;
            }
        }
    }

    public boolean addRightFlipper(int x, int y, Color color, int rotation) {
        return rightFlippers.add(new Attributes(x*20, y*20, color, rotation));
    }
*/

   /* private class Attributes {
        int x, y;
        Color color;
        int rotation;

        public Attributes(int x, int y, Color color, int rotation) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.rotation = rotation % 4;
        }

        public int getXcoordinate() {
            return x;
        }

        public int getYcoordinate() {
            return y;
        }

        public Color getColor() {
            return color;
        }

        public int getRotation() {
            return rotation;
        }

        public void rotate() {
            rotation = (rotation + 1) % 4;
        }
    }

    private class Triangle {
        int xCoordinate, yCoordinate, rotate;
        int[] x = new int[3];
        int[] y = new int[3];
        Color color;

        public Triangle(int x, int y, Color color, int rotate) {
            this.color = color;
            xCoordinate = x;
            yCoordinate = y;
            this.rotate = rotate % 4;
            calculate();
        }

        public int[] getXcoordinates() {
            return x;
        }

        public int[] getYcoordinates() {
            return y;
        }

        public Color getColor() {
            return color;
        }

        private void calculate() {
            switch (rotate) {
                case 0:
                    this.x[0] = xCoordinate;
                    this.y[0] = yCoordinate;
                    this.x[1] = xCoordinate + 20;
                    this.y[1] = yCoordinate;
                    this.x[2] = xCoordinate + 20;
                    this.y[2] = yCoordinate + 20;
                    break;
                case 1:
                    this.x[0] = xCoordinate;
                    this.y[0] = yCoordinate;
                    this.x[1] = xCoordinate + 20;
                    this.y[1] = yCoordinate;
                    this.x[2] = xCoordinate;
                    this.y[2] = yCoordinate + 20;
                    break;
                case 2:
                    this.x[0] = xCoordinate;
                    this.y[0] = yCoordinate;
                    this.x[1] = xCoordinate + 20;
                    this.y[1] = yCoordinate + 20;
                    this.x[2] = xCoordinate;
                    this.y[2] = yCoordinate + 20;
                    break;
                case 3:
                    this.x[0] = xCoordinate + 20;
                    this.y[0] = yCoordinate;
                    this.x[1] = xCoordinate + 20;
                    this.y[1] = yCoordinate + 20;
                    this.x[2] = xCoordinate;
                    this.y[2] = yCoordinate + 20;
                    break;
            }
        }

        public void rotate() {
            rotate = (rotate + 1) % 4;
            calculate();
        }*/
    //}
