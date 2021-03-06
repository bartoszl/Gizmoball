package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bartosz Lewandowski
 * 
 * Part of the Gimozball model, representing the Absorber.
 * Implements IAbsorber interface.
 *
 */
public class Absorber implements IAbsorber {
	private double xTopLeft, yTopLeft, xBottomRight, yBottomRight;
	private Color color;
	private String name;
	private List<Ball> ballsAbsorbed;
	private boolean connectedToItself;
	/**
	 * Constructor for Absorber object. Initially sets color to Pink
	 *
	 * @param name -> string, representing the name of the absorber
	 * @param x -> double, representing x coordinate of top left corner of the absorber.
	 * @param y -> double, representing y coordinate of top left corner of the absorber.
	 * @param x1 -> double, representing x coordinate of bottom right corner of the absorber.
	 * @param y1 -> double, representing y coordinate of the bottom right corner of the absorber.
	 */
	public Absorber(String name, double x, double y, double x1, double y1){
		xTopLeft=Math.min(x,x1);
		yTopLeft=Math.min(y,y1);
		xBottomRight=Math.max(x,x1);
		yBottomRight=Math.max(y,y1);
		color=Color.PINK;
		this.name = name;
		this.ballsAbsorbed = new ArrayList<Ball>();
		this.connectedToItself = true;
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
		List<LineSegment> l = new ArrayList<LineSegment>();
		LineSegment l1 = new LineSegment(xTopLeft, yTopLeft, xBottomRight, yTopLeft);
		LineSegment l2 = new LineSegment(xBottomRight, yTopLeft, xBottomRight, yBottomRight);
		LineSegment l3 = new LineSegment(xTopLeft, yBottomRight, xBottomRight, yBottomRight);
		LineSegment l4 = new LineSegment(xTopLeft, yTopLeft, xTopLeft, yBottomRight);
		l.add(l1);
		l.add(l2);
		l.add(l3);
		l.add(l4);
		return l;
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> l = new ArrayList<Circle>();
		Circle c1 = new Circle(xTopLeft, yTopLeft, 0);
		Circle c2 = new Circle(xBottomRight, yTopLeft, 0);
		Circle c3 = new Circle(xBottomRight, yBottomRight, 0);
		Circle c4 = new Circle(xTopLeft, yBottomRight, 0);
		l.add(c1);
		l.add(c2);
		l.add(c3);
		l.add(c4);
		return l;
	}

    public void move(double x, double y) {
        double width = getWidth(), height = getHeight();
        this.xTopLeft=x;
        this.yTopLeft=y;
        this.xBottomRight=xTopLeft+width;
        this.yBottomRight=yTopLeft+height;
    }

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color=color;
	}
	
	@Override
	public void absorb(Ball ball) {
		ball.setAbsorbed(true);
		ballsAbsorbed.add(ball);
		ball.setMoving(false);
		if(isConnectedToItself()) {
			fire();
		}
		ball.setXY(xBottomRight-10, yBottomRight-10);
	}
	
	@Override
	public void fire() {
		for(Ball ball : ballsAbsorbed) {
			ball.setMoving(true);
			ball.setVelocity(0, -1000);
		}
		ballsAbsorbed = new ArrayList<>();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean isConnectedToItself() {
		return connectedToItself;
	}
	
	@Override
	public void setConnectedToItself(boolean connectedToItself) {
		this.connectedToItself = connectedToItself;
	}
}
