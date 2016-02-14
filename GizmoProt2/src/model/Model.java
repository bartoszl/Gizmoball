package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Model {
	private Absorber abs;
	private Ball ball;
	//private Walls gws;
	
	public Model(){
		ball = new Ball(25,25,100,100);
		//walls = new Walls(0,0,500,500);
		abs = new Absorber(0,400, 450,450);
	}
	
	public void moveBall(){
		if(ball!=null && ball.isMoving()){
			
		}
	}
	
	public Ball moveBallForTime(Ball ball, double time){
		return null;
	}
	
	private double timeUntilCollision(){
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelocity();
		
		double shortestTime = Double.MAX_VALUE;
		double time=0.00;
		for(LineSegment line: abs.getLines()){
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if(time<shortestTime)
				shortestTime=time;
		}
		
		for(Circle circ: abs.getCircles()){
			time = Geometry.timeUntilCircleCollision(circ, ballCircle, ballVelocity);
		}
		return time;
	}

}
