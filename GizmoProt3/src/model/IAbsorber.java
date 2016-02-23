package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

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
