package model;

import java.awt.Color;
import java.util.Observable;

import physics.*;

public class CircularBumper extends Observable implements iGizmo {
    private Circle circle;
    private Color color;
    private double radius;
    private String gizmoName;
    private double cx, cy;

    public CircularBumper(double cx, double cy, double r, String gizmoName) {
        this.cx = cx;
        this.cy = cy;
        this.radius = r;
        this.gizmoName = gizmoName;
        circle = new Circle(cx, cy, this.radius);
    }

    public Circle getCircle() {
        return circle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move(int cx, int cy) {
        circle = new Circle(cx, cy, radius);
    }

    //90 degrees clockwise rotation
    public void rotate() {

    }

    public String getName() {
        return gizmoName;
    }

    public int reservedArea() {
        return 1;
    }

    public String getGizmoName() {
        return gizmoName;
    }

    public Vect getCentre() {
        return circle.getCenter();
    }

    public double getCentreX() {
        return cx;
    }

    public double getCentreY() {
        return cy;
    }

    public double getRadius() {
        return radius;
    }
}