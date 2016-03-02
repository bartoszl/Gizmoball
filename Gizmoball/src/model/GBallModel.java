package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John Watt on 01/03/2016.
 */
public class GBallModel implements IGBallModel {

    private double gravity, xFriction, yFriction;
    private List<Bumper> gizmos;
    private List<Flipper> flippers;
    private List<Connection> connections;
    private List<KeyConnection> keyConnections;
    private List<Ball> balls;
    private Absorber absorber;
    private boolean [][] occupiedSpaces;

    public GBallModel() {
        gizmos = new ArrayList<>();
        connections = new ArrayList<>();
        keyConnections = new ArrayList<>();
        balls = new ArrayList<>();
        occupiedSpaces = new boolean [20][20];
    }

    @Override
    public boolean addSquareBumper(int x, int y, String name) {
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            // Create a new square bumper and add it to the gizmos array list
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
    public boolean addAbsorber(Absorber absorber) {
        return false;
    }

    @Override
    public boolean addBall(Ball ball) {
        return false;
    }

    @Override
    public void setGravity(double gravity) {

    }

    @Override
    public double getGravity() {
        return 0;
    }

    @Override
    public void setFriction(double xFriction, double yFriction) {

    }

    @Override
    public double getFrictionX() {
        return 0;
    }

    @Override
    public double getFrictionY() {
        return 0;
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
        return null;
    }

    @Override
    public Absorber getAbsorber() {
        return absorber;
    }

    @Override
    public List<Ball> getBalls() {
        return null;
    }
}
