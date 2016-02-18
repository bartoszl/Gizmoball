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
    private Vect center;

    private int scale;


    public TriangularBumper(double cx, double cy, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
        scale = 20;
        sideOne = new LineSegment(cx, cy, cx + (1* scale), cy);
        sideTwo = new LineSegment(cx, cy, cx, cy - (1 * scale));
        hypotenuse = new LineSegment(cx, cy - (1 * scale), cx + (1 * scale), cy);
        this.gizmoName = gizmoName;
        this.center = new Vect(cx+0.5, cy+0.5);
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
        Geometry.rotateAround(sideOne, center, a);
        Geometry.rotateAround(sideTwo, center, a);
        Geometry.rotateAround(hypotenuse, center, a);
        System.out.println(sideOne.getP1().getX());
        System.out.println(sideOne.getP1().getY());
        System.out.println("Side one: X: " + sideOne.getP1().getX() + " Y: " + sideOne.getP1().getY());
        System.out.println("Side two: X: " + sideTwo.getP1().getX() + " Y: " + sideTwo.getP1().getY());
        System.out.println("C: X: " + hypotenuse.getP1().getX() + " Y: " + hypotenuse.getP1().getX());
        System.out.println("P2");
        System.out.println("Side one: X: " + sideOne.getP2().getX() + " Y: " + sideOne.getP2().getY());
        System.out.println("Side two: X: " + sideTwo.getP2().getX() + " Y: " + sideTwo.getP2().getY());
        System.out.println("C: X: " + hypotenuse.getP2().getX() + " Y: " + hypotenuse.getP2().getX());
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