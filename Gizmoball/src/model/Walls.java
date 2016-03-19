package model;

import java.util.ArrayList;
import java.util.List;

import physics.LineSegment;

/**
 * 
 * @author Bartosz Lewandowski
 *
 */
public class Walls {
	private double x,y,x1,y1;
	
	/**
	 * Constructor for Walls.
	 * 
	 * @param x -> double, representing the x coordinate of the top left corner of the Board. (Usually 0).
	 * @param y -> double, representing the y coordinate of the top left corner of the Board. (Usually 0).
	 * @param x1 -> double, representing the x coordinate of the bottom right corner of the Board. (20*L)
	 * @param y1 -> double, representing the y coordinate of the bottom right corner of the Board. (20*L)
	 */
	public Walls(double x, double y, double x1, double y1){
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
	}
	
	/**
	 * Getter method for LineSegments representing walls.
	 * 
	 * @return List<LineSegment>, representing 4 walls.
	 */
	public List<LineSegment> getLines(){
		List<LineSegment> l = new ArrayList<LineSegment>();
		l.add(new LineSegment(x,y,x1,y));
		l.add(new LineSegment(x1,y,x1,y1));
		l.add(new LineSegment(x1,y1,x,y1));
		l.add(new LineSegment(x,y,x,y1));
		return l;
	}
}
