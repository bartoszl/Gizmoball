package model;

import physics.Vect;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GBallModel extends Observable implements iGBallModel {

    private List<iGizmo> gizmos;
    private double gravity, xFriction, yFriction;
    private List<Connection> connections;
    private List<KeyConnection> keyConnections;
    private List<iAbsorber> absorbers;
    private List<iBall> balls;

    public GBallModel() {
        gizmos = new ArrayList<>();
        connections = new ArrayList<>();
        keyConnections = new ArrayList<>();
        absorbers = new ArrayList<>();
        balls = new ArrayList<>();
    }

    public boolean addGizmo(iGizmo gizmo) {
        return false;
    }

    public boolean addAbsorber(iAbsorber absorber) { return false;}

    public boolean addBall(iBall ball) {
        return false;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setFriction(double xFriction, double yFriction) {
        this.xFriction = xFriction;
        this.yFriction = yFriction;
    }

    public boolean addConnection(Connection connection) {
        for(Connection conn : connections) {
            if(conn.getConnectingFrom().equals(connection.getConnectingFrom()) &&
                    conn.getConnectingTo().equals(connection.getConnectingTo())) {
                return false;
            }
        }
        connections.add(connection);
        return true;
    }

    public boolean addKeyConnection(KeyConnection keyConnection) {
        for(KeyConnection keyConn : keyConnections) {
            if(keyConn.getKeyID() == keyConnection.getKeyID() &&
                    keyConn.getConnectingTo().equals(keyConnection.getConnectingTo())) {
                return false;
            }
        }
        keyConnections.add(keyConnection);
        return true;
    }

    public List<iGizmo> getGizmos() {
        return gizmos;
    }

    private boolean safeToAddGizmo(iGizmo gizmo) {
        List<String> gizmoNames = new ArrayList<>();
        for(iGizmo g : gizmos) {
            gizmoNames.add(g.getName());
        }
        if(gizmoNames.contains(gizmo.getName())) {
            return false;
        }
        for(iGizmo g : gizmos) {
            if(g instanceof CircularBumper) {
                double centreX = ((CircularBumper) g).getCentreX();
                double centreY = ((CircularBumper) g).getCentreY();
                double radius = ((CircularBumper) g).getRadius();
                double leftLimit = centreX - radius;
                double rightLimit = centreX + radius;
                double upperLimit = centreY - radius;
                double lowerLimit = centreY + radius;
            }
        }
        return true;
    }

    public double getGravity() {
        return gravity;
    }

    public List<iAbsorber> getAbsorbers() {
        return absorbers;
    }

    public List<iBall> getBalls() {
        return balls;
    }
}
