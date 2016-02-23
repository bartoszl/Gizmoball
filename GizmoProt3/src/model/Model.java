package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable{
    private List<iGizmo> gizmos;
	private Ball ball;
	private Walls walls;
    private Absorber abs;
    private ArrayList<LineSegment> totalLines;
    private List<Circle> circles;
    private List<SquareBumper> squareBumpers;
    private List<TriangularBumper> triangularBumpers;
    private List<CircularBumper> circularBumpers;
	
	public Model(){
		ball = new Ball(390, 390, 100, 100);
        ball.setColor(Color.BLUE);
        walls = new Walls(0, 0, 400, 400);

        totalLines = new ArrayList<>();
        circles = new ArrayList<>();
        squareBumpers = new ArrayList<>();
        triangularBumpers = new ArrayList<>();
        circularBumpers = new ArrayList<>();
        abs = new Absorber(0, 380, 400, 400);
	}

    public void moveBall() {
        double moveTime = 0.05;
        if(ball != null && !ball.stopped()) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();

            if(ball.getAbsorbed()) {
                moveBallForTime(ball, moveTime);
                if(ball.getY() < abs.getYTopLeft()) {
                    ball.setAbsorbed(false);
                }
            } else {
                if(tuc > moveTime) {
                    // No collision occurred
                    ball = moveBallForTime(ball, moveTime);
                } else {
                    // Collision in tuc
                    ball = moveBallForTime(ball, tuc);
                    if(cd.isAbsorbed()) {
                        abs.absorb(ball);
                    } else {
                        ball.setVelocity(cd.getVelo());
                    }
                }
            }
            this.setChanged();
            this.notifyObservers();
        }
    }

    private Ball moveBallForTime(Ball ball, double time) {
        double newX = 0.0;
        double newY = 0.0;
        double xVel = ball.getVelocity().x();
        double yVel = ball.getVelocity().y();
        newX = ball.getX() + (xVel * time);
        newY = ball.getY() + (yVel * time);
        ball.setXY(newX, newY);
        return ball;
    }

    private CollisionDetails timeUntilCollision() {
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

        for(SquareBumper sBumper : squareBumpers) {
            circles.addAll(sBumper.getCircles());
        }

        for(TriangularBumper tBumper : triangularBumpers) {
            circles.addAll(tBumper.getCircles());
        }

        for(Circle circle : circles) {
            time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
            if(time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectCircle(circle.getCenter(), ball.getCircle().getCenter(), ball.getVelocity(), 1.0);
            }
        }

        for(LineSegment ls : totalLines) {
            time = Geometry.timeUntilWallCollision(ls, ballCircle, ballVelocity);
            if(time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(ls, ball.getVelocity(), 1.0);
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

    public List<CircularBumper> getCircularBumpers() {
        return circularBumpers;
    }

    public List<SquareBumper> getSquareBumpers() {
        return squareBumpers;
    }

    public List<TriangularBumper> getTriangularBumpers() {
        return triangularBumpers;
    }

    public void addCircularBumper(CircularBumper cBumper) {
        circularBumpers.add(cBumper);
        circles.add(cBumper.getCircle());
    }

    public void addSquareBumper(SquareBumper sBumper) {
        squareBumpers.add(sBumper);
        totalLines.add(sBumper.getSideOne());
        totalLines.add(sBumper.getSideTwo());
        totalLines.add(sBumper.getSideThree());
        totalLines.add(sBumper.getSideFour());
    }

    public void addTriangularBumper(TriangularBumper tBumper) {
        triangularBumpers.add(tBumper);
        totalLines.add(tBumper.getSideOne());
        totalLines.add(tBumper.getSideOne());
        totalLines.add(tBumper.getHypotenuse());
    }

    public void setMoving(boolean b){ ball.setMoving(b);}
	
	public Ball getBall(){
		return ball;
	}

    public Absorber getAbsorber() {
        return abs;
    }

    public List<iGizmo> getGizmos(){ return gizmos; }

    public void setBallSpeed(int x, int y) {
        ball.setVelocity(new Vect(x,y));
    }
}