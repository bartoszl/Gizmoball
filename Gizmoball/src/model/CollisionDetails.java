package model;

import physics.*;

public class CollisionDetails {
	private double time;
	private Vect velocity;
	private boolean absorbed;
	private boolean fired;
	private Bumper bumper;
	
	public CollisionDetails(double t, Vect v, boolean abs, Bumper bumper){
		this.time = t;
		this.velocity = v;
		this.absorbed = abs;
		this.bumper = bumper;
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

	public Bumper getBumper() {
		return bumper;
	}

	public void setBumper(Bumper bumper) {
		this.bumper = bumper;
	}

	public boolean isFired() {
		return fired;
	}

	public void setFired(boolean fired) {
		this.fired = fired;
	}
}
