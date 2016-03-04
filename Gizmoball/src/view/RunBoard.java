package view;

import java.awt.Graphics;

import model.IGBallModel;

public class RunBoard extends Board{

	public RunBoard(IGBallModel model) {
		super(model);
	}
	
    public void paintComponent(Graphics g) {
    	if(!super.getDelete()){
    		drawBorder(g);
    		super.paintComponent(g);
    	}
    }
    
    private void drawBorder(Graphics g){
        g.drawLine(0, 0, 400, 0);
        g.drawLine(0, 0, 0, 400);
        g.drawLine(0, 400, 400, 400);
        g.drawLine(400, 0, 400, 400);
    }

}