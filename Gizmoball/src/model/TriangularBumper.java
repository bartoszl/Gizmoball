package model;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import physics.*;

public class TriangularBumper implements Bumper {
    private LineSegment sideOne;
    private LineSegment sideTwo;
    private LineSegment hypotenuse;
    private Color color;
    private String gizmoName;
    private double cx, cy;
    private Vect center;
    private int rotation;

    private int scale;

    public TriangularBumper(double cx, double cy, int rotation, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
        this.gizmoName = gizmoName;
        this.rotation = rotation%4;
        this.color = Color.BLUE;
        /*
        scale = 20;
        sideOne = new LineSegment(cx, cy, cx + (1* scale), cy);
        sideTwo = new LineSegment(cx, cy, cx, cy + (1 * scale));
        hypotenuse = new LineSegment(sideTwo.p2().x(), sideTwo.p2().y(), sideOne.p2().x(), sideOne.p2().y());
        this.gizmoName = gizmoName;
        double centerX = Math.abs((hypotenuse.p1().x() - hypotenuse.p2().x())/ 2);
        double centerY = Math.abs((hypotenuse.p1().y() - hypotenuse.p2().y())/ 2);
        if(hypotenuse.p1().y() > hypotenuse.p2().y()) {
            if(hypotenuse.p1().x() < hypotenuse.p2().x()) {
                this.center = new Vect(Math.abs(hypotenuse.p1().x() + centerX), Math.abs(hypotenuse.p1().y() - centerY));
            } else {
                this.center = new Vect(Math.abs(hypotenuse.p1().x() - centerX), Math.abs(hypotenuse.p1().y() - centerY));
            }
        } else {
            if(hypotenuse.p1().x() < hypotenuse.p2().x()) {
                if(hypotenuse.p1().y() < 0) {
                    this.center = new Vect(hypotenuse.p1().x() + centerX, centerY);
                } else {
                    this.center = new Vect(hypotenuse.p1().x() + centerX, hypotenuse.p1().y() + centerY);
                }
            } else {
                if(hypotenuse.p1().y() < 0) {
                    this.center = new Vect(hypotenuse.p1().x() - centerX, centerY);
                } else {
                    this.center = new Vect(hypotenuse.p1().x() - centerX, hypotenuse.p1().y() + centerY);
                }
            }
        }
        for(int i=0;i<rotation%4;i++){
        	this.rotate();
        }*/
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
        /*List<LineSegment> lines = new ArrayList<>();
        lines.add(sideOne);
        lines.add(sideTwo);
        lines.add(hypotenuse);
        return null;
        */
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
        		l.add(new LineSegment(cx,cy+20,cx+20,cy+20));
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

    public LineSegment getHypotenuse() {
        return hypotenuse;
    }

    public LineSegment getSideOne() {
        return sideOne;
    }

    public LineSegment getSideTwo() {
        return sideTwo;
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
    	/*
        Angle a = Angle.DEG_90;
        sideOne = Geometry.rotateAround(sideOne, center, a);
        sideTwo = Geometry.rotateAround(sideTwo, center, a);
        hypotenuse = new LineSegment(sideTwo.p2().x(), sideTwo.p2().y(), sideOne.p2().x(), sideOne.p2().y());
        double centerX = Math.abs((hypotenuse.p1().x() - hypotenuse.p2().x())/ 2);
        double centerY = Math.abs((hypotenuse.p1().y() - hypotenuse.p2().y())/ 2);
        if(hypotenuse.p1().y() > hypotenuse.p2().y()) {
            if(hypotenuse.p1().x() < hypotenuse.p2().x()) {
                this.center = new Vect(Math.abs(hypotenuse.p1().x() + centerX), Math.abs(hypotenuse.p1().y() - centerY));
            } else {
                this.center = new Vect(Math.abs(hypotenuse.p1().x() - centerX), Math.abs(hypotenuse.p1().y() - centerY));
            }
        } else {
            if(hypotenuse.p1().x() < hypotenuse.p2().x()) {
                if(hypotenuse.p1().y() < 0) {
                    this.center = new Vect(hypotenuse.p1().x() + centerX, centerY);
                } else {
                    this.center = new Vect(hypotenuse.p1().x() + centerX, hypotenuse.p1().y() + centerY);
                }
            } else {
                if(hypotenuse.p1().y() < 0) {
                    this.center = new Vect(hypotenuse.p1().x() - centerX, centerY);
                } else {
                    this.center = new Vect(hypotenuse.p1().x() - centerX, hypotenuse.p1().y() + centerY);
                }
            }
        }*/
    }
    @Override
    public String getName() {
        return gizmoName;
    }

    public double getLeftLimit() {
        return cx - (1 * scale);
    }

    public double getRightLimit() {
        return cx + (1 * scale);
    }

    public double getUpperLimit() {
        return cy - (1 * scale);
    }

    public double getLowerLimit() {
        return cy + (1 * scale);
    }
}