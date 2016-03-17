package model;

import physics.Angle;
import physics.Circle;
import physics.LineSegment;

import java.awt.Color;
import java.util.List;

public interface IFlipper {

    enum Movement {
        FORWARDS, BACKWARDS, NONE
    }

    public void rotatePerTime(double time);

    public void press();

    public void release();

    public void rotate();

    public Color getColor();

    public void setColor(Color color);

    public Circle getEndCircle();

    public Circle getOriginCircle();

    public Movement getMovement();

    public void setMovement(Movement movement);
    
    public boolean isLeft();
    
    public List<LineSegment> getLines();
}
