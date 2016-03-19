package model;

import physics.*;

/**
 * 
 * @author Bartosz Lewandowski
 *
 */
public class CollisionDetails {
	private double time;
	private Vect velocity;
	private boolean absorbed;
	private Bumper bumper;
	
	/**
	 * 
	 * @param t -> double, represents time until collision.
	 * @param v -> Vect, representing velocity of the ball after collision.
	 * @param abs -> boolean, represents whether the ball collided with an absorber.
	 * @param bumper -> Bumper, representing a Bumper that the ball collided with.
	 */
	public CollisionDetails(double t, Vect v, boolean abs, Bumper bumper){
		this.time = t;
		this.velocity = v;
		this.absorbed = abs;
		this.bumper = bumper;
	}
	
	/**
	 * Getter method for time.
	 * 
	 * @return double, representing time until collision.
	 */
	public double getTime(){
		return time;
	}
	
	/**
	 * Getter method for velocity.
	 * 
	 * @return Vect, representing velocity of a ball after collision.
	 */
	public Vect getVelocity(){
		return velocity;
	}
	
	/**
	 * Getter method for absorbed.
	 * 
	 * @return boolean, representing whether the ball hit an absorber.
	 */
	public boolean getAbsorbed(){
		return absorbed;
	}
	
	/**
	 * Getter method for bumper.
	 * 
	 * @return Bumper, represents the Bumper that the ball collided with.
	 */
	public Bumper getBumper() {
		return bumper;
	}

	/**
	 * Setter method for bumper.
	 * 
	 * @param bumper -> Bumper, representing the Bumper that the ball collided with.
	 */
	public void setBumper(Bumper bumper) {
		this.bumper = bumper;
	}

}
