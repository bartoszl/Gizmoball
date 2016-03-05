package view;

import java.awt.Color;
import java.awt.Graphics;

import model.IGBallModel;

public class BuildBoard extends Board{

	public BuildBoard(IGBallModel model) {
		super(model);
	}
	
    public void paintComponent(Graphics g) {
    	if(!super.getDelete()){
    		super.superPaint(g);
    		drawGrid(g);
    		super.paintComponent(g);
    	}
    }

    private void drawGrid(Graphics g){
        int gridSize = 20;//20x20
        g.setColor(new Color(0,0,0));
        for(int i = 0; i <= gridSize; i++){
            g.drawLine(0, i*20, 20*gridSize, i*20);
            g.drawLine(i*20, 0, i*20, 20*gridSize);
        }
    }

}
