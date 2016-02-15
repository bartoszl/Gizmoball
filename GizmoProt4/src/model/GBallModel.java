package model;

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
        if(safeToAddGizmo(gizmo)) {
            gizmos.add(gizmo);
            return true;
        }
        return false;
    }

    public boolean addAbsorber(iAbsorber absorber) {
        for(iAbsorber abs : absorbers) {
            if(abs.getLeftXCoordinate() == absorber.getLeftXCoordinate()) {
                return false;
            } else if(abs.getRightXCoordinate() == absorber.getRightXCoordinate()) {
                return false;
            } else if(abs.getTopYCoordinate() == absorber.getTopYCoordinate()) {
                return false;
            } else if(abs.getBottomYCoordinate() == absorber.getBottomYCoordinate()) {
                return false;
            }
        }
        absorbers.add(absorber);
        return true;
    }

    public boolean addBall(iBall ball) {
        for(iBall b : balls) {
            if(b.getLeftLimit() == ball.getLeftLimit() && b.getRightLimit() == ball.getRightLimit()) {
                return false;
            } else if(b.getUpperLimit() == ball.getUpperLimit() && b.getLowerLimit() == ball.getLowerLimit()) {
                return false;
            }
        }
        balls.add(ball);
        return true;
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
            if (gizmo.getLeftLimit() == g.getLeftLimit() && gizmo.getRightLimit() == g.getRightLimit() &&
                    gizmo.getUpperLimit() == g.getUpperLimit() && gizmo.getLowerLimit() == gizmo.getLowerLimit()) {
                return false;
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
