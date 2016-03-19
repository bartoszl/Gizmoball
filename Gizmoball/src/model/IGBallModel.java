package model;

import java.io.File;
import java.util.List;

/**
 * Created by John Watt on 01/03/2016.
 */
public interface IGBallModel    {
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param rotation
	 * @param name
	 * @return
	 */
    public boolean addSquareBumper(int x, int y, int rotation, String name);
    
    /**
     * 
     * @param x
     * @param y
     * @param rotation
     * @param name
     * @return
     */
    public boolean addTriangularBumper(int x, int y, int rotation, String name);
    
    /**
     * 
     * @param x
     * @param y
     * @param rotation
     * @param name
     * @return
     */
    public boolean addCircularBumper(int x, int y, int rotation, String name);
    
    /**
     * 
     * @param gizmoName
     * @return
     */
    public Bumper getBumper(String gizmoName);
    
    /**
     * 
     * @param name
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @return
     */
    public boolean addAbsorber(String name, int x, int y, int x1, int y1);
    
    /**
     * 
     * @param name
     * @param x
     * @param y
     * @param xv
     * @param yv
     * @return
     */
    public boolean addBall(String name, double x, double y, double xv, double yv);
    
    /**
     * 
     * @param gravity
     */
    public void setGravity(double gravity);
    
    /**
     * 
     * @return
     */
    public double getGravity();
    
    /**
     * 
     * @param xFriction
     * @param yFriction
     */
    public void setFriction(double xFriction, double yFriction);
    
    /**
     * 
     * @return
     */
    public double getFrictionX();
    
    /**
     * 
     * @return
     */
    public double getFrictionY();
    
    /**
     * 
     * @param cBumperName
     * @param flipperName
     * @return
     */
    public boolean addConnection(String cBumperName, String flipperName);
    
    /**
     * 
     * @param keyID
     * @param flipper
     * @param upDown
     * @return
     */
    public boolean addKeyConnectionFlipper(int keyID, IFlipper flipper, String upDown);
    
    /**
     * 
     * @param keyID
     * @param abs
     * @param upDown
     * @return
     */
    public boolean addKeyConnectionAbs(int keyID, IAbsorber abs, String upDown);
    
    /**
     * 
     * @return
     */
    public List<Bumper> getBumpers();
    
    /**
     * 
     * @return
     */
    public Absorber getAbsorber();
    
    /**
     * 
     * @return
     */
    public List<Ball> getBalls();
    
    /**
     * 
     * @return
     */
    public List<Flipper> getFlippers();
    
    /**
     * 
     * @param bumpers
     */
    public void setGizmos(List<Bumper> bumpers);
    
    /**
     * 
     * @param balls
     */
    public void setBalls(List<Ball> balls);
    
    /**
     * 
     * @param absorber
     */
    public void setAbsorber(Absorber absorber);
    
    /**
     * 
     * @param flippers
     */
    public void setFlippers(List<Flipper> flippers);
    
    /**
     * 
     * @param i
     * @param i1
     * @param b
     * @param flipper
     * @return
     */
    public boolean addFlipper(int i, int i1, boolean b, String flipper);
    
    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean rotateElement(double x, double y);
    
    /**
     * 
     * @param x
     * @param y
     * @param newX
     * @param newY
     * @return
     */
    public boolean moveElement(double x, double y, double newX, double newY);
    
    /**
     * 
     * @return
     */
    public boolean[][] getOccupiedSpaces();
    
    /**
     * 
     */
    public void clear();
    
    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean deleteElement(double x, double y);
    
    /**
     * 
     * @return
     */
    public List<Connection> getConnections();
    
    /**
     * 
     * @return
     */
    public List<KeyConnectionFlipper> getKeyConnectionsFlipper();
    
    /**
     * 
     * @return
     */
    public List<KeyConnectionAbs> getKeyConnectionsAbs();
    
    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public Flipper findFlipper(double x, double y);
    
    /**
     * 
     */
    public void moveBall();
    
    /**
     * 
     */
    public void reset();
    
    /**
     * 
     * @param x
     * @param y
     * @return
     */
    public Bumper findBumper(double x, double y);
    
    /**
     * 
     * @param f
     */
    public void setLoadFile(File f);
    
    /**
     * 
     * @return
     */
    public File getLoadFile();
    
    /**
     * 
     * @param objectName
     * @return
     */
    public String getObjectTypeForKeyConnection(String objectName);
    
    /**
     * 
     * @param flipperName
     * @return
     */
    public Flipper getFlipper(String flipperName);
    
    /**
     * 
     * @param set
     */
    public void setConnectedToAbs(boolean set);
}
