package model;

<<<<<<< HEAD
import physics.LineSegment;

import java.awt.*;
=======
import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
>>>>>>> master

public class TriangularBumper implements iGizmo {
    private LineSegment sideOne;
    private LineSegment sideTwo;
    private LineSegment hypotenuse;
    private Color color;
    private String gizmoName;
<<<<<<< HEAD
    private int cx, cy, scale, rotation;


    public TriangularBumper(int cx, int cy, int rotation, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
        this.rotation = rotation;
=======
    private int scale;
    private List<Circle> circles;
    private double cx, cy;
    private int[] x, y;

    public TriangularBumper(double cx, double cy, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
>>>>>>> master
        scale = 20;
        sideOne = new LineSegment(cx, cy, cx + (1* scale), cy);
        sideTwo = new LineSegment(cx, cy, cx, cy - (1 * scale));
        hypotenuse = new LineSegment(cx, cy - (1 * scale), cx + (1 * scale), cy);
        this.gizmoName = gizmoName;
<<<<<<< HEAD
=======
        circles = new ArrayList<>();
        x = new int[3];
        y = new int[3];

        circles = new ArrayList<>();
        Circle c1 = new Circle((double) cx, (double) cy, 0);
        Circle c2 = new Circle((double) cx + (1* scale), (double) cy, 0);
        Circle c3 = new Circle((double) cx, (double) cy - (1 * scale), 0);
        circles.add(c1);
        circles.add(c2);
        circles.add(c3);
        this.gizmoName = gizmoName;

        x[0] = (int) cx;
        x[1] = (int) cx + (1*scale);
        x[2] = (int) cx;

        y[0] = (int) cy;
        y[1] = (int) cy;
        y[2] = (int) (cy - (1*scale));
    }

    public int[] getXCoords() {
        return x;
    }

    public int[] getYCoords() {
        return y;
    }

    public List<Circle> getCircles() {
        return circles;
>>>>>>> master
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
<<<<<<< HEAD
    public int getX(){ return cx; }

    @Override
    public int getY(){ return cy; }

    public int getRotation(){ return rotation; }
=======
    public double getX(){ return cx; }

    @Override
    public double getY(){ return cy; }

    @Override
    public int getRotation() {
        return 0;
    }
>>>>>>> master

    @Override
    public FlipperOrientation getOrientation() {
        return null;
    }

    public String getGizmoName() {
        return gizmoName;
    }
}