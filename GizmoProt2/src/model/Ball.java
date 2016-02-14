package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public class Ball implements IBall {
	private Vect velocity;
	private double radius;
	private double xpos;
	private double ypos;
	private Color color;
	
	private boolean moving;
	
	public Ball(double xpos, double ypos, double xv, double yv){
		this.xpos =xpos;
		this.ypos =ypos;
		velocity = new Vect(xv, yv);
		color = Color.YELLOW;
		radius = 10;
		moving = true;
	}
	
	public Vect getVelocity(){
		return velocity;
	}
	
	public void setVelocity(Vect v){
		velocity = v;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public Circle getCircle(){
		return new Circle(xpos, ypos, radius);
	}
	
	public boolean isMoving(){
		return moving;
	}
	
	public void setMoving(boolean b){
		moving = b;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}
}
