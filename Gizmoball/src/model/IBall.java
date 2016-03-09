package model;

import physics.Circle;
import physics.Vect;
import java.awt.Color;

/**
 * 
 * @author Bartosz Lewandowski
 * 
 * Interface for Ball class.
 */
public interface IBall {
	
	/**
	 * Getter method for velocity.
	 * 
	 * @return Vect, representing velocity of the ball.
	 */
	public Vect getVelocity();
	
	/**
	 * Setter method for velocity.
	 * 
	 * @param v -> Vect, representing new velocity of the Ball.
	 */
	public void setVelocity(Vect v);
	
	/**
	 * Setter method for velocity.
	 * 
	 * @param vx -> double, representing new velocity along X axis.
	 * @param vy -> double, representing new velocity along Y axis.
	 */
	public void setVelocity(double vx, double vy);
	
	/**
	 * Getter method for x coordinate.
	 * 
	 * @return double, X coordinate of the Ball.
	 */
	public double getX();
	
	/**
	 * Getter method for y coordinate.
	 * 
	 * @return double, Y coordinate of the Ball
	 */
	public double getY();
	
	/**
	 * Setter method for X and Y coordinates.
	 * 
	 * @param x -> double, new X coordinate of the ball.
	 * @param y -> double, new Y coordinate of the ball.
	 */
	public void setXY(double x, double y);
	
	/**
	 * Getter method for Color.
	 * 
	 * @return -> Color, representing Color of the ball.
	 */
	public Color getColor();
	
	/**
	 * Setter method for Color
	 * 
	 * @param color -> Color, new color of the Ball.
	 */
	public void setColor(Color color);
	
	/**
	 * Getter method for radius.
	 * 
	 * @return double, the radius of the Ball.
	 */
	public double getRadius();
	
	/**
	 * Setter method for radius
	 * 
	 * @param r -> double, new radius of the ball.
	 */
	public void setRadius(double r);
	
	/**
	 * Getter method for moving.
	 * 
	 * @return boolean, showing whether the ball is moving or not.
	 */
	public boolean isMoving();
	
	/**
	 * Setter method for moving.
	 * 
	 * @param b -> boolean, new value for moving.
	 */
	public void setMoving(boolean b);
	
	/**
	 * Getter method for absorbed.
	 * 
	 * @return boolean, showing whether the ball is absorbed or not.
	 */
	public boolean isAbsorbed();
	
	/**
	 * Setter method for absorber.
	 * 
	 * @param b -> boolean, new value for absorbed.
	 */
	public void setAbsorbed(boolean b);
	
	/**
	 * Helper method, that returns a circle representing the Ball.
	 * 
	 * @return Circle, representing the ball.
	 */
	public Circle getCircle();

	public double getLeftLimit();

	public double getUpperLimit();

	public double getRightLimit();

	public double getLowerLimit();
	
	public void move(double x,double y);

	public String getName();
}
