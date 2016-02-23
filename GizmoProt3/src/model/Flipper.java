package model;

import physics.LineSegment;
import physics.Vect;

import java.awt.*;

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

    private Vect pivotPoint;
    private CircularBumper c1, c2;
    private int scale;
    private FlipperOrientation orientation;
    private String gizmoName;

    public Flipper(int cx, int cy, FlipperOrientation orientation, String gizmoName) {
        this.gizmoName = gizmoName;
        scale = 20;
        this.cx = cx;
        this.cy = cy;
        this.orientation = orientation;
        this.pivotPoint = new Vect(cx, cy);
        if(orientation == FlipperOrientation.LEFT) {
            leftSide = new LineSegment(cx, cy, cx, cy + (1.75 * scale));
            rightSide = new LineSegment(cx + (0.5 * scale), cy, cx + (0.5 * scale), cy + (1.75 * scale));
            c1 = new CircularBumper(cx + (0.25 * scale), cy + (0.25 * scale), (0.25 * 20), "N/A");
            c2 = new CircularBumper(cx + (0.25 * scale), cy + (1.75 * scale), (0.25 * 20), "N/A");
        } else {
            c1 = new CircularBumper(cx - (0.25 * scale), cy + (0.25 * scale), (0.25 * 20), "N/A");
            c2 = new CircularBumper(cx - (0.25 * scale), cy  +(1.75 * scale), (0.25 * 20), "N/A");
        }

    }

    public void rotate() {
    }

    public String getName() {
        return gizmoName;
    }

    public double getLeftLimit() {
        if(orientation == FlipperOrientation.LEFT) {
            return cx;
        } else {
            return cx - (2 * scale);
        }
    }

    public double getRightLimit() {
        if(orientation == FlipperOrientation.LEFT) {
            return cx + (2 * scale);
        } else {
            return cx;
        }
    }

    public double getUpperLimit() {
        return cy;
    }

    public double getLowerLimit() {
        return cy + (2 * scale);
    }

    @Override
<<<<<<< HEAD
    public int getX() {
=======
    public double getX() {
>>>>>>> master
        return cx;
    }

    @Override
<<<<<<< HEAD
    public int getY() {
=======
    public double getY() {
>>>>>>> master
        return cy;
    }

    @Override
    public int getRotation() {
        return 0;
    }

    public void trigger() {}

    public int reservedArea() {
        return 4;
    }

    @Override
    public void move(int cx, int cy) {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getGizmoName() {
        return gizmoName;
    }

    public FlipperOrientation getOrientation(){
        return orientation;
    }
}
