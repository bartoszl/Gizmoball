package model;

import physics.Circle;
import physics.Vect;
import java.awt.Color;

/**
 * 
 * @author Bartosz Lewandowski
 *
 * Part of the Gizmoball model representing a Ball. 
 * Implements IBall interface. 
 */

public class Ball implements IBall{
	private Vect velocity;
	private double x, y, r, sx, sy;
	private Color color;
	private boolean moving, absorbed;
	private static final double L=20;
	private String name;
	
	/**
	 * Constructor for Ball object. Initially sets color to Yellow,
	 * Radius to 5px, marks ball as moving and sets the ball as 
	 * not absorbed.
	 *
	 * @param name -> string, representing the name of the ball
	 * @param x -> double, representing Ball's position along x axis
	 * @param y -> double, representing Ball's position along y axis
	 * @param xv -> double, representing velocity along x axis
	 * @param yv -> double, representing velocity along y axis
	 */
	public Ball(String name, double x, double y, double xv, double yv){
		this.x = x+L/2;
		this.y = y+L/2;
        this.sx = this.x;
        this.sy = this.y;
		this.velocity = new Vect(xv, yv);
		this.color = Color.MAGENTA;
		this.r = 5;
		moving = true;
		absorbed = false;
		this.name = name;
	}
	
	@Override
	public Vect getVelocity() {
		return velocity;
	}
	
	@Override
	public void setVelocity(Vect v) {
		velocity = v;
	}
	
	@Override
	public void setVelocity(double vx, double vy) {
		velocity = new Vect(vx, vy);
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	@Override
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean isMoving() {
		return moving;
	}
	
	@Override
	public void setMoving(boolean b) {
		moving = b;
	}
	
	@Override
	public boolean isAbsorbed() {
		return absorbed;
	}
	
	@Override
	public void setAbsorbed(boolean b) {
		absorbed = b;
	}

	@Override
	public double getRadius() {
		return r;
	}

	@Override
	public void setRadius(double r) {
		this.r = r;
	}
	
	@Override
	public Circle getCircle() {
		return new Circle(x,y,r);
	}
    
    @Override
    public void move(double x, double y){
    	this.x = x+L/2;
    	this.y = y+L/2;
    }
    
    @Override
	public String getName() {
		return name;
	}
	
	@Override
    public void reset(){
        this.x = this.sx;
        this.y = this.sy;
    }
}
