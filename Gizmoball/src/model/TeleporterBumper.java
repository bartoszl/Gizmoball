package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

/**
 * 
 * @author Martin Peev
 *
 */
public class TeleporterBumper implements Bumper{
    private Color color;
    private String gizmoName;
    private double cx, cy;
    private int rotation;
    
    /**
     * Teleporter Bumper constructor
     * @param cx - x coordinate of top left corner of the grid box where it is placed
     * @param cy - y coordinate of top left corner of the grid box where it is placed
     * @param rotation - current rotation of the teleporter
     * @param gizmoName - name of the gizmo (mainly used for saving/loading)
     */
    public TeleporterBumper(double cx, double cy, int rotation, String gizmoName){
        this.cx = cx;
        this.cy = cy;
        this.gizmoName = gizmoName;
        this.rotation = rotation%4;
        this.color = Color.CYAN;
    }
    
	@Override
	public List<LineSegment> getLines() {
        List<LineSegment> lines = new ArrayList<LineSegment>();
        switch(rotation){
        	case(0):
        		lines.add(new LineSegment(cx,cy,cx+20,cy));
        		lines.add(new LineSegment(cx,cy,cx,cy+20));
        		lines.add(new LineSegment(cx+20,cy,cx,cy+20));
        		break;
        	case(1):
        		lines.add(new LineSegment(cx,cy,cx+20,cy));
        		lines.add(new LineSegment(cx+20,cy,cx+20,cy+20));
        		lines.add(new LineSegment(cx,cy,cx+20,cy+20));
        		break;
        	case(2):
        		lines.add(new LineSegment(cx+20,cy,cx+20,cy+20));
        		lines.add(new LineSegment(cx+20,cy+20,cx,cy+20));
        		lines.add(new LineSegment(cx,cy+20,cx+20,cy));
        		break;
        	case(3):
        		lines.add(new LineSegment(cx+20,cy+20,cx,cy+20));
        		lines.add(new LineSegment(cx,cy,cx,cy+20));
        		lines.add(new LineSegment(cx,cy,cx+20,cy+20));
        		break;
        }
        return lines;
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
	public void rotate() {
		rotation=(rotation+1)%4;
	}

	@Override
	public String getName() {
		return gizmoName;
	}

	@Override
	public void setColor(Color color) {
		this.color=color;
	}

	@Override
	public void move(double cx, double cy) {
        this.cx=cx;
        this.cy=cy;
	}

	@Override
	public double getX() {
		return cx;
	}

	@Override
	public double getY() {
		return cy;
	}

}
