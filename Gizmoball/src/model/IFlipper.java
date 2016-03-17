package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.Color;
import java.util.List;

/**
 * 
 * @author Bartosz Lewandowski, Kiril Rupasov
 *
 *	IFlipper interface for Flipper class.
 */
public interface IFlipper {

    enum Movement {
        FORWARDS, BACKWARDS, NONE
    }
    
    /**
     * This method changes coordinates of flipper
     * @param cx new coordinate of left upper cell which is used for flipper
     * @param cy new coordinate of left upper cell which is used for flipper
     */
    public void move(double cx, double cy);
    
    /**
     * Method reponsible for flipping the flipper. It rotates it around
     * the topCircle. Maximum rotation is 90 degrees.
     * 
     * @param time -> double, representing the moving time for the flipper. 
     * 				  This value is taken from the ballMoving method. It's
     * 				  either the moveTime or if collision happens then it's
     * 				  time until collision.
     */
    public void rotatePerTime(double time);
    
    /**
     * Getter method for angular speed of the flipper.
     * 
     * @return double, representing the angular speed of the flipper in radians.
     */
    public double getAngSpeed();

    /**
     * Method that should be called once button connected to flipper is pressed.
     * Makes the flipper move forward.
     */
    public void press();

    /**
     * Method that should be called once button connected to flipper is released.
     * Makes the flipper go to the initial position.
     */
    public void release();
    
    /**
     * Getter method for rotation.
     * 
     * @return int, representing how many times the flipper was rotated by 90 degrees.
     * The value ranges from 0 to 3 as for rotations means 360 degrees and that is the
     * same position as 0.
     */
    public int getRotation();
    
    /**
     * Rotates the flipper by 90 degrees clockwise.
     */
    public void rotate();

    
    /**
     * Getter method for name.
     * 
     * @return String, representing the name of the flipper.
     */
    public String getName();
    
    /**
     * Setter method for name.
     * 
     * @param name -> String, representing the new name of the flipper.
     */
    public void setName(String name);

    /**
     * Getter method for color.
     * 
     * @return Color, representing Color of the flipper.
     */

    public Color getColor();

    /**
     * Setter method for color.
     * 
     * @param color -> Color, representing the new color of the flipper.
     */
    public void setColor(Color color);

    /**
     * Getter method for end(bottom) circle.
     * 
     * @return Circle, representing the end circle of the flipper.
     * 		   i.e Circle that is not the origin of the flipper.
     */
    public Circle getEndCircle();

    /**
     * Getter method for Origin(top) circle.
     * 
     * @return Circle, representing the origin of the flipper.
     * 		   Flipper gets rotated around this flipper.
     */
    public Circle getOriginCircle();
    
    /**
     * Getter method for Movement.
     * 
     * @return Movement(enum), representing present movement of the flipper.
     * 		   Can take 3 values, FORWARDS if the flipper is moving forwards,
     * 		   BACKWARDS, if the flipper is returning to its initial position
     * 		   or NONE if the flipper remains still.
     */
    public Movement getMovement();

    /**
     * Setter Method for movement.
     * 
     * @param movement -> Movement. FORWARDS if the flipper is rotation.
     * 					  BACKWARDS if the flipper is rotating to it's 
     * 					  initial position or NONE if the flipper is meant
     * 				 	  to remain still.
     * 
     */
    public void setMovement(Movement movement);
    
    /**
     * Getter method for isLeft.
     * 
     * @return boolean, true if the flipper is a left flipper, false
     * 		   if it's a right flipper.
     */
    public boolean isLeft();
    
    /**
     * Getter method for the origin of the flipper. 
     * 
     * @return Vect, representing top left corner of the grid in which the flipper
     * 		   is placed.
     */
    public Vect getOrigin();
    
    /**
     * Setter method for Origin.
     * 
     * @param origin -> Vect, representing the new top left corner of the grid in
     * 				    which the flipper is placed.
     */
    public void setOrigin(Vect origin);
    
    /**
     * Getter method for Lines that represent the sides of the flipper.
     * 
     * @return List<LineSegment>, contains 2 lines representing the left and right side
     * 		   of the flipper.
     */
    public List<LineSegment> getLines();
    
    /**
     * Getter method for Circles representing the flipper.
     * 
     * @return List<Circle> that represents circles that the flipper is build from.
     * 		   First circle is the top circle, second is the bottom one. Another 4 
     * 		   are placed in the ends of lines to ensure perfect collisions.
     */
    public List<Circle> getCircles();
}
