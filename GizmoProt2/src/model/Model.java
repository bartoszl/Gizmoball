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
		ball = new Ball(200,200,100,0);
		//walls = new Walls(0,0,500,500);
		abs = new Absorber(0,380, 400,400);
	}
	
	public void moveBall(){
		double moveTime = 0.05;
		if(ball!=null && ball.isMoving()){
			double collisionTime = timeUntilCollision();
			if(collisionTime>moveTime){
				ball = moveBallForTime(ball, moveTime);
			} else {
				ball = moveBallForTime(ball, collisionTime);
				abs.absorb(ball);
			}
		} 
	}
	
	public Ball moveBallForTime(Ball ball, double time){
		double xVelocity = ball.getVelocity().x();
		double yVelocity = ball.getVelocity().y();
		double newX = ball.getX() + (xVelocity*time);
		double newY = ball.getY() + (yVelocity*time);
		ball.setXY(newX, newY);
		return ball;
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
			if(time<shortestTime)
				shortestTime=time;
		}
		return time;
	}
	
	public Ball getBall(){
		return ball;
	}

}
