package model;

import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;

public interface IAbsorber {
	public ArrayList<LineSegment> getLines();
	
	public ArrayList<Circle> getCircles();
	
	public void absorb(Ball ball);
}
