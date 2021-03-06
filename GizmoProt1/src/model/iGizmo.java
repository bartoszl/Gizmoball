package model;

import java.awt.Color;

import physics.Vect;

/**
 * Values in gizmos stored in Ls.
 *
 */

public interface iGizmo{
	
	public Color getColor();
	public void setColor(Color color);
	public int reservedArea();
	public void move(int cx, int cy);
	public void rotate();
	public Vect getCenter();
}
