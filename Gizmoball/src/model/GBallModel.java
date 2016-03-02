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
    private List<KeyConnection> keyConnections;
    private List<Ball> balls;
    private Absorber absorber;
    private boolean [][] occupiedSpaces;

    public GBallModel() {
        gizmos = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnections = new ArrayList<KeyConnection>();
        balls = new ArrayList<Ball>();
        occupiedSpaces = new boolean [20][20];
    }

    private void notifyObs() {
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean addSquareBumper(int x, int y, String name) {
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            // Create a new square bumper and add it to the gizmos array list - still to be implemented, requires the SquareBumper class
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addTriangularBumper(int x, int y, String name) {
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            TriangularBumper tBumper = new TriangularBumper((double) x, (double) y, name);
            gizmos.add(tBumper);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addCircularBumper(int x, int y, String name) {
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            CircularBumper cBumper = new CircularBumper((double) x, (double) y, name);
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
        return false;
    }

    @Override
    public boolean addKeyConnection(KeyConnection keyConnection) {
        return false;
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
}