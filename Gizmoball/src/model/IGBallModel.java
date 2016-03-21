package model;

import java.io.File;
import java.util.List;

/**
 * Created by John Watt on 01/03/2016.
 */
public interface IGBallModel    {
	
	/**
	 * 
	 * @param x -> int, representing x coordinate the top left corner of the grid in which the bumper is placed.
	 * @param y -> int, representing y coordinate the top left corner of the grid in which the bumper is placed.
	 * @param rotation -> int, represents how many times the bumper was rotated by 90 degrees.
	 * @param name -> String, representing name of the Bumper.
	 * @return true if the Bumper was successfully created, false if adding a bumper was unsuccessful.
	 */
    public boolean addSquareBumper(int x, int y, int rotation, String name);
    
    /**
	 * 
	 * @param x -> int, representing x coordinate the top left corner of the grid in which the bumper is placed.
	 * @param y -> int, representing y coordinate the top left corner of the grid in which the bumper is placed.
	 * @param rotation -> int, represents how many times the bumper was rotated by 90 degrees.
	 * @param name -> String, representing name of the Bumper.
	 * @return true if the Bumper was successfully created, false if adding a bumper was unsuccessful.
	 */
    public boolean addTriangularBumper(int x, int y, int rotation, String name);
    
    /**
	 * 
	 * @param x -> int, representing x coordinate the top left corner of the grid in which the bumper is placed.
	 * @param y -> int, representing y coordinate the top left corner of the grid in which the bumper is placed.
	 * @param rotation -> int, represents how many times the bumper was rotated by 90 degrees.
	 * @param name -> String, representing name of the Bumper.
	 * @return true if the Bumper was successfully created, false if adding a bumper was unsuccessful.
	 */
    public boolean addCircularBumper(int x, int y, int rotation, String name);
    
    /**
     * Getter method for bumpers.
     * 
     * @param gizmoName -> representing an unique name of the bumper.
     * @return Bumper, with a name specified in the params.
     */
    public Bumper getBumper(String gizmoName);
    
    /**
     * 
     * @param name -> String, representing the name of the Absorber.
     * @param x -> double, representing the x coordinate of top left corner of the top
     * 			   left grid in which the absorber is placed.
     * @param y -> double, representing the y coordinate of top left corner of the top
     * 			   left grid in which the absorber is placed.
     * @param x1 -> double, representing the x coordinate of bottom right corner of the bottom right
     * 				grid in which the absorber is placed.
     * @param y1 -> double, representing the y coordinate of bottom right corner of the bottom
     * 			    right grid in which the absorber is placed. 
     * @return true if the Absorber was successfully created, false if adding a Absorber was unsuccessful.
     */
    public boolean addAbsorber(String name, int x, int y, int x1, int y1);
    
    /**
     * 
     * @param name -> String, representing the name of the Ball.
     * @param x -> double, representing the x coordinate of top left corner of the grid in which the Ball is placed.
     * @param y -> double, representing the y coordinate of top left corner of the grid in which the Ball is placed.
     * @param xv -> double, representing the ball's velocity along x axis.
     * @param yv -> double, representing the ball's velocity along y axis.
     * @return true if the Ball was successfully created, false if adding a Ball was unsuccessful.
     */
    public boolean addBall(String name, double x, double y, double xv, double yv);
    
    /**
     * 
     * @param gravity -> double, representing the new gravity.
     */
    public void setGravity(double gravity);
    
    /**
     * 
     * @return double representing gravity.
     */
    public double getGravity();
    
    /**
     * 
     * @param xFriction -> double, representing the new friction along x axis.
     * @param yFriction -> double, representing the new friction along y axis.
     */
    public void setFriction(double xFriction, double yFriction);
    
    /**
     * 
     * @return double, representing friction along x axis.
     */
    public double getFrictionX();
    
    /**
     * 
     * @return double, representing friction along y axis.
     */
    public double getFrictionY();
    
    /**
     * 
     * @param cBumperName -> String representing name of the Bumper that will be connected.
     * @param flipperName
     * @return
     */
    public boolean addConnection(CircularBumper circularBumper, Flipper flipper);
    
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
     * Getter method for Bumpers.
     * @return List<Bumper>, a list of Bumpers.
     */
    public List<Bumper> getBumpers();
    
    /**
     * Getter method for Absorber.
     * @return Absorber, if the absorber exists, null if it doesn't.
     */
    public Absorber getAbsorber();
    
    /**
     * Getter method for balls.
     * @return List<Ball>, a list of balls.
     */
    public List<Ball> getBalls();
    
    /**
     * Getter method for flippers.
     * @return List<Flipper>, a list of flippers.
     */
    public List<Flipper> getFlippers();
    
    /**
     * Setter method for Bumpers.
     * @param bumpers -> List of bumpers, that will replace the bumpers in the model.
     */
    public void setBumpers(List<Bumper> bumpers);
    
    /**
     * Setter method for Balls.
     * @param balls -> List of ball, that will replace the balls in the model.
     */
    public void setBalls(List<Ball> balls);
    
