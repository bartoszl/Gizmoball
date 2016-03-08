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
        if(occupiedSpacesFlipper(lX, lY, isLeft)) {
            occupyFlipper(lX,lY,isLeft);
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
        x1 -= (x1%20) - 20;
        y1 -= (y1%20) - 20;
        lX = x/20;
        lY = y/20;
        for(int i = x; i <= x1; i++) {
            for(int j = y; j <= y1; j++) {
                if(occupiedSpaces[lX][lY]) {
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
		if(b==null) return false;
		b.rotate();
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
		if(b==null && f==null) return false;
		if(b!=null){
			System.out.println("bumper");
			if(occupiedSpaces[(int)newX/20][(int)newY/20]==true) return false;
			b.move(newX, newY);
	        occupiedSpaces[(int)x/20][(int)y/20] = false;
	        occupiedSpaces[(int)newX/20][(int)newY/20] = true;
	        notifyObs();
			return true;
		}
		if(f!=null){
			System.out.println("flipper");
			if(!occupiedSpacesFlipper((int)newX/20, (int)newY/20, f.isLeft())) return false;
			f.move(newX, newY);
			System.out.println(newX+ " "+newY);
			occupyFlipper((int)newX/20, (int)newY/20, f.isLeft());
			unoccupyFlipper((int)x/20, (int)y/20, f.isLeft());
			notifyObs();
			return true;
		}
		return false;
	}
	
	private void unoccupyFlipper(int x, int y, boolean left){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
					occupiedSpaces[x+i][y+j]=false;
			}
		}
	}
	
	private void occupyFlipper(int x, int y, boolean left){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
					occupiedSpaces[x+i][y+j]=true;
					System.out.println((x+i)+" "+(y+i));
			}
		}
	}
	
	private boolean occupiedSpacesFlipper(int x, int y, boolean left){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
					if(occupiedSpaces[x+i][y+j]==true) return false;
			}
		}
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
}
