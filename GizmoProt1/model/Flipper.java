package model;

import java.awt.Color;

import physics.*;

public class Flipper implements iGizmo {
	private boolean isLeft;
	private boolean isVertical;
	private int cx;
	private int cy;
	private Color color;
	private LineSegment topSide;
	private LineSegment leftSide;
	private LineSegment rightSide;
	private LineSegment bottomSide;
	private Vect center;
	
	/**
	 * By now just keep it simply rectangular
	 * @param cx
	 * @param cy
	 */
	public Flipper(int cx, int cy, boolean isLeft, Color color) {
		double off = isLeft ? 0 : 1.5;
		center = new Vect(cx, cy);
		topSide = new LineSegment(cx+off, cy, cx+off+0.5, cy);
		leftSide = new LineSegment(cx+off, cy, cx+off, cy+2);
		rightSide = new LineSegment(cx+off+0.5, cy, cx+off+0.5, cy+2);
		bottomSide = new LineSegment(cx+off, cy+2, cx+off+0.5, cy+2);
		this.color = color;
	}
	
	public void rotate() {
		
	}
	
	public void trigger() {
		while(leftSide.angle().compareTo(Angle.DEG_90) < 0) {
			/*Move at angular velocity of 1080 degrees per second*/
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
}
