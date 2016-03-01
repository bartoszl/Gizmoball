package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John Watt on 01/03/2016.
 */
public class GBallModel implements IGBallModel {

    private double gravity, xFriction, yFriction;
    private List<iGizmo> gizmos;
    private List<IFlipper> flippers;
    private List<Connection> connections;
    private List<KeyConnection> keyConnections;
    private List<IBall> balls;
    private List<IAbsorber> absorbers;

    public GBallModel() {
        gizmos = new ArrayList<>();
        connections = new ArrayList<>();
        keyConnections = new ArrayList<>();
        absorbers = new ArrayList<>();
        balls = new ArrayList<>();
    }

    @Override
    public boolean addGizmo(iGizmo gizmo) {
        return false;
    }

    @Override
    public iGizmo getGizmo(String gizmoName) {
        return null;
    }

    @Override
    public boolean addAbsorber(IAbsorber absorber) {
        return false;
    }

    @Override
    public boolean addBall(IBall ball) {
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
    public List<iGizmo> getGizmos() {
        return null;
    }

    @Override
    public List<IAbsorber> getAbsorbers() {
        return null;
    }

    @Override
    public List<IBall> getBalls() {
        return null;
    }
}
