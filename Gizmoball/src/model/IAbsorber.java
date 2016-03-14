package model;

import physics.Circle;
import physics.LineSegment;
import java.util.*;
import java.awt.Color;

/**
 * 
 * @author Bartosz Lewandowski
 * 
 * Interface for Absorber class.
 */
public interface IAbsorber {
	
	/**
	 * Getter method for x coordinate of the top left corner of the absorber.
	 * 
	 * @return double, representing x coordinate of the top left corner of the absorber.
	 */
	public double getXTopLeft();
	
	/**
	 * Getter method for y coordinate of the top left corner of the absorber.
	 * 
	 * @return double, representing y coordinate of the top left corner of the absorber.
	 */
	public double getYTopLeft();
	
	/**
	 * Getter method for x coordinate of the bottom right corner of the absorber.
	 * 
	 * @return double, representing x coordinate of the bottom right corner of the absorber.
	 */
	public double getXBottomRight();
	
	/**
	 * Getter method for y coordinate of the bottom right corner of the absorber.
	 * 
	 * @return double, representing y coordinate of the bottom right corner of the absorber.
	 */
	public double getYBottomRight();
	
	/**
	 * Method calculating the width for the absorber. This is a helper method
	 * to simplify drawing in the GUI. The width is calculated by subtracting
	 * top left x coordinate from bottom right x coordinate. 
	 * 
	 * @return double, representing the width of the absorber
	 */
	public double getWidth();
	
	/**
	 * Method calculating the height for the absorber. This is a helper method
	 * to simplify drawing in the GUI. The height is calculated by subtracting
	 * top left y coordinate from bottom right y coordinate. 
	 * 
	 * @return double, representing the height of the absorber
	 */
	public double getHeight();
	
	/**
	 * Method returning 4 line segments, representing the boundaries of the 
	 * absorber. This method is useful for calculating collisions.
	 * 
	 * @return List<LineSegments> containing 4 LineSegments, representing
	 * 		   top, right, bottom and left edge of the absorber.
	 */
	public List<LineSegment> getLines();
	
	/**
	 * Method returning 4 Circles representing 4 corners of the absorber.
	 * Radius of these Circles is set to 0. This method is useful for calculating 
	 * collisions.
	 * 
	 * @return List<Circle> containing 4 Circles, representing top left,
	 * 		   top right, bottom right and bottom left corner of the absorber.
	 */
	public List<Circle> getCircles();
	
	/**
	 * Getter method for color of the absorber.
	 * 
	 * @return Color, representing the color of the absorber.
	 */
	public Color getColor();
	
	/**
	 * Setter method for color.
	 * 
	 * @param color -> Instance of the Color class, which represents the new color
	 * 				   of the absorber.
	 */
	public void setColor(Color color);
	
	/**
	 * Method that should be called once the ball collides with the absorber.
	 * Sets the ball speed to -25*L. Sets the position of the ball to the
	 * Bottom left corner of the absorber and sets the absorbed field of the 
	 * ball to true. This field should be set to false, once tha ball escapes the 
	 * absorber.
	 * 
	 * @param ball -> Ball object representing a ball that just collided with
	 * 				  the absorber.
	 */
	public void absorb(Ball ball);

	public void fire();

	public String getName();
}
