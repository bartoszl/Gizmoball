package model;

public class Model {
	private Absorber abs;
	private Ball ball;
	//private Walls gws;
	
	public Model(){
		ball = new Ball(25,25,100,100);
		//walls = new Walls(0,0,500,500);
		abs = new Absorber(0,400, 450,450);
	}
	
	public void moveBall(){
		
	}
	
	public Ball moveBallForTime(Ball ball, double time){
		return null;
	}

}
