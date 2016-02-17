package model;

import java.awt.Color;

import physics.*;

public class TriangularBumper implements iGizmo {
    private LineSegment sideOne;
    private LineSegment sideTwo;
    private LineSegment hypotenuse;
    private Color color;
    private String gizmoName;
    private double cx, cy;

    private int scale;


    public TriangularBumper(double cx, double cy, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
        scale = 20;
        sideOne = new LineSegment(cx, cy, cx + (1* scale), cy);
        sideTwo = new LineSegment(cx, cy, cx, cy - (1 * scale));
        hypotenuse = new LineSegment(cx, cy - (1 * scale), cx + (1 * scale), cy);
        this.gizmoName = gizmoName;
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

    }

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

    public String getGizmoName() {
        return gizmoName;
    }
}