package model;

import java.awt.Color;

import physics.*;

public class SquareBumper implements iGizmo {
	private Color color;
	private LineSegment sideOne;
	private LineSegment sideTwo;
	private LineSegment sideThree;
	private LineSegment sideFour;

	public SquareBumper(double cx, double cy, Color color) {
		sideOne = new LineSegment(cx, cy, cx+1, cy);
		sideTwo = new LineSegment(cx, cy, cx, cy+1);
		sideThree = new LineSegment(cx, cy+1, cx+1, cy+1);
		sideFour = new LineSegment(cx+1, cy, cx+1, cy+1);
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
<<<<<<< HEAD:GizmoProt1/model/SquareBumper.java
	
=======

>>>>>>> master:GizmoProt1/src/model/SquareBumper.java
	public void move(int cx, int cy) {
		sideOne = new LineSegment(cx, cy, cx+1, cy);
		sideTwo = new LineSegment(cx, cy, cx, cy+1);
		sideThree = new LineSegment(cx, cy+1, cx+1, cy+1);
		sideFour = new LineSegment(cx+1, cy, cx+1, cy+1);
	}
	
	public void rotate() {
		
	}
<<<<<<< HEAD:GizmoProt1/model/SquareBumper.java
	
=======

>>>>>>> master:GizmoProt1/src/model/SquareBumper.java
	public int reservedArea() {
		return 1;
	}
}