    /**
     * Setter method for absorber.
     * @param absorber -> now absorber that will replace the old one in the model.
     */
    public void setAbsorber(Absorber absorber);
    
    /**
     * Setter method for flippers.
     * @param flippers -> List of flippers, that will replace the flippers in the model.
     */
    public void setFlippers(List<Flipper> flippers);
    
    /**
     * 
     * @param x -> int, representing the x coordinate of the top left corner of the grid that the flipper is placed in.
     * @param y -> int, representing the x coordinate of the top left corner of the grid that the flipper is placed in.
     * @param left -> boolean, true if its a left flipper, false if its the right flipper.
     * @param name -> String, representing name of the flipper.
     * @return true if the flipper was successfully created, otherwise false.
     */
    public boolean addFlipper(int x, int y, boolean left, String name);
    
    /**
     * 
     * @param x -> double, x coordinate of the top left corner of the grid, in which the element that will be rotated is placed.
     * @param y -> double, y coordinate of the top left corner of the grid, in which the element that will be rotated is placed.
     * @return true if the element was successfully rotated, otherwise false.
     */
    public boolean rotateElement(double x, double y);
    
    /**
     * 
     * @param x -> double, x coordinate of the top left corner of the grid, in which the element that will be moved is placed.
     * @param y -> double, y coordinate of the top left corner of the grid, in which the element that will be moved is placed.
     * @param newX -> double, x coordinate of the top left corner of the grid, into which the element will be moved.
     * @param newY -> double, y coordinate of the top left corner of the grid, into which the element will be moved.
     * @return true, if the element was successfully moved, otherwise false.
     */
    public boolean moveElement(double x, double y, double newX, double newY);
    
    /**
     * Getter method for occupied spaces.
     * 
     * @return boolean[][], represeting the occupied and unoccupied spaces of the grid.
     */
    public boolean[][] getOccupiedSpaces();
    
    /**
     * Method that clears the model. i.e removes all Bumpers, Flippers, Balls, Connection etc.
     */
    public void clear();
    
    /**
     * 
     * @param x -> double, x coordinate of the top left corner of the grid, in which the element that will be moved is placed.
     * @param y -> double, y coordinate of the top left corner of the grid, in which the element that will be moved is placed.
     * @return true if the element was successfully deleted. Otherwise false.
     */
    public boolean deleteElement(double x, double y);
    
    /**
     * Getter method for connections.
     * 
     * @return List<Connections>, list of all connections.
     */
    public List<Connection> getConnections();
    
    /**
     * Getter method for KeyConnections for Flippers.
     * 
     * @return List<KeyConnectionsFlipper>, list of all key connections for flippers.
     */
    public List<KeyConnectionFlipper> getKeyConnectionsFlipper();
    
    /**
     * Getter method for KeyConnections for Absorber.
     * 
     * @return List<KeyConnectionsAbs>, list of all key connections for absorber.
     */
    public List<KeyConnectionAbs> getKeyConnectionsAbs();
    
    /**
     * 
     * @param x -> double, x coordinate of the top left corner of the grid in which the flipper is potentially placed.
     * @param y -> double, y coordinate of the top left corner of the grid in which the flipper is potentially placed.
     * @return Flipper, if the flipper was found. Otherwise null.
     */
    public Flipper findFlipper(double x, double y);
    
    /**
     * Method for run mode. Moves the ball by 1 Tick or by the collisionTime if a collision happens.
     * This method also moves Flippers while moving balls.
     */
    public void moveModel();
    
    /**
     * This method resets the model to its initial state. 
     * Gets balls back to their initial positions.
     * Sets flipper back to initial rotation state.
     */
    public void reset();
    
    /**
     * 
     * @param x -> double, x coordinate of the top left corner of the grid in which the bumper is potentially placed.
     * @param y -> double, y coordinate of the top left corner of the grid in which the bumper is potentially placed.
     * @return Bumper if the Bumper exists, null if it doesn't.
     */
    public Bumper findBumper(double x, double y);
    
    /**
     * Setter method for loadFile.
     * 
     * @param f -> File, representing the file from which the model will be loaded.
     */
    public void setLoadFile(File f);
    
    /**
     * Getter method for loadFile.
     * 
     * @return File, representing the file from which the model was loaded.
     */
    public File getLoadFile();
    
    /**
     * 
     * @param objectName -> name of an element which will be connected to a key.
     * @return String, "Flipper" if its a flipper, "Absorber" if its an absorber or null if its something else.
     */
    public String getObjectTypeForKeyConnection(String objectName);
    
    /**
     * 
     * @param flipperName -> String, name of the flipper.
     * @return Flipper if flipper with a given name exists, otherwise null.
     */
    public Flipper getFlipper(String flipperName);
    
    /**
     * 
     * @param set -> True if the absorber should be connected to itself, false if it shouldn't.
     */
    public void setConnectedToAbs(boolean set);

    public boolean loadConnection(String cBumperName, String flipperName);
}
