package view;

import model.Absorber;
import model.Ball;
import model.CircularBumper;
import model.IAbsorber;
import model.IFlipper;
import model.iAbsorber;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

class Board extends JPanel implements Observer {

    private boolean isBuild;
    

    public Board(boolean isBuild){
        this.isBuild = isBuild;
    }

//    private int width, height;
//    //private Model gm;
//
//    public Board(Model m) {
//        width = 400;
//        height = 400;
//        m.addObserver(this);
//        gm = m;
//        this.setBorder(BorderFactory.createLineBorder(Color.black));
//    }
//
//    public Dimension getPreferredSize() {
//        return new Dimension(width, height);
//    }
//
    public void paintComponent(Graphics g) {
        if(isBuild) drawGrid(g);
        else drawBorder(g);

//        super.paintComponent(g);
//
//        Graphics2D g2 = (Graphics2D) g;
//
//        // Draw all square bumpers
//        for (SquareBumper sBumper : gm.getSquareBumpers()) {
//            g2.setColor(sBumper.getColor());
//            g2.fillRect((int) sBumper.getX(), (int) sBumper.getY(), 20, 20);
//        }
//
//        Ball b = gm.getBall();
//
//        // Draw the ball
//        if (b != null) {
//            g2.setColor(b.getColor());
//            int x = (int) (b.getX() - b.getRadius());
//            int y = (int) (b.getY() - b.getRadius());
//            int width = (int) (2 * b.getRadius());
//            g2.fillOval(x, y, width, width);
//        }
//
//        // Draw all triangle bumpers
//        for (TriangularBumper tBumper : gm.getTriangularBumpers()) {
//            g2.setColor(tBumper.getColor());
//            g2.fillPolygon(tBumper.getXCoords(), tBumper.getYCoords(), 3);
//        }
//
//        //Draw all circular bumpers
//        for (CircularBumper cBumper : gm.getCircularBumpers()) {
//            g2.setColor(cBumper.getColor());
//            int x = (int) (cBumper.getX() - cBumper.getRadius());
//            int y = (int) (cBumper.getY() - cBumper.getRadius());
//            int width = (int) (2 * cBumper.getRadius());
//            g2.fillOval(x, y, width, width);
//        }
//
//        // Draw the absorber
//        Absorber absorber = gm.getAbsorber();
//        g.setColor(absorber.getColor());
//        g.fillRect((int) absorber.getXTopLeft(), (int) absorber.getYTopLeft(), (int) absorber.getWidth(), (int) absorber.getHeight());
    }

    private void drawGrid(Graphics g){
        int gridSize = 20;//20x20
        for(int i = 0; i <= gridSize; i++){
            g.drawLine(0, i*20, 20*gridSize, i*20);
            g.drawLine(i*20, 0, i*20, 20*gridSize);
        }
    }

    private void drawBorder(Graphics g){
        g.drawLine(0, 0, 400, 0);
        g.drawLine(0, 0, 0, 400);
        g.drawLine(0, 400, 400, 400);
        g.drawLine(400, 0, 400, 400);
    }

    private void drawFlippers(Graphics g) {
    	
    	for(IFlipper flipper: model.getFlippers()){
			//only needs the center of the top and bottom circles
			g.setColor(flipper.getColor());
			g.fillOval((int)((flipper.getOriginCircle().getCenter().x()-0.25)*20), (int)((flipper.getOriginCircle().getCenter().y()-0.25)*20), 10, 10);
			//evil math magic to get the polygon values
			int[] polyX = new int[4];
			int[] polyY = new int[4];
			if(flipper.getEndCircle().getCenter().y()-flipper.getOriginCircle().getCenter().y()!=0){
				double alpha = Math.atan((flipper.getOriginCircle().getCenter().x()-flipper.getEndCircle().getCenter().x())/(flipper.getEndCircle().getCenter().y()-flipper.getOriginCircle().getCenter().y()));
				double dx = 5*Math.cos(alpha);
				double dy = 5*Math.sin(alpha);
				
				polyX[0]=(int)(flipper.getOriginCircle().getCenter().x()*20+dx);
				polyX[1]=(int)(flipper.getEndCircle().getCenter().x()*20+dx);
				polyX[2]=(int)(flipper.getEndCircle().getCenter().x()*20-dx);
				polyX[3]=(int)(flipper.getOriginCircle().getCenter().x()*20-dx);
				
				polyY[0]=(int)(flipper.getOriginCircle().getCenter().y()*20+dy);
				polyY[1]=(int)(flipper.getEndCircle().getCenter().y()*20+dy);
				polyY[2]=(int)(flipper.getEndCircle().getCenter().y()*20-dy);
				polyY[3]=(int)(flipper.getOriginCircle().getCenter().y()*20-dy);
			}
			else{//if the flipper is horizontal, avoid division by 0
				polyX[0]=(int)(flipper.getOriginCircle().getCenter().x()*20);
				polyX[1]=(int)(flipper.getEndCircle().getCenter().x()*20);
				polyX[2]=(int)(flipper.getEndCircle().getCenter().x()*20);
				polyX[3]=(int)(flipper.getOriginCircle().getCenter().x()*20);
				
				polyY[0]=(int)(flipper.getOriginCircle().getCenter().y()*20-5);
				polyY[1]=(int)(flipper.getEndCircle().getCenter().y()*20-5);
				polyY[2]=(int)(flipper.getEndCircle().getCenter().y()*20+5);
				polyY[3]=(int)(flipper.getOriginCircle().getCenter().y()*20+5);
			}
			g.fillPolygon(polyX ,polyY, 4);
			g.fillOval((int)((flipper.getEndCircle().getCenter().x()-0.25)*20), (int)((flipper.getEndCircle().getCenter().y()-0.25)*20), 10, 10);
    	}
	}
    
    public void drawAbsorbers(Graphics g){
    	for(IAbsorber absorber : model.getAbsorbers()) {
    		g.setColor(absorber.getColor());
			g.fillRect((int)absorber.getXTopLeft(), (int)absorber.getYTopLeft(), (int)absorber.getWidth(), (int)absorber.getHeight());
        }
    }
    
    public void update(Observable o, Object arg) {
        repaint();
    }
}
