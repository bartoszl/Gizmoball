package model;

import java.awt.Color;

import physics.*;

public class SquareBumper implements iGizmo {
    private Color color;
    private LineSegment sideOne;
    private LineSegment sideTwo;
    private LineSegment sideThree;
    private LineSegment sideFour;
    private String gizmoName;
    private int scale;

    public SquareBumper(double cx, double cy, String gizmoName) {
        scale = 20;
        this.gizmoName = gizmoName;
        sideOne = new LineSegment(cx, cy, cx+(1 * scale), cy);
        sideTwo = new LineSegment(cx, cy, cx, cy+(1 * scale));
        sideThree = new LineSegment(cx, cy+1, cx+1, cy+(1 * scale));
        sideFour = new LineSegment(cx+1, cy, cx+1, cy+(1 * scale));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move(int cx, int cy) {
        sideOne = new LineSegment(cx, cy, cx+1, cy);
        sideTwo = new LineSegment(cx, cy, cx, cy+1);
        sideThree = new LineSegment(cx, cy+1, cx+1, cy+1);
        sideFour = new LineSegment(cx+1, cy, cx+1, cy+1);
    }

    public void rotate() {

    }

    public int reservedArea() {
        return 1;
    }

    public String getGizmoName() {
        return gizmoName;
    }
}
