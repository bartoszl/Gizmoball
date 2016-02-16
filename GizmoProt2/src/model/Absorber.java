package model;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Absorber implements IAbsorber {
	private double xTopLeft,yTopLeft, xBottomRight, yBottomRight;
	private Color color;
	
	public Absorber(double xTopLeft, double yTopLeft, double xBottomRight, double yBottomRight){
		this.xTopLeft = xTopLeft;
		this.yTopLeft = yTopLeft;
		this.xBottomRight = xBottomRight;
		this.yBottomRight = yBottomRight;
		color = Color.PINK;
	}
	
	public ArrayList<LineSegment> getLines(){
		ArrayList<LineSegment> lineList = new ArrayList<LineSegment>();
		LineSegment top = new LineSegment(xTopLeft, yTopLeft,xBottomRight, yTopLeft);
		LineSegment bottom = new LineSegment(xTopLeft, yBottomRight,xBottomRight, yBottomRight);
		LineSegment left = new LineSegment(xTopLeft, yTopLeft, xTopLeft, yBottomRight);
		LineSegment right = new LineSegment(xBottomRight, yTopLeft,xBottomRight, yBottomRight);
		lineList.add(top);
		lineList.add(bottom);
		lineList.add(left);
		lineList.add(right);
		return lineList;
	}
	
	public ArrayList<Circle> getCircles(){
		ArrayList<Circle> circleList = new ArrayList<Circle>();
		Circle topLeft = new Circle(xTopLeft, yTopLeft, 0);
		Circle topRight = new Circle(xBottomRight, yTopLeft, 0);
		Circle bottomLeft = new Circle(xTopLeft, yBottomRight, 0);
		Circle bottomRight = new Circle(xBottomRight, yBottomRight, 0);
		circleList.add(topLeft);
		circleList.add(topRight);
		circleList.add(bottomLeft);
		circleList.add(bottomRight);
		return circleList;
	}
	
	public void absorb(Ball ball){
		ball.setMoving(false);
		ball.setXY(xBottomRight, yBottomRight);
		Vect v = new Vect(400,0);
		ball.setVelocity(v);
	}
	
	public Color getColor(){
		return color;
	}
	
}