package model;

import java.awt.Color;
import java.util.Observable;

import physics.*;

public class CircularBumper extends Observable implements iGizmo {
	private Circle circle;
	private Color color;
	private double radius;
	
	public CircularBumper(double cx, double cy, double r, Color color) {
		this.color = color;
		this.radius = r;
		circle = new Circle(cx, cy, r);
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void move(int cx, int cy) {
		circle = new Circle(cx, cy, radius);
	}
	
	//90 degrees clockwise rotation
	public void rotate() {
		
	}

	public int reservedArea() {
		return 1;
	}
}