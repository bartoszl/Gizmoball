package model;

import view.Board;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by John Watt on 01/03/2016.
 */
public class GBallModel extends Observable implements IGBallModel {

    private double gravity, xFriction, yFriction;
    private List<Bumper> gizmos;
    private List<Flipper> flippers;
    private List<Connection> connections;
    private List<KeyConnectionAbs> keyConnectionsAbs;
    private List<KeyConnectionFlipper> keyConnectionsFlipper;
    private List<Ball> balls;
    private Absorber absorber;
    private boolean [][] occupiedSpaces;
    private int lX, lY;
    private Walls walls;

    public GBallModel() {
        gizmos = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnectionsAbs = new ArrayList<KeyConnectionAbs>();
        keyConnectionsFlipper = new ArrayList<KeyConnectionFlipper>();
        flippers = new ArrayList<Flipper>();
        balls = new ArrayList<Ball>();
        walls = new Walls(0,0,400,400);
        occupiedSpaces = new boolean [20][20];
    }

    private void notifyObs() {
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean addSquareBumper(int x, int y, int rotation, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            gizmos.add(new SquareBumper((double) x, (double) y, rotation, name));
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addFlipper(int x, int y, boolean isLeft, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(lX > 18 || lY > 18) return false;
        if(occupiedSpacesFlipper(lX, lY)) {
            occupyFlipper(lX,lY);
            Flipper f = new Flipper(x, y, isLeft, Color.RED, name);
            flippers.add(f);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addTriangularBumper(int x, int y, int rotation, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            TriangularBumper tBumper = new TriangularBumper((double) x - x%20, (double) y - y%20, rotation, name);
            gizmos.add(tBumper);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addCircularBumper(int x, int y, int rotation, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            CircularBumper cBumper = new CircularBumper((double) x, (double) y, rotation, name);
            gizmos.add(cBumper);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public Bumper getGizmo(String gizmoName) {
        for(Bumper b : gizmos) {
            if(b.getName().equals(gizmoName)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean addAbsorber(String name, int x, int y, int x1, int y1) {
        x -= x%20;
        y -= y%20;
        x1 -= (x1%20);
        y1 -= (y1%20);
        lX = x/20;
        lY = y/20;
        for(int i = x; i < x1; i++) {
            for(int j = y; j < y1; j++) {
                if(occupiedSpaces[i/20][j/20]) {
                    return false;
                }
            }
        }
        if(this.getAbsorber()!=null){
            Absorber a = this.getAbsorber();
            for(int i = (int)a.getXTopLeft()/20; i < a.getXBottomRight()/20; i++){
                for(int j = (int)a.getYTopLeft()/20; j < a.getYBottomRight()/20; j++){
                    occupiedSpaces[i][j] = false;
                }
            }
        }
        absorber = new Absorber(name, (double) x, (double) y, (double) x1, (double) y1);
        for(int i = x; i < x1; i++) {
            for(int j = y; j < y1; j++) {
                occupiedSpaces[(i/20)][(j/20)] = true;
            }
        }
        notifyObs();
        return true;
    }

    @Override
    public boolean addBall(String name, double x, double y, double xv, double yv) {
        x -= x%20;
        y -= y%20;
        lX = (int) x/20;
        lY = (int) y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            Ball b = new Ball(name, x, y, xv, yv);
            balls.add(b);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
        notifyObs();
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Override
    public void setFriction(double xFriction, double yFriction) {
        this.xFriction = xFriction;
        this.yFriction = yFriction;
        notifyObs();
    }

    @Override
    public double getFrictionX() {
        return xFriction;
    }

    @Override
    public double getFrictionY() {
        return yFriction;
    }

    @Override
    public boolean addConnection(Connection connection) {
        return connections.add(connection);
    }

    public boolean addKeyConnectionAbs(KeyConnectionAbs keyConnectionAbs) {
        return keyConnectionsAbs.add(keyConnectionAbs);
    }

    public boolean addKeyConnectionFlipper(KeyConnectionFlipper keyConnectionFlipper) {
        return keyConnectionsFlipper.add(keyConnectionFlipper);
    }

    @Override
    public List<Bumper> getGizmos() {
        return gizmos;
    }

    @Override
    public Absorber getAbsorber() {
        return absorber;
    }

    @Override
    public List<Ball> getBalls() {
        return balls;
    }

	@Override
	public List<Flipper> getFlippers() {
		// TODO Auto-generated method stub
		return flippers;
	}

    @Override
    public void setGizmos(List<Bumper> bumpers) {
        this.gizmos = bumpers;
    }

    @Override
    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public void setAbsorber(Absorber absorber) {
        this.absorber = absorber;
    }

    @Override
    public void setFlippers(List<Flipper> flippers) {
        this.flippers = flippers;
    }

    @Override
	public boolean rotateElement(double x, double y) {
		x=x-(x%20);
		y=y-(y%20);
		Bumper b = findBumper(x,y);
		Flipper f = findFlipper(x,y);
		if(b==null && f==null) return false;
		if(b!=null)
			b.rotate();
		if(f!=null)
			f.rotate();
		notifyObs();
		return true;
	}

    public boolean deleteElement(double x, double y) {
        x=x-(x%20);
        y=y-(y%20);
        Bumper b = findBumper(x,y);
        Flipper f = findFlipper(x,y);
        Ball ball = findBall(x, y);
        if(b==null && f==null && ball == null) return false;
        if(b!=null) {
            getGizmos().remove(b);
            occupiedSpaces[(int) x / 20][(int) y / 20] = false;
        }
        if(f!=null) {
            getFlippers().remove(f);
            unoccupyFlipper((int) f.getOrigin().x() / 20, (int) f.getOrigin().y() / 20);
        }
        if(ball!=null) {
            getBalls().remove(ball);
            occupiedSpaces[(int) x / 20][(int) y / 20] = false;
        }
        notifyObs();
        return true;
    }

	@Override
	public boolean moveElement(double x, double y, double newX, double newY) {
		x=x-(x%20);
		y=y-(y%20);
		newX=newX-(newX%20);
		newY=newY-(newY%20);
		Bumper b = findBumper(x,y);
		Flipper f = findFlipper(x,y);
		Ball ball = findBall(x,y);
		if(b==null && f==null && ball==null) return false;
		if(b!=null){
			if(occupiedSpaces[(int)newX/20][(int)newY/20]==true) return false;
			b.move(newX, newY);
	        occupiedSpaces[(int)x/20][(int)y/20] = false;
	        occupiedSpaces[(int)newX/20][(int)newY/20] = true;
	        notifyObs();
			return true;
		}
		if(f!=null){
			if(!occupiedSpacesFlipper((int)newX/20, (int)newY/20)) return false;
            unoccupyFlipper((int) f.getOrigin().x() / 20, (int) f.getOrigin().y() / 20);
			f.move(newX, newY);
			occupyFlipper((int)newX/20, (int)newY/20);
			notifyObs();
			return true;
		}
		if(ball!=null){
			if(occupiedSpaces[(int)newX/20][(int)newY/20]==true) return false;
			ball.move(newX, newY);
	        occupiedSpaces[(int)x/20][(int)y/20] = false;
	        occupiedSpaces[(int)newX/20][(int)newY/20] = true;
	        notifyObs();
			return true;
		}
		return false;
	}
	
	private void unoccupyFlipper(int x, int y){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
                occupiedSpaces[x+i][y+j]=false;
			}
		}
	}
	
	private void occupyFlipper(int x, int y){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
                occupiedSpaces[x+i][y+j]=true;
			}
		}
	}
	
	private boolean occupiedSpacesFlipper(int x, int y){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
                if(occupiedSpaces[x+i][y+j]==true) return false;
			}
		}
		return true;
	}
	
	private Ball findBall(double x, double y){
		for(Ball b: balls){
			if(b.getX()-10==x && b.getY()-10==y)
				return b;
		}
		return null;
	}
	
	public Bumper findBumper(double x, double y){
		for(Bumper b: gizmos){
			if(b.getX()==x && b.getY()==y)
				return b;
		}
		return null;
	}
	
	public Flipper findFlipper(double x, double y){
		for(Flipper f:flippers){
			double xx = f.getOrigin().x();
			double yy = f.getOrigin().y();
			System.out.println(xx+" "+yy);
			for(int i=0;i<2;i++){
				for(int j=0;j<2;j++){
					if(xx==x-(i*20) && yy==y-(20*j)) return f;
				}
			}
		}
		return null;
	}

    public boolean[][] getOccupiedSpaces() {
        return occupiedSpaces;
    }
    
    public void clear(){
    	gizmos = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnectionsAbs = new ArrayList<KeyConnectionAbs>();
        keyConnectionsFlipper = new ArrayList<KeyConnectionFlipper>();
        flippers = new ArrayList<Flipper>();
        balls = new ArrayList<Ball>();
        occupiedSpaces = new boolean [20][20];
        absorber = null;
        notifyObs();
    }

    public List<KeyConnectionAbs> getKeyConnectionsAbs() {
        return keyConnectionsAbs;
    }

    public List<KeyConnectionFlipper> getKeyConnectionsFlipper() {
        return keyConnectionsFlipper;
    }

    public List<Connection> getConnections() {
        return connections;
    }

	@Override
	public void moveBall() {
		double moveTime = 0.05;
		for(Ball ball: balls){
			
			if(ball!=null && !ball.isMoving()){
				CollisionDetails cd = timeUntilCollision(ball);
				double tuc = cd.getTime();
				if(tuc>moveTime){
					ball = moveBallForTime(ball, moveTime);
				} else {
					ball = moveBallForTime(ball, tuc);
					ball.setVelocity(cd.getVelocity());
				}
				notifyObs();
			}
			
		}
	}

	@Override
	public Ball moveBallForTime(Ball ball, double time) {
		double vx = ball.getVelocity().x();
		double vy = ball.getVelocity().y();
		double newX = ball.getX() + (vx*time);
		double newY = ball.getY() + (vy*time);
		ball.setXY(newX, newY);
		return ball;
	}
	
	private CollisionDetails timeUntilCollision(Ball ball) {
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelocity();
		Vect newVelocity = new Vect(0,0);
		double shortest = Double.MAX_VALUE;
		double time = 0.0;
		// Check walls
		for(LineSegment line: walls.getLines()){
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			shortest = shortest<time ? shortest : time;
			if(shortest==time)
				newVelocity = Geometry.reflectWall(line, ballVelocity);
		}
		// Check Bumpers
		for(Bumper bumper: gizmos){
			for(LineSegment line: bumper.getLines()){
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				shortest = shortest<time ? shortest : time;
				if(shortest==time)
					newVelocity = Geometry.reflectWall(line, ballVelocity);
			}
			for(Circle circle: bumper.getCircles()){
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				shortest = shortest<time ? shortest : time;
				if(shortest==time)
					newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity);
			}
		}
		// Check absorber
		for(LineSegment line: absorber.getLines()){
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			shortest = shortest<time ? shortest : time;
			if(shortest==time)
				newVelocity = Geometry.reflectWall(line, ballVelocity);
		}
		for(Circle circle: absorber.getCircles()){
			time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
			shortest = shortest<time ? shortest : time;
			if(shortest==time)
				newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity);
		}
		
		for(Flipper flipper: flippers){
			for(LineSegment line: flipper.getLines()){
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				shortest = shortest<time ? shortest : time;
				if(shortest==time)
					newVelocity = Geometry.reflectWall(line, ballVelocity);
			}
			for(Circle circle: flipper.getCircles()){
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				shortest = shortest<time ? shortest : time;
				if(shortest==time)
					newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity);
			}
		}
		
		return new CollisionDetails(time, newVelocity);
	}
}
