package model;

public class Ball implements iBall{

    private double xCoordinate, yCoordinate, radius, xVelocity, yVelocity;
    private String name;

    public Ball(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, String name) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.radius = 2.5;
        this.name = name;
    }

    public double getXCoordinate() {
        return yCoordinate;
    }

    public double getYCoordinate() {
        return xCoordinate;
    }

    public double getRadius() {
        return radius;
    }

    public String getName() {
        return name;
    }

    public double getLeftLimit() {
        return xCoordinate - radius;
    }

    public double getRightLimit() {
        return xCoordinate + radius;
    }

    public double getUpperLimit() {
        return yCoordinate - radius;
    }

    public double getLowerLimit() {
        return yCoordinate + radius;
    }
}
