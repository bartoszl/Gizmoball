package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

<<<<<<< HEAD
=======
import java.awt.*;
>>>>>>> master
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable{
<<<<<<< HEAD
	//private Absorber abs;
    private List<iGizmo> gizmos;
	private Ball ball;
	private Walls walls;
    private LineSegment colWall;
	
	public Model(){
        gizmos = initGizmos();
		ball = new Ball(200,300,100,100);
		walls = new Walls();
		//abs = new Absorber(0,380, 400,400);
	}
	
	public void moveBall(){
		double moveTime = 0.05;
		if(ball!=null && ball.isMoving()){
			double collisionTime = timeUntilCollision();
			if(ball.isAbsorbed()){
				//moveBallForTime(ball, moveTime);
				//if(ball.getY()<abs.getYTopLeft())
					ball.setAbsorbed(false);
			} else
			if(collisionTime>moveTime){
				ball = moveBallForTime(ball, moveTime);
			} else {
                ball.setVelocity(Geometry.reflectWall(colWall,ball.getVelocity()));
				ball = moveBallForTime(ball, collisionTime);
			}
			this.setChanged();
			this.notifyObservers();
		} 
	}

    public double calcVelocity(double Vold, double time){
        System.out.println("Vnew %: " + (1 - (0.002 * time) - (0.002 * Math.abs(Vold) * time)));
        System.out.println("Vold: " + Vold);
        return  Vold * (1 - (0.2 * time) - (0.002 * Math.abs(Vold) * time));
    }
	
	public Ball moveBallForTime(Ball ball, double time){
        //Vect velocity = new Vect(calcVelocity(ball.getVelocity().x(),time), calcVelocity((ball.getVelocity().y() + 200*(time)),time));
        //ball.setVelocity(velocity);
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
        for(int i = 0; i < walls.getWalls().size(); i++){
            time = Geometry.timeUntilWallCollision(walls.getWalls().get(i), ballCircle, ballVelocity);
            if(time<shortestTime) {
                shortestTime = time;
                colWall = walls.getWalls().get(i);
            }
        }
        for(iGizmo g : this.getGizmos()){
            if(g.getName().toCharArray()[0]=='s'){
                //time = Geometry.timeUntilWallCollision(g.getSide, ballCircle, ballVelocity);
            }
        }
		return shortestTime;
	}
=======
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
        gizmos = initGizmos();
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
>>>>>>> master

    public void setMoving(boolean b){ ball.setMoving(b);}
	
	public Ball getBall(){
		return ball;
	}

<<<<<<< HEAD
    public List<iGizmo> getGizmos(){ return gizmos; }

=======
    public Absorber getAbsorber() {
        return abs;
    }

    public List<iGizmo> getGizmos(){ return gizmos; }

    public void setBallSpeed(int x, int y) {
        ball.setVelocity(new Vect(x,y));
    }

>>>>>>> master
    private List<iGizmo> initGizmos() {
        List<iGizmo> gizmos = new ArrayList<>();
        gizmos.add(new SquareBumper(18,2,"s0"));
        gizmos.add(new SquareBumper(17,2,"s1"));
        gizmos.add(new SquareBumper(16,2,"s2"));
        gizmos.add(new SquareBumper(15,2,"s3"));
        gizmos.add(new SquareBumper(14,2,"s4"));
        gizmos.add(new SquareBumper(13,2,"s5"));
        gizmos.add(new SquareBumper(8,2,"s6"));
        gizmos.add(new SquareBumper(7,2,"s7"));
        gizmos.add(new SquareBumper(6,2,"s8"));
        gizmos.add(new SquareBumper(5,2,"s9"));
        gizmos.add(new SquareBumper(4,2,"s10"));
        gizmos.add(new SquareBumper(3,2,"s11"));
        gizmos.add(new SquareBumper(2,2,"s12"));
        gizmos.add(new SquareBumper(1,2,"s13"));
        gizmos.add(new SquareBumper(0,2,"s14"));
<<<<<<< HEAD
        gizmos.add(new TriangularBumper(19,0,4,"t0"));
        gizmos.add(new TriangularBumper(1,1,1,"t1"));
=======
        //gizmos.add(new TriangularBumper(19,0,4,"t0"));
        //gizmos.add(new TriangularBumper(1,1,1,"t1"));
>>>>>>> master
        gizmos.add(new Flipper(9,2,FlipperOrientation.LEFT,"f0"));
        gizmos.add(new Flipper(8,7,FlipperOrientation.LEFT,"f1"));
        gizmos.add(new Flipper(11,2,FlipperOrientation.RIGHT,"f2"));
        gizmos.add(new Flipper(13,7,FlipperOrientation.RIGHT,"f3"));
        gizmos.add(new CircularBumper(18,3,10,"c0"));
        gizmos.add(new CircularBumper(17,4,10,"c1"));
        gizmos.add(new CircularBumper(16,5,10,"c2"));
        gizmos.add(new CircularBumper(15,6,10,"c3"));
        gizmos.add(new CircularBumper(13,9,10,"c4"));
        gizmos.add(new CircularBumper(12,9,10,"c5"));
        gizmos.add(new CircularBumper(11,10,10,"c6"));
        gizmos.add(new CircularBumper(10,9,10,"c7"));
        gizmos.add(new CircularBumper(9,9,10,"c8"));
        gizmos.add(new CircularBumper(7,6,10,"c9"));
        gizmos.add(new CircularBumper(6,5,10,"c10"));
        gizmos.add(new CircularBumper(5,4,10,"c11"));
        gizmos.add(new CircularBumper(4,3,10,"c12"));
        return gizmos;
    }
}
