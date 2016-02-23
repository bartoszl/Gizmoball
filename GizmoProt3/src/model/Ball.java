package model;

import physics.Circle;
import physics.Vect;

import java.awt.*;

public class Ball implements IBall {
	private Vect velocity;
	private double radius;
	private double x;
	private double y;
	private Color color;
	private boolean stopped;
	
	private boolean absorbed;
	private boolean moving;
	
	public Ball(double xpos, double ypos, double xv, double yv){
		x =xpos;
		y =ypos;
		velocity = new Vect(xv, yv);
		color = Color.YELLOW;
		radius = 5;
		moving = true;
		absorbed = false;
		stopped = false;
	}

	public boolean getAbsorbed(){
		return absorbed;
	}

	public boolean stopped() {
		return stopped;
	}
	
	public boolean isAbsorbed(){
		return absorbed;
	}
	
	public void setAbsorbed(boolean b){
		absorbed = b;
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
		return new Circle(x, y, radius);
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
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setXY(double x, double y){
		this.y = y;
		this.x = x;
	}
}
