package model;

import java.util.Vector;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Absorber implements IAbsorber {
	private LineSegment top;
	private LineSegment bottom;
	private LineSegment left;
	private LineSegment right;
	private Circle topLeft;
	private Circle topRight;
	private Circle bottomLeft;
	private Circle bottomRight;
	
	public Absorber(double xTopLeft, double yTopLeft, double xBottomRight, double yBottomRight){
		/* LINES */
		top = new LineSegment(xTopLeft, yTopLeft,xBottomRight, yTopLeft);
		bottom = new LineSegment(xTopLeft, yBottomRight,xBottomRight, yBottomRight);
		left = new LineSegment(xTopLeft, yTopLeft, xTopLeft, yBottomRight);
		right = new LineSegment(xBottomRight, yTopLeft,xBottomRight, yBottomRight);
		
		/* Circles */
		topLeft = new Circle(xTopLeft, yTopLeft, 0);
		topRight = new Circle(xBottomRight, yTopLeft, 0);
		bottomLeft = new Circle(xTopLeft, yBottomRight, 0);
		bottomRight = new Circle(xBottomRight, yBottomRight, 0);
	}
	
}
