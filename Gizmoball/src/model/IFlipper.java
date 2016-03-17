package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.Color;
import java.util.List;

public interface IFlipper {

    enum Movement {
        FORWARDS, BACKWARDS, NONE
    }
    
    /**
     * This method changes coordinates of flipper
     * @param cx new coordinate of left upper cell which is used for flipper
     * @param cy new coordinate of left upper cell which is used for flipper
     */
    public void move(double cx, double cy);

    public void rotatePerTime(double time);
    
    public double getAngSpeed();

    public void press();

    public void release();
    
    public int getRotation();

    public void rotate();
    
    public String getName();
    
    public void setName(String name);

    public Color getColor();

    public void setColor(Color color);

    public Circle getEndCircle();

    public Circle getOriginCircle();

    public Movement getMovement();

    public void setMovement(Movement movement);
    
    public boolean isLeft();
    
    public Vect getOrigin();
    
    public void setOrigin(Vect origin);
    
    public List<LineSegment> getLines();
    
    public List<Circle> getCircles();
}
