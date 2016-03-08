package model;

import java.util.List;

/**
 * Created by John Watt on 01/03/2016.
 */
public interface IGBallModel    {

    public boolean addSquareBumper(int x, int y, int rotation, String name);
    public boolean addTriangularBumper(int x, int y, int rotation, String name);
    public boolean addCircularBumper(int x, int y, int rotation, String name);
    public Bumper getGizmo(String gizmoName);
    public boolean addAbsorber(String name, int x, int y, int x1, int y1);
    public boolean addBall(String name, double x, double y, double xv, double yv);
    public void setGravity(double gravity);
    public double getGravity();
    public void setFriction(double xFriction, double yFriction);
    public double getFrictionX();
    public double getFrictionY();
    public boolean addConnection(Connection connection);
    public boolean addKeyConnectionFlipper(KeyConnectionFlipper keyConnection);
    public boolean addKeyConnectionAbs(KeyConnectionAbs keyConnection);
    public List<Bumper> getGizmos();
    public Absorber getAbsorber();
    public List<Ball> getBalls();
    public List<Flipper> getFlippers();
    public void setGizmos(List<Bumper> bumpers);
    public void setBalls(List<Ball> balls);
    public void setAbsorber(Absorber absorber);
    public void setFlippers(List<Flipper> flippers);
    public boolean addFlipper(int i, int i1, boolean b, String flipper);
    
    public boolean rotateElement(double x, double y);
    public boolean moveElement(double x, double y, double newX, double newY);
    public boolean[][] getOccupiedSpaces();
    
    public void clear();

}
