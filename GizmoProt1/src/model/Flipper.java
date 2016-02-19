package model;

import java.awt.Color;

import physics.*;

public class Flipper implements iGizmo {
	public static enum Movement {
		FORWARDS, BACKWARDS, NONE
	};
	
	private boolean isLeft;
	private Color color;
	private Circle topCircle;
	private Circle bottomCircle;
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
		/** o **/
		topCircle = new Circle(center.x()+0.25 +off, center.y()+0.25, 0.25);
		/** - **/
		topSide = new LineSegment(center.x()+off, center.y()+0.25, center.x()+off+0.5, center.y()+0.25);
		/**| **/
		leftSide = new LineSegment(center.x()+off, center.y()+0.25, center.x()+off, center.y()+1.75);
		/**  |**/
		rightSide = new LineSegment(center.x()+off+0.5, center.y()+0.25, center.x()+off+0.5, center.y()+1.75);
		/** _ **/
		bottomSide = new LineSegment(center.x()+off, center.y()+1.75, center.x()+off+0.5, center.y()+1.75);
		/** o **/
		bottomCircle = new Circle(center.x()+off+0.25, center.y()+1.75, 0.25);
		
		movement = Movement.NONE;
	}
	
	/**Getter for coordinates -> translated to pixels**/
	public Vect getCenter() {
		return center.times(20.0);
	}
	
	public Vect getUpperLeft() {
		return topSide.p1().times(20.0);
	}
	
	public Vect getUpperRight() {
		return topSide.p2().times(20.0);
	}
	
	public Vect getLowerLeft() {
		return bottomSide.p1().times(20.0);
	}
	
	public Vect getLowerRight() {
		return bottomSide.p1().times(20.0);
	}
	
	public void rotate() {
		
		Angle a = Angle.DEG_90;
		Geometry.rotateAround(topSide, center, a);
		Geometry.rotateAround(leftSide, center, a);
		Geometry.rotateAround(bottomSide, center, a);
		Geometry.rotateAround(rightSide, center, a);
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
