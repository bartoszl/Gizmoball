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

    private boolean delete;
    private IGBallModel model;

    public Board(IGBallModel model){
        this.model=model;
        delete = false;
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
	}
    
    private void drawAbsorber(Graphics g){
    	IAbsorber absorber = model.getAbsorber();
    	if(absorber!=null){
	    	g.setColor(absorber.getColor());
			g.fillRect((int)absorber.getXTopLeft(), (int)absorber.getYTopLeft(), (int)absorber.getWidth(), (int)absorber.getHeight());
    	}
    }
    
    private void drawBumpers(Graphics g){
    	if(model.getGizmos()!=null){
			for(Bumper gizmo: model.getGizmos()){
				List<Circle> circles = gizmo.getCircles();
				g.setColor(gizmo.getColor());
				if(circles.size()==1){//circular bumper
					int radius = (int)circles.get(0).getRadius();
					g.fillOval((int)circles.get(0).getCenter().x()-radius, (int)circles.get(0).getCenter().y()-radius, radius*2, radius*2);
				}else if(circles.size()==3){//triangular bumper
					int[] polyX = new int[3];
					int[] polyY = new int[3];
					
					for(int i=0; i<3; i++){
						polyX[i] = (int)circles.get(i).getCenter().x();
						polyY[i] = (int)circles.get(i).getCenter().x();
					}
					g.fillPolygon(polyX, polyY, 3);
				}else if(circles.size()==4){//square bumper
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
    
	public boolean getDelete() {
		return delete;
	}
    
    public void update(Observable o, Object arg) {
        repaint();
    }
}
