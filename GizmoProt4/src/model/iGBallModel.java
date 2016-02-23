package model;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

public interface iGBallModel {

    public boolean addGizmo(iGizmo gizmo);

    public List<iGizmo> getGizmos();

    public boolean addAbsorber(iAbsorber absorber);

    public boolean addBall(iBall ball);

    public void setGravity(double gravity);

    public void setFriction(double xFriction, double yFriction);

    public boolean addConnection(Connection connection);

    public boolean addKeyConnection(KeyConnection keyConnection);

    public double getGravity();

    public List<iAbsorber> getAbsorbers();

    public List<iBall> getBalls();

}
