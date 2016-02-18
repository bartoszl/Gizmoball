package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.IAbsorber;
import model.IBall;
import model.Model;

class Board extends JPanel implements Observer{
	private Model model;
	
	public Board(Model m){
		this.model = m;
		m.addObserver(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawGrid(g);
		drawAbsorber(g);
		drawBalls(g);
	}
	
	private void drawGrid(Graphics g){
//		for(int i = 0; i <= 20; i++){
//			g.drawLine(0, i*20, 400, i*20);
//			g.drawLine(i*20, 0, i*20, 400);
//		}
        g.drawLine(0,0,400,0);
        g.drawLine(0,0,0,400);
	}

	
	private void drawBalls(Graphics g) {
		IBall ball = model.getBall();
		int size = (int)ball.getRadius()*2;
		g.setColor(ball.getColor());
		g.fillOval((int)ball.getX(), (int)ball.getY(), size, size);
        g.setColor(Color.black);
		g.drawOval((int)ball.getX(), (int)ball.getY(), size, size);
	}
	
	private void drawAbsorber(Graphics g) {
		IAbsorber absorb= model.getAbsorber();
		g.setColor(absorb.getColor());
		g.fillRect((int)absorb.getXTopLeft(), (int)absorb.getYTopLeft(), (int)absorb.getWidth(), (int)absorb.getHeight());
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
}