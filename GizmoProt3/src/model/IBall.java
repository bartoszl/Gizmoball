package model;

import physics.Circle;
import physics.Vect;

import java.awt.*;

public interface IBall {
	
	public Vect getVelocity();
	public void setVelocity(Vect v);
	public double getRadius();
	public Circle getCircle();
	public boolean isMoving();
	public void setMoving(boolean b);
	public Color getColor();
	public void setColor(Color c);
	public double getX();
	public double getY();
	public void setXY(double x, double y);
}
