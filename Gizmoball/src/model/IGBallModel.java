package model;

import java.io.File;
import java.util.List;

/**
 * @author John Watt, Bartosz Lewandowski
 */
public interface IGBallModel    {
	
	/**
	 * Method for adding a square bumper.
	 * @param x -> int, representing x coordinate the top left corner of the grid in which the bumper is placed.
	 * @param y -> int, representing y coordinate the top left corner of the grid in which the bumper is placed.
	 * @param rotation -> int, represents how many times the bumper was rotated by 90 degrees.
	 * @param name -> String, representing name of the Bumper.
	 * @return true if the Bumper was successfully created, false if adding a bumper was unsuccessful.
	 */
    public boolean addSquareBumper(int x, int y, int rotation, String name);
    
    /**
	 * Method for adding a triangular bumper.
	 * @param x -> int, representing x coordinate the top left corner of the grid in which the bumper is placed.
	 * @param y -> int, representing y coordinate the top left corner of the grid in which the bumper is placed.
	 * @param rotation -> int, represents how many times the bumper was rotated by 90 degrees.
	 * @param name -> String, representing name of the Bumper.
	 * @return true if the Bumper was successfully created, false if adding a bumper was unsuccessful.
	 */
    public boolean addTriangularBumper(int x, int y, int rotation, String name);
    
    /**
	 * Method for adding a circular bumper.
	 * @param x -> int, representing x coordinate the top left corner of the grid in which the bumper is placed.
	 * @param y -> int, representing y coordinate the top left corner of the grid in which the bumper is placed.
	 * @param rotation -> int, represents how many times the bumper was rotated by 90 degrees.
	 * @param name -> String, representing name of the Bumper.
	 * @return true if the Bumper was successfully created, false if adding a bumper was unsuccessful.
	 */
    public boolean addCircularBumper(int x, int y, int rotation, String name);
    
    /**
	 * Method for adding a teleporter bumper.
	 * @param x -> int, representing x coordinate the top left corner of the grid in which the bumper is placed.
	 * @param y -> int, representing y coordinate the top left corner of the grid in which the bumper is placed.
	 * @param rotation -> int, represents how many times the bumper was rotated by 90 degrees.
	 * @param name -> String, representing name of the Bumper.
	 * @return true if the Bumper was successfully created, false if adding a bumper was unsuccessful.
	 */
    public boolean addTeleporterBumper(int x, int y, int rotation, String name);
    
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
     * Add a connection between a bumper and a flipper
     * @param bumper -> The bumper that will be part of the connection
     * @param flipper -> The flipper that will be part of the connection
     * @return True if the connection could be added successfully, or false if it couldn't be added
     */
    public boolean addConnection(Bumper bumper, Flipper flipper);
    
    /**
     * Add a connection between a bumper and a flipper
     * @param bumper -> The bumper that will be part of the connection
     * @param flipper -> The flipper that will be part of the connection
     * @return True if the connection could be added successfully, or false if it couldn't be added
     */
    public String addTeleporterConnection(Bumper tp1, Bumper tp2);
    
    /**
     * Add a (key) connection between a key and a flipper
     * @param keyID -> The key number of the key to be used in the connection
     * @param flipper -> The flipper to be used in the connection
     * @param upDown -> Whether the key connection is to be executed when the key is pressed or when its released
     * @return True if the key connection could be added successfully, false if not
     */
    public boolean addKeyConnectionFlipper(int keyID, IFlipper flipper, String upDown);
    
    /**
     * Add a (key) connection between a key and a flipper
     * @param keyID -> The key number of the key to be used in the connection
     * @param abs -> The absorber to be used in the connection
     * @param upDown -> Whether the key connection is to be executed when the key is pressed or when its released
     * @return True if the key connection could be added successfully, false if not
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
     * Getter method for teleporter connections.
     * 
     * @return List<TeleporterConnection>, list of all connections.
     */
    public List<TeleporterConnection> getTeleporterConnections();
    
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

    /**
<<<<<<< HEAD
     * @param cBumperName name of Bumper to be connected
     * @param flipperName name of Flipper to be connected
     * @return
=======
     * Add a connection between a bumper and a flipper
     * @param cBumperName -> The name of the bumper that will be part of the connection
     * @param flipperName -> The name of the flipper that will be part of the connection
     * @return True if the connection could be added successfully, or false if it couldn't be added
>>>>>>> master
     */
    public boolean loadConnection(String cBumperName, String flipperName);

    /**
     * @param f Flipper which will be cleared from all connections
     * @return true if any connection found and removed, false otherwise
     */
    public boolean deleteConnection(IFlipper f);

    /*
     * Setter method for sound files.
     *
     * @param f -> representing a File loaded in from a controller.
     */
    public void setSound(File f);

    /**
     * Getter method for sound files.
     *
     * @return File, that was set using the above setter.
     */
    public File getSound();

    /**
     * Play/Pause the sound loaded in.
     *
     * @param play -> boolean value deciding whether or not to play the sound.
     */
    public void playSound(boolean play);
}
