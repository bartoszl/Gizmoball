package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class Absorber implements IAbsorber {
	private double xTopLeft, yTopLeft, xBottomRight, yBottomRight;
	private Color color;
	
	public Absorber(double x, double y, double x1, double y1){
		xTopLeft=x;
		yTopLeft=y;
		xBottomRight=x1;
		yBottomRight=y1;
		color=Color.PINK;
	}

	@Override
	public double getXTopLeft() {
		return xTopLeft;
	}

	@Override
	public double getYTopLeft() {
		return yTopLeft;
	}

	@Override
	public double getXBottomRight() {
		return xBottomRight;
	}

	@Override
	public double getYBottomRight() {
		return yBottomRight;
	}

	@Override
	public double getWidth() {
		return xBottomRight-xTopLeft;
	}

	@Override
	public double getHeight() {
		return yBottomRight-yTopLeft;
	}

	@Override
	public List<LineSegment> getLines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Circle> getCircles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void absorb(Ball ball) {
		// TODO Auto-generated method stub
		
	}
	
}
