package model;

import java.awt.Color;

import physics.*;

public class Flipper implements iGizmo {
    private boolean isLeft;
    private boolean isVertical;
    private double cx;
    private double cy;
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

    public Flipper(double cx, double cy, FlipperOrientation orientation, String gizmoName) {
        scale = 20;
        this.cx = cx;
        this.cy = cy;
        this.gizmoName = gizmoName;
        this.orientation = orientation;
        this.pivotPoint = new Vect(cx, cy);
        this.center = new Vect(cx + (1*scale), cy + (1*scale));
        if(orientation == FlipperOrientation.LEFT) {
//            leftSide = new LineSegment(cx, cy, cx, cy + (1.75 * scale));
//            rightSide = new LineSegment(cx + (0.5 * scale), cy, cx + (0.5 * scale), cy + (1.75 * scale));
//            c1 = new CircularBumper(cx + (0.25 * scale), cy + (0.25 * scale), (0.25 * 20), "N/A");
//            c2 = new CircularBumper(cx + (0.25 * scale), cy + (1.75 * scale), (0.25 * 20), "N/A");
            leftSide = new LineSegment(cx, cy + (0.25*scale), cx, cy + (1.75*scale));
            rightSide = new LineSegment(cx + (0.5*scale), cy + (0.25*scale), cx + (0.5*scale), cy + (1.75*scale));
            c1 = new CircularBumper(cx + (0.25*scale), leftSide.getP1().getY(), (0.25*scale), "N/A");
            c2 = new CircularBumper(cx + (0.25*scale), leftSide.getP2().getY(), (0.25*scale), "N/A");
        } else {
//            c1 = new CircularBumper(cx - (0.25 * scale), cy + (0.25 * scale), (0.25 * 20), "N/A");
//            c2 = new CircularBumper(cx - (0.25 * scale), cy  +(1.75 * scale), (0.25 * 20), "N/A");
            leftSide = new LineSegment(cx + (1.5*scale), cy + (0.25*scale), cx + (1.5*scale), cy + (1.75*scale));
            rightSide = new LineSegment(cx + (2*scale), cy + (0.25*scale), cx + (2*scale), cy + (1.75*scale));
            c1 = new CircularBumper(leftSide.getP1().getX() + (0.25*scale), leftSide.getP1().getY(), (0.25*scale), "N/A");
            c2 = new CircularBumper(rightSide.getP2().getX() - (0.25*scale), rightSide.getP2().getY(), (0.25*scale), "N/A");
        }
    }

    public double getX() {
        return cx;
    }

    public double getY() {
        return cy;
    }

    public void rotate() {
        Angle a = Angle.DEG_90;
        leftSide = Geometry.rotateAround(leftSide, center, a);
        rightSide = Geometry.rotateAround(rightSide, center, a);
        Circle innerC1 = Geometry.rotateAround(c1.getCircle(), center, a);
        Circle innerC2 = Geometry.rotateAround(c2.getCircle(), center, a);
        c1 = new CircularBumper(innerC1.getCenter().getX(), innerC1.getCenter().getY(), innerC1.getRadius(), "N/A");
        c2 = new CircularBumper(innerC2.getCenter().getX(), innerC2.getCenter().getY(), innerC2.getRadius(), "N/A");
    }

    public FlipperOrientation getOrientation() {
        return orientation;
    }

    public String getName() {
        return gizmoName;
    }

    public double getLeftLimit() {
        return cx;
    }

    public double getRightLimit() {
        return cx + (2 * scale);
    }

    public double getUpperLimit() {
        return cy;
    }

    public double getLowerLimit() {
        return cy + (2 * scale);
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

    public CircularBumper getC1() {
        return c1;
    }

    public CircularBumper getC2() {
        return c2;
    }

    public LineSegment getLeftSide() {
        return leftSide;
    }

    public LineSegment getRightSide() {
        return rightSide;
    }
}
