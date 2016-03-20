package view;

import model.Bumper;
import model.IAbsorber;
import model.IBall;
import model.IFlipper;
import model.IGBallModel;
import physics.Circle;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class Board extends JPanel implements Observer {

	public enum Action {
		NONE, ADD, DELETE, MOVE, ROTATE, KEY_CONNECT, KEY_DISCONNECT, CONNECT, DISCONNECT
	}

    private boolean delete;
    private IGBallModel model;
	private Action action;

    public Board(IGBallModel model){
        this.model = model;
		action = Action.NONE;
        delete = false;
    }

    void superPaint(Graphics g){
    	super.paintComponent(g);
    }
    
    public void paintComponent(Graphics g) {
    	if(!delete){
	        drawFlippers(g);
	        drawAbsorber(g);
	        drawBumpers(g);
	        drawBalls(g);
    	}
    }

    private void drawFlippers(Graphics g) {
    	if(model.getFlippers()!=null){
	    	for(IFlipper flipper: model.getFlippers()){
				g.setColor(flipper.getColor());
				g.fillOval((int)((flipper.getOriginCircle().getCenter().x()-5)), (int)((flipper.getOriginCircle().getCenter().y()-5)), 10, 10);

				int[] polyX = new int[4];
				int[] polyY = new int[4];
				
				polyX[0] = (int)(flipper.getLines().get(0).p1().x());
				polyX[1] = (int)(flipper.getLines().get(0).p2().x());
				polyX[2] = (int)(flipper.getLines().get(1).p2().x());
				polyX[3] = (int)(flipper.getLines().get(1).p1().x());
				
				polyY[0] = (int)(flipper.getLines().get(0).p1().y());
				polyY[1] = (int)(flipper.getLines().get(0).p2().y());
				polyY[2] = (int)(flipper.getLines().get(1).p2().y());
				polyY[3] = (int)(flipper.getLines().get(1).p1().y());
					
				g.fillPolygon(polyX ,polyY, 4);
				g.fillOval((int)((flipper.getEndCircle().getCenter().x()-5)), (int)((flipper.getEndCircle().getCenter().y()-5)), 10, 10);
	    	}
    	}
	}
    
    private void drawAbsorber(Graphics g){
    	IAbsorber absorber = model.getAbsorber();
    	if(absorber!=null){
	    	g.setColor(absorber.getColor());
			g.fillRect((int)absorber.getXTopLeft(), (int)absorber.getYTopLeft(), (int)absorber.getWidth(), (int)absorber.getHeight());
    	}
    }
    
    private void drawBumpers(Graphics g){
    	if(model.getBumpers()!=null){
			for(Bumper gizmo: model.getBumpers()){
				List<Circle> circles = gizmo.getCircles();
				g.setColor(gizmo.getColor());
				if(circles.size()==1){ // Circular Bumper
					int radius = (int)circles.get(0).getRadius();
					g.fillOval((int)circles.get(0).getCenter().x()-radius, (int)circles.get(0).getCenter().y()-radius, radius*2, radius*2);
				}else if(circles.size()==3){ // Triangular Bumper
					int[] polyX = new int[3];
					int[] polyY = new int[3];
					
					for(int i=0; i<3; i++){
						polyX[i] = (int)circles.get(i).getCenter().x();
						polyY[i] = (int)circles.get(i).getCenter().y();
					}
					g.fillPolygon(polyX, polyY, 3);
				}else if(circles.size()==4){ // Square Bumper
					int x = (int)circles.get(0).getCenter().x();
					int y = (int)circles.get(0).getCenter().y();
					g.fillRect(x, y, 20, 20);
				}
			}	
    	}
	}
    
    private void drawBalls(Graphics g){
    	if(model.getBalls()!=null){
	    	for(IBall ball: model.getBalls()){
	    		g.setColor(ball.getColor());
	    		int radius = (int)ball.getRadius();
				g.fillOval((int)ball.getX()-radius, (int)ball.getY()-radius, radius*2, radius*2);
	    	}
    	}
    }
    
    public void delete(){
    	delete = true;
    	repaint();
    }
    
	boolean getDelete() {
		return delete;
	}
    
    public void update(Observable o, Object arg) {
		repaint();
    }

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}

