package model;

import java.awt.Color;
import java.util.Observable;

import physics.*;

public class Flipper extends Observable implements iGizmo, Runnable{
	public static enum Movement {
		FORWARDS, BACKWARDS, NONE
	}

	
	private boolean isLeft;
	private Color color;
	private Circle topCircle;
	private Circle bottomCircle;
	private LineSegment topSide;
	private LineSegment leftSide;
	private LineSegment rightSide;
	private LineSegment bottomSide;
	private Vect center;
	private static Movement movement;
	private Angle rotation;
	private Angle leftToRotate;
	
	/**
	 * By now just keep it simply rectangular
	 * @param cx
	 * @param cy
	 */
	public Flipper(int cx, int cy, boolean isLeft, Color color) {
		leftToRotate = Angle.DEG_90;
		double off = isLeft ? 0 : 1.5;
		//For the right flipper it is 54 degrees -> 0.95 rad
		//For the left it is 360 - 54 = 304 ->
		rotation = new Angle(0.95);
		this.isLeft = isLeft;
		this.color=color;
		center = new Vect((double)cx, (double)cy);
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
	
	public Circle getOriginCircle(){
		return topCircle;
	}
	
	public Circle getEndCircle(){
		return bottomCircle;
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
		
		this.notifyObservers();
	}
	
	public Angle getLeft() {
		return leftToRotate;
	}
	
	public Angle movePerTick(Angle left) {
		/* Move at angular velocity of 1080 degrees per second
		 * One tick is approx 0.05 sec -> 1080/100 * 5 = 54 (about 0.95 radians) 
		 * degrees per tick
		 */
		//check the current state
		
		switch(movement) {
			case FORWARDS:
				if(left.compareTo(rotation) > 0) {
					//still much to go -> rotate it
					move(rotation);
					leftToRotate = left.minus(rotation);
					return leftToRotate;
				} else {
					//not much left -> rotate till end
					//left = isLeft ? Angle.DEG_90.minus(left) : left;
					move(left);
					movement = Movement.NONE;
					return Angle.ZERO;
				}
			case BACKWARDS:
				if(left.compareTo(rotation) > 0) {
					//still much to go -> rotate it
					move(Angle.ZERO.minus(rotation));
					leftToRotate = left.minus(rotation);
					return leftToRotate;
				} else {
					//not much left -> rotate till end
					//left = isLeft ? Angle.DEG_90.minus(left) : left;
					move(Angle.ZERO.minus(left));
					movement = Movement.NONE;
					return Angle.ZERO;
				}
			default:
				//DO nothing
				return Angle.ZERO;				
		}
	}
	
	public void move(Angle a) {
		a = isLeft ? Angle.ZERO.minus(a) : a;
		bottomCircle = Geometry.rotateAround(bottomCircle, center, a);
		topSide = Geometry.rotateAround(topSide, center, a);
		leftSide = Geometry.rotateAround(leftSide, center, a);
		rightSide = Geometry.rotateAround(rightSide, center, a);
		bottomSide = Geometry.rotateAround(bottomSide, center, a);
		topCircle = Geometry.rotateAround(topCircle, center, a);
		setChanged();
		notifyObservers();
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
	
	public Movement getMovement() {
		return movement;
	}
	
	public void setMovement(Movement movement) {
		this.movement = movement;
	}

	@Override
	public void run() {
		System.out.println("forwards");
		setMovement(Movement.FORWARDS);
		Angle a = Angle.DEG_90;
		while(getMovement() == Movement.FORWARDS) {
			a = movePerTick(a);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
