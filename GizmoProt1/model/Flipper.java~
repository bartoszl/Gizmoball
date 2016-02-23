package model;

import java.awt.Color;

import physics.*;

public class Flipper implements iGizmo {
	public static enum Movement {
		FORWARDS, BACKWARDS, NONE
	};
	
	private boolean isLeft;
	private int cx;
	private int cy;
	private Color color;
	private LineSegment topSide;
	private LineSegment leftSide;
	private LineSegment rightSide;
	private LineSegment bottomSide;
	private Vect center;
	private Movement movement;
	
	/**
	 * By now just keep it simply rectangular
	 * @param cx
	 * @param cy
	 */
	public Flipper(int cx, int cy, boolean isLeft, Color color) {
		double off = isLeft ? 0 : 1.5;
		this.isLeft = isLeft;
		center = new Vect(cx, cy);
		topSide = new LineSegment(cx+off, cy, cx+off+0.5, cy);
		leftSide = new LineSegment(cx+off, cy, cx+off, cy+2);
		rightSide = new LineSegment(cx+off+0.5, cy, cx+off+0.5, cy+2);
		bottomSide = new LineSegment(cx+off, cy+2, cx+off+0.5, cy+2);
		this.color = color;
		movement = Movement.NONE;
	}
	
	//TODO or NOT TODO?
	public void rotate() {
		
	}
	
	public void move(int cx, int cy) {
		double off = isLeft ? 0 : 1.5;
		center = new Vect(cx, cy);
		topSide = new LineSegment(cx+off, cy, cx+off+0.5, cy);
		leftSide = new LineSegment(cx+off, cy, cx+off, cy+2);
		rightSide = new LineSegment(cx+off+0.5, cy, cx+off+0.5, cy+2);
		bottomSide = new LineSegment(cx+off, cy+2, cx+off+0.5, cy+2);		
	}
	
	public Angle movePerTick(Angle left) {
		/* Move at angular velocity of 1080 degrees per second
		 * One tick is approx 0.05 sec -> 1080/100 * 5 = 54 (about 0.95 radians) 
		 * degrees per tick
		 */
		//check the current state
		Angle rotation = new Angle(0.95);
		switch(movement) {
			case FORWARDS:
				if(left.compareTo(rotation) > 0) {
					//still much to go -> rotate it
					Geometry.rotateAround(topSide, center,rotation);
					Geometry.rotateAround(leftSide, center,rotation);
					Geometry.rotateAround(rightSide, center,rotation);
					Geometry.rotateAround(bottomSide, center,rotation);
					return left.minus(rotation);
				} else {
					//not much left -> rotate till end
					Geometry.rotateAround(topSide, center,left);
					Geometry.rotateAround(leftSide, center,left);
					Geometry.rotateAround(rightSide, center,left);
					Geometry.rotateAround(bottomSide, center,left);
					movement = Movement.NONE;
					return Angle.ZERO;
				}
			case BACKWARDS:
				//not quite how to specify other direction yet
				return Angle.ZERO;
			default:
				//DO nothing
				return Angle.ZERO;				
		}
	}
	
	public int reservedArea() {
		return 4;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
}
