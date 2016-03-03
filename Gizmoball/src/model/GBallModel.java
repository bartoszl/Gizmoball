package model;

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
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            gizmos.add(new SquareBumper((double) x, (double) y, rotation, name));
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addTriangularBumper(int x, int y, int rotation, String name) {
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            TriangularBumper tBumper = new TriangularBumper((double) x, (double) y, rotation, name);
            gizmos.add(tBumper);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addCircularBumper(int x, int y, int rotation, String name) {
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
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
        for(int i = x; i <= x1; i++) {
            for(int j = y; j <= y1; j++) {
                if(occupiedSpaces[i][j]) {
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
        if(!occupiedSpaces[(int) x][(int) y]) {
            occupiedSpaces[(int) x][(int) y] = true;
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
	
	//TODO move functionality needs to be added
}
