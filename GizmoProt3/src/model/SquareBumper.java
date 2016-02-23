package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SquareBumper implements iGizmo {
    private Color color;
    private LineSegment sideOne;
    private LineSegment sideTwo;
    private LineSegment sideThree;
    private LineSegment sideFour;
    private String gizmoName;
    private int scale, cx, cy;
    private List<Circle> circles;

    public SquareBumper(int cx, int cy, String gizmoName) {
        scale = 20;
        this.gizmoName = gizmoName;
        this.cx = cx;
        this.cy = cy;
        sideOne = new LineSegment(cx, cy, cx+(1 * scale), cy);
        sideTwo = new LineSegment(cx, cy, cx, cy+(1 * scale));
        sideThree = new LineSegment(cx, cy+(1*scale), cx+(1*scale), cy+(1 * scale));
        sideFour = new LineSegment(cx+(1*scale), cy, cx+(1*scale), cy+(1 * scale));
        circles = new ArrayList<>();
        Circle c1 = new Circle((double) cx, (double) cy, 0); // Circle at sideOne P1
        Circle c2 = new Circle((double) cx+(1 * scale), (double) cy, 0); // Circle at sideOne P2
        Circle c3 = new Circle((double) cx, (double) cy+(1 * scale), 0); // Circle at sideTwo P2
        Circle c4 = new Circle((double) cx+(1*scale), (double) cy+(1 * scale), 0); // Circle at sideThree P2
        circles.add(c1);
        circles.add(c2);
        circles.add(c3);
        circles.add(c4);
    }

    public List<Circle> getCircles() {
        return circles;
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

    public void rotate() {}

    public String getName() {
        return gizmoName;
    }

    @Override
    public double getLeftLimit() {
        return cx - (1 * scale);
    }

    @Override
    public double getRightLimit() {
        return cx + (1 * scale);
    }

    @Override
    public double getUpperLimit() {
        return cy - (1 * scale);
    }

    @Override
    public double getLowerLimit() {
        return cy + (1 * scale);
    }

    public LineSegment getSideOne() { return sideOne; }
    public LineSegment getSideTwo() { return sideTwo; }
    public LineSegment getSideThree() { return sideThree; }
    public LineSegment getSideFour() { return sideFour; }

    public double getX(){ return cx; }

    public double getY(){ return cy; }

    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public FlipperOrientation getOrientation() {
        return null;
    }

    public int reservedArea() {
        return 1;
    }

    public String getGizmoName() {
        return gizmoName;
    }
}
