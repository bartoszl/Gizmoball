package model;

import java.util.ArrayList;
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

    // Adding this line to test git commit
    public Model(){
        ball = new Ball(100,200,75,100);
        abs = new Absorber(0,380, 400,400);
        walls = new Walls(0, 0, 400, 400);
    }

    public void moveBall(){
        double moveTime = 0.05;
        if(ball!=null && ball.isMoving()){
            CollisionDetails cd = timeUntilCollision();
            double collisionTime = cd.getTuc();
            if(ball.isAbsorbed()){
                if(ball.getY()<abs.getYTopLeft())
                    ball.setAbsorbed(false);
            } else {
                if(collisionTime>moveTime){
                    ball = moveBallForTime(ball, moveTime);
                } else {
                    ball = moveBallForTime(ball, collisionTime);
                    if(cd.isAbsorbed()) {
                        abs.absorb(ball);
                    } else {
                        ball.setVelocity(cd.getVelo());
                    }
                }
                this.setChanged();
                this.notifyObservers();
            }
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

    private CollisionDetails timeUntilCollision(){
        Circle ballCircle = ball.getCircle();
        Vect ballVelocity = ball.getVelocity();
        Vect newVelo = new Vect(0,0);

        double shortestTime = Double.MAX_VALUE;
        double time = 0.0;

        ArrayList<LineSegment> lss = walls.getLineSegments();
        for(LineSegment line : lss) {
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
            if(time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(line, ball.getVelocity(), 1.0);
            }
        }

        boolean absorbed = false;
        for(LineSegment ls : abs.getLines()) {
            time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
            if(time < shortestTime) {
                absorbed = true;
                shortestTime = time;
                newVelo = Geometry.reflectWall(ls, ball.getVelocity(), 1.0);
            }
        }

        return new CollisionDetails(shortestTime, newVelo, absorbed);
    }

    public void setMoving(boolean b){ ball.setMoving(b);}

    public Ball getBall(){
        return ball;
    }

    public Absorber getAbsorber(){ return abs; }
}