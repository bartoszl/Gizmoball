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

    private int scale;

    public TriangularBumper(double cx, double cy, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
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
    }

    public double getX() {
        return cx;
    }

    public double getY() {
        return cy;
    }

    @Override
    public List<LineSegment> getLines() {
        List<LineSegment> lines = new ArrayList<>();
        lines.add(sideOne);
        lines.add(sideTwo);
        lines.add(hypotenuse);
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        return null;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int getRotation() {
        return 0;
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

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void move(double x, double y) {

    }

    public int reservedArea() {
        return 1;
    }

    public void move(int cx, int cy) {

    }

    public void rotate() {
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
        }
    }

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

    public String getGizmoName() {
        return gizmoName;
    }
}