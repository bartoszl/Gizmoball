package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class SquareBumper implements Bumper {
	private double x,y;
	private String name;
	private Color color;
	private int rotation;
	private static final double L = 20;
	
	public SquareBumper(double x, double y,int rotation, String name){
		this.x =x;
		this.y = y;
		this.name = name;
		this.color = Color.RED;
		this.rotation = rotation%4;
	}

	@Override
	public List<LineSegment> getLines() {
		List<LineSegment> l = new ArrayList<LineSegment>();
		LineSegment top = new LineSegment(x, y, x+L, y);
		LineSegment right = new LineSegment(x+L, y, x+L, y+L);
		LineSegment bottom = new LineSegment(x, y+L, x+L, y+L);
		LineSegment left = new LineSegment(x, y, x, y+L);
		l.add(top);
		l.add(right);
		l.add(bottom);
		l.add(left);
		return l;
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> c = new ArrayList<Circle>();
		Circle c1 = new Circle(x, y, 0);
		Circle c2 = new Circle(x+L, y, 0);
		Circle c3 = new Circle(x+L, y+L, 0);
		Circle c4 = new Circle(x, y+L, 0);
		c.add(c1);
		c.add(c2);
		c.add(c3);
		c.add(c4);
		return null;
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
		rotation +=1;
		rotation%=4;
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
		this.x=x;
		this.y=y;
	}

}
