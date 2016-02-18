package model;

import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Model extends Observable{
	private Absorber abs;
	private Ball ball;
	//private Walls gws;
	
	public Model(){
		ball = new Ball(200,300,0,100);
		//walls = new Walls(0,0,500,500);
		abs = new Absorber(0,380, 400,400);
	}
	
	public void moveBall(){
		double moveTime = 0.05;
		if(ball!=null && ball.isMoving()){
			double collisionTime = timeUntilCollision();
			if(ball.isAbsorbed()){
				moveBallForTime(ball, moveTime);
				if(ball.getY()<abs.getYTopLeft())
					ball.setAbsorbed(false);
			} else
			if(collisionTime>moveTime){
				ball = moveBallForTime(ball, moveTime);
			} else {
				ball = moveBallForTime(ball, collisionTime);
				abs.absorb(ball);
			}
			this.setChanged();
			this.notifyObservers();
		} 
	}

    public void fireFromAbs(){
        if(ball.isAbsorbed()){
            moveBallForTime(ball, 0.05);
        }
    }

    public double calcVelocity(double Vold, double time){
        return  Vold * (1 - 0.25 * time - 0.0025 * Math.abs(Vold) * time);
    }
	
	public Ball moveBallForTime(Ball ball, double time){
        Vect velocity = new Vect(calcVelocity(ball.getVelocity().x(),time), calcVelocity((ball.getVelocity().y() + 25*(time*10)),time));
        ball.setVelocity(velocity);
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
		double time;
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
		return shortestTime;
	}

    public void setMoving(boolean b){ ball.setMoving(b);}
	
	public Ball getBall(){
		return ball;
	}
	
	public Absorber getAbsorber(){
		return abs;
	}
}
