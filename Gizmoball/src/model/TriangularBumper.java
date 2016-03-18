package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.*;

/**
 * 
 * @author Bartosz Lewandowski
 *
 */
public class TriangularBumper implements Bumper {
    private Color color;
    private String gizmoName;
    private double cx, cy;
    private int rotation;
    
    /**
     * Constructor for Triangular Bumper.
     * @param cx -> double, representing the x coordinate of the top left corner of the grid
     * 				in which the Bumper is placed.
     * @param cy -> double, representing the x coordinate of the top left corner of the grid
     * 				in which the Bumper is placed.
     * @param rotation -> int, representing how many times the bumper should be rotated by 90 degrees.
     * @param gizmoName -> String, representing a name for the Bumper.
     */
    public TriangularBumper(double cx, double cy, int rotation, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
        this.gizmoName = gizmoName;
        this.rotation = rotation%4;
        this.color = Color.BLUE;
    }
    
    @Override
    public double getX() {
        return cx;
    }
    
    @Override
    public double getY() {
        return cy;
    }

    @Override
    public List<LineSegment> getLines() {
        List<LineSegment> l = new ArrayList<LineSegment>();
        switch(rotation){
        	case(0):
        		l.add(new LineSegment(cx,cy,cx+20,cy));
        		l.add(new LineSegment(cx+20,cy,cx,cy+20));
        		l.add(new LineSegment(cx,cy,cx,cy+20));
        		break;
        	case(1):
        		l.add(new LineSegment(cx,cy,cx+20,cy));
        		l.add(new LineSegment(cx+20,cy,cx+20,cy+20));
        		l.add(new LineSegment(cx,cy,cx+20,cy+20));
        		break;
        	case(2):
        		l.add(new LineSegment(cx+20,cy,cx+20,cy+20));
        		l.add(new LineSegment(cx+20,cy+20,cx,cy+20));
        		l.add(new LineSegment(cx,cy+20,cx+20,cy));
        		break;
        	case(3):
        		l.add(new LineSegment(cx,cy,cx+20,cy+20));
        		l.add(new LineSegment(cx+20,cy+20,cx,cy+20));
        		l.add(new LineSegment(cx,cy,cx,cy+20));
        		break;
        }
        return l;
    }

    @Override
    public List<Circle> getCircles() {
        List<Circle> circles = new ArrayList<Circle>();
        switch(rotation){
        	case(0):
        		circles.add(new Circle(cx,cy,0));
        		circles.add(new Circle(cx+20,cy,0));
        		circles.add(new Circle(cx,cy+20,0));
        		break;
        	case(1):
        		circles.add(new Circle(cx,cy,0));
        		circles.add(new Circle(cx+20,cy,0));
        		circles.add(new Circle(cx+20,cy+20,0));
        		break;
        	case(2):
        		circles.add(new Circle(cx+20,cy,0));
        		circles.add(new Circle(cx+20,cy+20,0));
        		circles.add(new Circle(cx,cy+20,0));
        		break;
        	case(3):
        		circles.add(new Circle(cx,cy,0));
        		circles.add(new Circle(cx+20,cy+20,0));
        		circles.add(new Circle(cx,cy+20,0));
        		break;
        }
        
        return circles;
    }
    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getRotation() {
        return rotation;
    }

    
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void move(double cx, double cy){
        this.cx=cx;
        this.cy=cy;
    }
    @Override
    public void rotate() {
    	rotation=++rotation%4;
    }
    
    @Override
    public String getName() {
        return gizmoName;
    }
}