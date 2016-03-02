package model;

import java.util.List;

/**
 * Created by John Watt on 01/03/2016.
 */
public interface IGBallModel    {

    public boolean addSquareBumper(int x, int y, String name);

    public boolean addTriangularBumper(int x, int y, String name);

    public boolean addCircularBumper(int x, int y, String name);

    public Bumper getGizmo(String gizmoName);

    public boolean addAbsorber(Absorber absorber);

    public boolean addBall(String name, );

    public void setGravity(double gravity);

    public double getGravity();

    public void setFriction(double xFriction, double yFriction);

    public double getFrictionX();

    public double getFrictionY();

    public boolean addConnection(Connection connection);

    public boolean addKeyConnection(KeyConnection keyConnection);

    public List<Bumper> getGizmos();

    public Absorber getAbsorber();

    public List<Ball> getBalls();


}
