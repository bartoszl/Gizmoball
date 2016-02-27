package model;

import physics.Circle;
import physics.LineSegment;
import java.util.*;
import java.awt.Color;

public interface IAbsorber {
	
	public double getXTopLeft();
	
	public double getYTopLeft();
	
	public double getXBottomRight();
	
	public double getYBottomRight();
	
	public double getWidth();
	
	public double getHeight();
	
	public List<LineSegment> getLines();
	
	public List<Circle> getCircles();
	
	public Color getColor();
	
	public void absorb(Ball ball);
	
}
