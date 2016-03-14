package model;

import physics.*;

public class CollisionDetails {
	private double time;
	private Vect velocity;
	private boolean absorbed;
	
	public CollisionDetails(double t, Vect v, boolean abs){
		this.time = t;
		this.velocity = v;
		this.absorbed = abs;
	}
	
	public double getTime(){
		return time;
	}
	
	public Vect getVelocity(){
		return velocity;
	}
	
	public boolean getAbsorbed(){
		return absorbed;
	}
}
