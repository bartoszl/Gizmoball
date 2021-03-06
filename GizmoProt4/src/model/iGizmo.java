package model;

import java.awt.Color;

/**
 * Values in gizmos stored in Ls.
 * @author zveket
 *
 */

public interface iGizmo {
    public Color getColor();

    public void setColor(Color color);

    public int reservedArea();

    public void move(int cx, int cy);

    public void rotate();

    public String getName();

    public double getLeftLimit();

    public double getRightLimit();

    public double getUpperLimit();

    public double getLowerLimit();
}
