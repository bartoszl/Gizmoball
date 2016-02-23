package model;

import physics.LineSegment;

import java.awt.*;

public class TriangularBumper implements iGizmo {
    private LineSegment sideOne;
    private LineSegment sideTwo;
    private LineSegment hypotenuse;
    private Color color;
    private String gizmoName;
    private int cx, cy, scale, rotation;


    public TriangularBumper(int cx, int cy, int rotation, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
        this.rotation = rotation;
        scale = 20;
        sideOne = new LineSegment(cx, cy, cx + (1* scale), cy);
        sideTwo = new LineSegment(cx, cy, cx, cy - (1 * scale));
        hypotenuse = new LineSegment(cx, cy - (1 * scale), cx + (1 * scale), cy);
        this.gizmoName = gizmoName;
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

    public void move(int cx, int cy) {}

    public void rotate() {}

    public String getName() {
        return gizmoName;
    }

    @Override
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

    @Override
    public int getX(){ return cx; }

    @Override
    public int getY(){ return cy; }

    public int getRotation(){ return rotation; }

    @Override
    public FlipperOrientation getOrientation() {
        return null;
    }

    public String getGizmoName() {
        return gizmoName;
    }
}