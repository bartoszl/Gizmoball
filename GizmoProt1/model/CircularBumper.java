package model;

import java.awt.Color;

import physics.*;

public class CircularBumper implements iGizmo {
	private Circle circle;
	private Color color;
	
	public CircularBumper(double cx, double cy, double r, Color color) {
		this.color = color;
		circle = new Circle(cx, cy, r);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public int reservedArea() {
		return 1;
	}
}