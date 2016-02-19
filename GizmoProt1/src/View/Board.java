package View;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class Board extends JPanel implements Observer{
	
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
	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

}
