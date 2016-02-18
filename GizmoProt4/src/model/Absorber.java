package model;

import physics.LineSegment;

public class Absorber implements iAbsorber {

    private String absorbderName;
    private double leftX, rightX, topY, bottomY;
    private LineSegment left, right, top, bottom;

    public Absorber(String absorbderName, double leftX, double rightX, double topY, double bottomY) {
        this.absorbderName = absorbderName;
        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.bottomY = bottomY;
    }

    public String getName() {
        return absorbderName;
    }

    public double getLeftXCoordinate() {
        return leftX;
    }

    public double getRightXCoordinate() {
        return rightX;
    }

    public double getTopYCoordinate() {
        return topY;
    }

    public double getBottomYCoordinate() {
        return bottomY;
    }
}
