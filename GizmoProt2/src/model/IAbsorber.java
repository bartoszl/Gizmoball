package model;

import java.awt.Color;
import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;

public interface IAbsorber {
	
	public ArrayList<LineSegment> getLines();
	public ArrayList<Circle> getCircles();
	public void absorb(Ball ball);
	public Color getColor();
	public double getXTopLeft();
	public double getYTopLeft();
	public double getWidth();
	public double getHeight();
}
