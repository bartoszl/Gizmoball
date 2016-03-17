package model;

import physics.Angle;
import physics.Circle;

import java.awt.*;

public interface IFlipper {
    enum Position {
        VERTICAL, HORIZONTAL, BETWEEN
    }

    enum Movement {
        FORWARDS, BACKWARDS, NONE
    }

    public void rotatePerTick();

    public void press();

    public void release();

    public void rotate();

    public String getName();

    public int reservedArea();

    public Color getColor();

    public void setColor(Color color);

    public Circle getEndCircle();

    public Circle getOriginCircle();

    public void setLeft(Angle left);

    public Angle getLeft();

    public void setPosition(Position position);

    public Position getPosition();

    public Movement getMovement();

    public void setMovement(Movement movement);
    
    public boolean isLeft();
}
