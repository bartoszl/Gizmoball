package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public interface IBall {
	
	public Vect getVelocity();
	
	public void setVelocity(Vect v);
	
	public double getRadius();
	
	public Circle getCircle();
	
	public boolean isMoving();
	
	public void setMoving(boolean b);
	
	public Color getColor();
	
	public void setColor(Color c);
}
