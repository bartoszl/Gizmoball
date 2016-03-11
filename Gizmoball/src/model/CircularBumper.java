package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
/**
 * 
 * @author Bartosz Lewandowski
 * 
 * CircularBumper class, represents a Circular Bumper.
 * Implements Bumper.
 */
public class CircularBumper implements Bumper {
	
	private Circle circle;
	private double x,y;
	private Color color;
	private int rotation;
	private String name;
	private static final double RADIUS=10;
	private static final double L=20;

	/**
	 * Constructor for CircularBumper.
	 * @param x ->double, position of the left top corner of the grid along x axis in which the bumper is placed.
	 * @param y	->double, position of the left top corner of the grid along y axis in which the bumper is placed.
	 * @param rotation -> int, representing how many times the Bumper was rotated by 90 degrees.
	 * 					  If bigger than 3 the modulus of 4 is taken.
	 * @param name -> String, representing the name of this Bumper.
	 */
	public CircularBumper(double x, double y, int rotation, String name){
		this.x = x;
		this.y = y;
		this.circle = new Circle(x+L/2,y+L/2,RADIUS);
		this.rotation = rotation%4;
		this.name = name;
		color = color.GREEN;
	}
	
	@Override
	public List<LineSegment> getLines() {
		return new ArrayList<LineSegment>();
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> l = new ArrayList<Circle>();
		l.add(circle);
		return l;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	@Override
	public void rotate() {
		rotation=(rotation+1)%4;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setColor(Color color) {
		this.color = color;
		
	}
	@Override
	public void move(double x, double y) {
		circle = new Circle(x+10, y+10, RADIUS);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}

}
