package model;

import view.Board;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

    public GBallModel() {
        gizmos = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnectionsAbs = new ArrayList<KeyConnectionAbs>();
        keyConnectionsFlipper = new ArrayList<KeyConnectionFlipper>();
        flippers = new ArrayList<Flipper>();
        balls = new ArrayList<Ball>();
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
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
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
        lX = x/20;
        lY = y/20;
        for(int i = x; i <= x1; i++) {
            for(int j = y; j <= y1; j++) {
                if(occupiedSpaces[lX][lY]) {
                    return false;
                }
            }
        }
        absorber = new Absorber(name, (double) x, (double) y, (double) x1, (double) y1);
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
	public boolean rotateElement(double x, double y) {
		x=x-(x%20);
		y=y-(y%20);
		Bumper b = findBumper(x,y);
		if(b==null) return false;
		b.rotate();
		return true;
	}

	@Override
	public boolean moveElement(double x, double y, double newX, double newY) {
		x=x-(x%20);
		y=y-(y%20);
		newX=newX-(newX%20);
		newY=newY-(newY%20);
		Bumper b = findBumper(x,y);
		if(b==null) return false;
		if(occupiedSpaces[(int)newX/20][(int)newY/20]==true) return false;
		b.move(newX, newY);
        notifyObs();
		return true;
	}
	
	private Bumper findBumper(double x, double y){
		for(Bumper b: gizmos){
			if(b.getX()==x && b.getY()==y)
				return b;
		}
		return null;
	}
	
	private Flipper findFlipper(double x, double y){
		for(Flipper f:flippers){
			double xx = f.getOrigin().x();
			double yy = f.getOrigin().y();
			if(xx==x && yy==y) return f;
		}
		return null;
	}

    public boolean[][] getOccupiedSpaces() {
        return occupiedSpaces;
    }
}
