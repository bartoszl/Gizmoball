package model;

import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Model extends Observable{
	private Absorber abs;
	private Ball ball;
    private Walls walls;
    private LineSegment colWall;
	
	public Model(){
		ball = new Ball(100,200,75,100);
		abs = new Absorber(0,380, 400,400);
        walls = new Walls(0, 0, 400, 400);
	}
	
	public void moveBall(){
		double moveTime = 0.05;
		if(ball!=null && ball.isMoving()){
			double collisionTime = timeUntilCollision();
			if(ball.isAbsorbed()){
				if(ball.getY()<abs.getYTopLeft())
					ball.setAbsorbed(false);
			} else
			if(collisionTime>moveTime){
				ball = moveBallForTime(ball, moveTime);
			} else if(colWall == walls.getTop()){
                ball.setVelocity(Geometry.reflectWall(colWall,ball.getVelocity()));
                ball = moveBallForTime(ball, collisionTime);
                colWall = null;
            } else {
                ball = moveBallForTime(ball, collisionTime);
                abs.absorb(ball);
			}
			this.setChanged();
			this.notifyObservers();
		} 
	}

    public boolean fireFromAbs(){
        if(ball.isAbsorbed()){
            moveBallForTime(ball, 0.05);
        }
        return true;
    }

    public Vect applyFriction(Vect Vold, double time){
        double newVect = Math.sqrt((Math.pow(Vold.x(), 2)+Math.pow(Vold.y(), 2)));
        return Vold.times((1 - (0.025 * time) - ((0.025) * (newVect/20) * time)));
    }
	
	public Ball moveBallForTime(Ball ball, double time){
        Vect temp = new Vect(ball.getVelocity().x(), ball.getVelocity().y() + (500*time));
        Vect Vnew = applyFriction(temp, time);
        ball.setVelocity(Vnew);
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
			if(time<shortestTime) {
                shortestTime = time;
                colWall = abs.getLines().get(0);
            }
		}
		for(Circle circ: abs.getCircles()){
			time = Geometry.timeUntilCircleCollision(circ, ballCircle, ballVelocity);
			if(time<shortestTime)
				shortestTime=time;
		}
        for(int i = 0; i < walls.getWalls().size(); i++){
            time = Geometry.timeUntilWallCollision(walls.getWalls().get(i), ballCircle, ballVelocity);
            if(time<shortestTime) {
                shortestTime = time;
                colWall = walls.getWalls().get(i);
            }
        }
		return shortestTime;
	}

    public void setMoving(boolean b){ ball.setMoving(b);}
	
	public Ball getBall(){
		return ball;
	}
	
	public Absorber getAbsorber(){ return abs; }
}