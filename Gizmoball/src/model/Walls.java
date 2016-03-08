package model;

import java.util.ArrayList;
import java.util.List;

import physics.LineSegment;

public class Walls {
	private double x,y,x1,y1;
	
	public Walls(double x, double y, double x1, double x2){
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public List<LineSegment> getLineSegments(){
		List<LineSegment> l = new ArrayList<LineSegment>();
		l.add(new LineSegment(x,y,x1,y));
		l.add(new LineSegment(x1,y,x1,y1));
		l.add(new LineSegment(x1,y1,x,y1));
		l.add(new LineSegment(x,y,x,y1));
		return l;
	}
}
