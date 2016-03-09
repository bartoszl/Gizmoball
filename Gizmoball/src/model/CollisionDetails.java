package model;

import physics.Vect;

public class CollisionDetails {
	private double time;
	private Vect velocity;
	
	public CollisionDetails(double t, Vect v){
		this.time = t;
		this.velocity = v;
	}
	
	public double getTime(){
		return time;
	}
	
	public Vect getVelocity(){
		return velocity;
	}
}
