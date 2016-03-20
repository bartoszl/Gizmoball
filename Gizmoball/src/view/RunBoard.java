package view;

import java.awt.Color;
import java.awt.Graphics;

import model.IGBallModel;

class RunBoard extends Board{

	RunBoard(IGBallModel model) {
		super(model);
	}
	
    public void paintComponent(Graphics g) {
    	if(!super.getDelete()){
    		super.superPaint(g);
    		drawBorder(g);
    		super.paintComponent(g);
    	}
    }
    
    private void drawBorder(Graphics g){
    	g.setColor(new Color(0,0,0));
        g.drawLine(0, 0, 400, 0);
        g.drawLine(0, 0, 0, 400);
        g.drawLine(0, 400, 400, 400);
        g.drawLine(400, 0, 400, 400);
    }
}
