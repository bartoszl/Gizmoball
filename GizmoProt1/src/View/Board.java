package View;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Flipper;


public class Board extends JPanel implements Observer{
	private Flipper flipper;
	
	public Board(Flipper f){
		this.flipper = f;
		f.addObserver(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawGrid(g);
		drawFlipper(g);
	}
	
	public void drawGrid(Graphics g){
		int gridSize = 6;//6x6
		for(int i = 0; i <= gridSize; i++){
			g.drawLine(0, i*20, 20*gridSize, i*20);
			g.drawLine(i*20, 0, i*20, 20*gridSize);
		}
	}
	
	private void drawFlipper(Graphics g) {
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
			
			polyY[0]=(int)(flipper.getOriginCircle().getCenter().y()*20-dy);
			polyY[1]=(int)(flipper.getEndCircle().getCenter().y()*20-dy);
			polyY[2]=(int)(flipper.getEndCircle().getCenter().y()*20+dy);
			polyY[3]=(int)(flipper.getOriginCircle().getCenter().y()*20+dy);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

}
