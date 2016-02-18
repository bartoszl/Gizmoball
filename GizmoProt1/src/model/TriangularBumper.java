package model;

import java.awt.Color;

import physics.*;

public class TriangularBumper implements iGizmo {
	private LineSegment sideOne;
	private LineSegment sideTwo;
	private LineSegment hypotenuse;
	private Color color;
	private boolean right;
	private boolean down;
	private Vect center;
	
	public TriangularBumper(
<<<<<<< HEAD:GizmoProt1/model/TriangularBumper.java
			int cx, int cy, 
=======
			double cx, double cy,
>>>>>>> master:GizmoProt1/src/model/TriangularBumper.java
			boolean right, boolean down, Color color
			) {
		int off1 = down ? 1 : 0;
		int off2 = right ? 1 : 0;
		sideOne = new LineSegment(cx+off1, cy+off1, cx+1+off1, cy+off1);
		sideTwo = new LineSegment(cx+off2, cy+off2, cx+off2, cy+1+off2);
		hypotenuse = new LineSegment(cx+1+off1, cy+off1, cx+off2, cy+1+off2);
		this.color = color;
<<<<<<< HEAD:GizmoProt1/model/TriangularBumper.java
		this.right = right;
		this.down = down;
		this.center = new Vect(cx+0.5, cy+0.5);
=======
>>>>>>> master:GizmoProt1/src/model/TriangularBumper.java
	}
	
	public Color getColor() {
		return color;
	}
<<<<<<< HEAD:GizmoProt1/model/TriangularBumper.java
	
	public void setColor(Color color) {
		this.color = color;
	}
	
=======

	public void setColor(Color color) {
		this.color = color;
	}

>>>>>>> master:GizmoProt1/src/model/TriangularBumper.java
	public LineSegment getHypotenuse() {
		return hypotenuse;
	}
	
	public LineSegment getSideOne() {
		return sideOne;
	}
	
	public LineSegment getSideTwo() {
		return sideTwo;
	}
<<<<<<< HEAD:GizmoProt1/model/TriangularBumper.java
=======
<<<<<<< HEAD
>>>>>>> master:GizmoProt1/src/model/TriangularBumper.java

	public void move(int cx, int cy) {
		int off1 = down ? 1 : 0;
		int off2 = right ? 1 : 0;
		sideOne = new LineSegment(cx+off1, cy+off1, cx+1+off1, cy+off1);
		sideTwo = new LineSegment(cx+off2, cy+off2, cx+off2, cy+1+off2);
		hypotenuse = new LineSegment(cx+1+off1, cy+off1, cx+off2, cy+1+off2);		
	}
	
	public void rotate() {
		Angle a = Angle.DEG_90;
		Geometry.rotateAround(sideOne, center, a);
		Geometry.rotateAround(sideTwo, center, a);
		Geometry.rotateAround(hypotenuse, center, a);
<<<<<<< HEAD:GizmoProt1/model/TriangularBumper.java
	}
=======
>>>>>>> master:GizmoProt1/src/model/TriangularBumper.java
	
	public int reservedArea() {
		return 1;
	}
	
}