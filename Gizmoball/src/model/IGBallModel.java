package model;

import java.util.List;

/**
 * Created by John Watt on 01/03/2016.
 */
public interface IGBallModel    {

    public boolean addGizmo(iGizmo gizmo);

    public iGizmo getGizmo(String gizmoName);

    public boolean addAbsorber(IAbsorber absorber);

    public boolean addBall(IBall ball);

    public void setGravity(double gravity);

    public double getGravity();

    public void setFriction(double xFriction, double yFriction);

    public double getFrictionX();

    public double getFrictionY();

    public boolean addConnection(Connection connection);

    public boolean addKeyConnection(KeyConnection keyConnection);

    public List<iGizmo> getGizmos();

    public List<IAbsorber> getAbsorbers();

    public List<IBall> getBalls();


}
