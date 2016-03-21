package model;

import java.awt.Color;
import java.util.List;

import physics.*;
/**
 * 
 * @author Bartosz Lewandowski
 * 
 * Interface for bumpers (Circular, Triangular and Square).
 *
 */
public interface Bumper {
	
	/**
	 * Getter class for LineSegments, which represent edges of a Bumper.
	 * @return List of LineSegment, representing edges of the Bumper.
	 */
	public List<LineSegment> getLines();
	
	/**
	 * Getter class for Circles, which represent corners of the Bumper.
	 * 
	 * @return List of Circle, representing corners of the Bumpers.
	 */
	public List<Circle> getCircles();
	
	/**
	 * Getter class for color.
	 * 
	 * @return Color, color of the Bumper.
	 */
	public Color getColor();
	
	/**
	 * 
	 * @return int, represents the number of rotation for a bumper.
	 * 		   Each rotation is 90 degrees.
	 */
	public int getRotation();
	
	/**
	 * rotate method, adds 1 to rotation variable. Each rotation
	 * represents how many times was the Bumper rotated by 90 degrees.
	 * Therefore the after adding 1 to rotation a modulus of 4 is taken,
	 * as rotation by 360 degrees is the same as 0.
	 */
	public void rotate();
	
	/**
	 * Getter method for name.
	 * 
	 * @return String, which represents the name of the Bumper.
	 */
	public String getName();
	
	/**
	 * Setter method for color.
	 * 
	 * @param color -> Color, representing the new color of a Bumper.
	 */
	public void setColor(Color color);
	
	/**
	 * Move method for Bumper, sets new "center" of a Bumper. 
	 * The new center is the left top corner of a grid the bumper is
	 * placed at.
	 * 
	 * @param x -> double representing position of a bumper along x axis.
	 * @param y -> double representing position of a bumper along y axis.
	 */
	public void move(double x, double y);
	
	/**
	 * Getter method for X coordinate.
	 * 
	 * @return double, representing the x coordinate of the top left corner of the grid in which 
	 * 		   the bumper is placed.
	 */
	public double getX();
	
	/**
	 * Getter method for Y coordinate.
	 * 
	 * @return double, representing the y coordinate of the top left corner of the grid in which 
	 * 		   the bumper is placed.
	 */
	public double getY();

}
