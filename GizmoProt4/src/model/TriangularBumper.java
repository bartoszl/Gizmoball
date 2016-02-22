package model;

import java.awt.Color;
import java.text.DecimalFormat;

import physics.*;

public class TriangularBumper implements iGizmo {
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
        hypotenuse = new LineSegment(sideTwo.getP2().getX(), sideTwo.getP2().getY(), sideOne.getP2().getX(), sideOne.getP2().getY());
        this.gizmoName = gizmoName;
        double centerX = Math.abs((hypotenuse.getP1().getX() - hypotenuse.getP2().getX())/ 2);
        double centerY = Math.abs((hypotenuse.getP1().getY() - hypotenuse.getP2().getY())/ 2);
        if(hypotenuse.getP1().getY() > hypotenuse.getP2().getY()) {
            if(hypotenuse.getP1().getX() < hypotenuse.getP2().getX()) {
                this.center = new Vect(Math.abs(hypotenuse.getP1().getX() + centerX), Math.abs(hypotenuse.getP1().getY() - centerY));
            } else {
                this.center = new Vect(Math.abs(hypotenuse.getP1().getX() - centerX), Math.abs(hypotenuse.getP1().getY() - centerY));
            }
        } else {
            if(hypotenuse.getP1().getX() < hypotenuse.getP2().getX()) {
                if(hypotenuse.getP1().getY() < 0) {
                    this.center = new Vect(hypotenuse.getP1().getX() + centerX, centerY);
                } else {
                    this.center = new Vect(hypotenuse.getP1().getX() + centerX, hypotenuse.getP1().getY() + centerY);
                }
            } else {
                if(hypotenuse.getP1().getY() < 0) {
                    this.center = new Vect(hypotenuse.getP1().getX() - centerX, centerY);
                } else {
                    this.center = new Vect(hypotenuse.getP1().getX() - centerX, hypotenuse.getP1().getY() + centerY);
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

    public Color getColor() {
        return color;
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

    public int reservedArea() {
        return 1;
    }

    public void move(int cx, int cy) {

    }

    public void rotate() {
        Angle a = Angle.DEG_90;
        sideOne = Geometry.rotateAround(sideOne, center, a);
        sideTwo = Geometry.rotateAround(sideTwo, center, a);
        hypotenuse = new LineSegment(sideTwo.getP2().getX(), sideTwo.getP2().getY(), sideOne.getP2().getX(), sideOne.getP2().getY());
        double centerX = Math.abs((hypotenuse.getP1().getX() - hypotenuse.getP2().getX())/ 2);
        double centerY = Math.abs((hypotenuse.getP1().getY() - hypotenuse.getP2().getY())/ 2);
        if(hypotenuse.getP1().getY() > hypotenuse.getP2().getY()) {
            if(hypotenuse.getP1().getX() < hypotenuse.getP2().getX()) {
                this.center = new Vect(Math.abs(hypotenuse.getP1().getX() + centerX), Math.abs(hypotenuse.getP1().getY() - centerY));
            } else {
                this.center = new Vect(Math.abs(hypotenuse.getP1().getX() - centerX), Math.abs(hypotenuse.getP1().getY() - centerY));
            }
        } else {
                if(hypotenuse.getP1().getX() < hypotenuse.getP2().getX()) {
                    if(hypotenuse.getP1().getY() < 0) {
                        this.center = new Vect(hypotenuse.getP1().getX() + centerX, centerY);
                    } else {
                        this.center = new Vect(hypotenuse.getP1().getX() + centerX, hypotenuse.getP1().getY() + centerY);
                    }
                } else {
                    if(hypotenuse.getP1().getY() < 0) {
                        this.center = new Vect(hypotenuse.getP1().getX() - centerX, centerY);
                    } else {
                        this.center = new Vect(hypotenuse.getP1().getX() - centerX, hypotenuse.getP1().getY() + centerY);
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