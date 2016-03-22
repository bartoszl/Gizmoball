package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.*;

/**
 * 
 * @author Kiril Rupasov, Bartosz Lewandowski
 *
 */
public class Flipper implements IFlipper {

    private boolean isLeft;
    private Color color;
    private Circle topCircle;
    private Circle bottomCircle;
    private List<LineSegment> lines;
    private Movement movement;
    private double rotationPerTick;
    private Angle currentRotation;
    private Vect origin;
    private String name;
    private int rotation;
    private List<Circle> lineCircles;
    
    private static final double RAD = 0.017;

    /**
     * Creates a flipper on a 4-cell space, with specific color and position.
     * For that purpose there are 4 Line Segment forming a rectangular, as well as
     * 2 Circles which round top and bottom of flipper
     *
     * @param cx coordinate of left upper cell which is used for flipper
     * @param cy coordinate of left upper cell which is used for flipper
     * @param isLeft specifies whether flipper is on a left or right side of space reserved
     */
    public Flipper(int cx, int cy, boolean isLeft, String name) {
        this.name = name;
        this.rotationPerTick = 15*RAD;
        this.rotation = 0;
        this.isLeft = isLeft;
        this.color = Color.YELLOW;
        this.currentRotation = Angle.ZERO;
        this.origin = new Vect((double)cx, (double)cy);
        this.movement = Movement.NONE;
        makeLines();
        makeCircles();
    }
    
    @Override
    public void move(double cx, double cy) {
        origin = new Vect(cx, cy);
        makeLines();
        makeCircles();
        int temp = rotation;
        rotation = 0;
        for(int i=0;i<temp;i++)
        	rotate();
    }
    
    @Override
    public void rotatePerTime(double time) {
        switch(movement) {
            case FORWARDS:
            	if(currentRotation.radians()<Angle.DEG_90.radians()){
            		double r = rotationPerTick * (time/0.05);
            		if(currentRotation.radians()+r>Angle.DEG_90.radians()){
            			r=Angle.DEG_90.radians()-currentRotation.radians();
            		}
            		currentRotation = new Angle(currentRotation.radians()+r);
	            	rotateByAngle(new Angle(r));
            	} else {
            		movement = Movement.NONE;
            	}
                break;
            case BACKWARDS:
            	if(currentRotation.radians()>Angle.ZERO.radians()){
            		double r = rotationPerTick * (time/0.05);
            		if(currentRotation.radians()-r<Angle.ZERO.radians())
            			r=currentRotation.radians();
            		currentRotation = new Angle(currentRotation.radians()-r);
            		rotateByAngle(Angle.ZERO.minus(new Angle(r)));
            	} else {
            		movement = Movement.NONE;
            	}
                break;
            default:
			break;
        }
    }

    @Override
    public double getAngSpeed(){
    	//if(Movement.NONE == movement) return 0;
    	double angSpeed = rotationPerTick*20;
    	if(isLeft)
    		angSpeed*=-1;
    	if(movement==Movement.BACKWARDS)
    		angSpeed*=-1;
    	return angSpeed;
    }

    @Override
    public void press() {
        movement = Movement.FORWARDS;
    }
    
    @Override
    public void release() {
        movement = Movement.BACKWARDS;
    }
    
    @Override
    public int getRotation() {
        return rotation;
    }
    
    @Override
    public void rotate() {
        rotateByAngle(Angle.DEG_90);
        swapCircles();
        rotation=(rotation+1)%4;
    }
    
    @Override
    public void setName(String name) {
    	this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
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
    public IFlipper.Movement getMovement() {
        return movement;
    }

    @Override
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    @Override
    public Circle getOriginCircle() {
        return topCircle;
    }

    @Override
    public Circle getEndCircle() {
        return bottomCircle;
    }

    @Override
    public Vect getOrigin() {
        return origin;
    }
    
    @Override
    public void setOrigin(Vect origin) {
        this.origin = origin;
    }

	@Override
	public boolean isLeft() {
		return isLeft;
	}
	
	@Override
	public List<LineSegment> getLines(){
		return lines;
	}
	
	@Override
	public List<Circle> getCircles(){
		List<Circle> l = new ArrayList<Circle>();
		l.add(topCircle);
		l.add(bottomCircle);
		l.addAll(lineCircles);
		return l;
	}
	
	@Override
	public Angle getCurrentRotation(){
		return currentRotation;
	}
	
	// Private Methods
	
	private void makeLines(){
    	double off = isLeft ? 0 : 30;
    	lines = new ArrayList<LineSegment>();
        lines.add(new LineSegment(origin.x()+off, origin.y()+5, origin.x()+off, origin.y()+35));
        lines.add(new LineSegment(origin.x()+off+10, origin.y()+5, origin.x()+off+10, origin.y()+35));
        makeLineCircles();
    }
    
    private void makeLineCircles(){
    	lineCircles = new ArrayList<Circle>();
        Circle line1a = new Circle(lines.get(0).p1().x(), lines.get(0).p1().y(), 0);
        lineCircles.add(line1a);
        Circle line1b = new Circle(lines.get(0).p2().x(), lines.get(0).p2().y(), 0);
        lineCircles.add(line1b);
        Circle line2a = new Circle(lines.get(1).p1().x(), lines.get(1).p1().y(), 0);
        lineCircles.add(line2a);
        Circle line2b = new Circle(lines.get(1).p2().x(), lines.get(1).p2().y(), 0);
        lineCircles.add(line2b);
    }
    
    private void makeCircles(){
    	double off = isLeft ? 0 : 30;
    	topCircle = new Circle(origin.x()+5+off, origin.y()+5, 5);
        bottomCircle = new Circle(origin.x()+off+5, origin.y()+35, 5);
    }
    
    private void swapCircles(){
    	Circle temp = bottomCircle;
        bottomCircle = topCircle;
        topCircle = temp;
    }
    
    private void rotateByAngle(Angle a) {
        //Flipper is left -> reverse rotation, otherwise -> do nothing
        a = isLeft ? Angle.ZERO.minus(a) : a;
        
        bottomCircle = Geometry.rotateAround(bottomCircle, topCircle.getCenter(), a);
        List<LineSegment> temp = new ArrayList<LineSegment>();
        for(LineSegment l : lines) {
        	temp.add(Geometry.rotateAround(l, topCircle.getCenter(), a));
        }
        lines = temp;
        List<Circle> tempc = new ArrayList<Circle>();
        for(Circle c : lineCircles){
        	tempc.add(Geometry.rotateAround(c, topCircle.getCenter(), a));
        }
        lineCircles = tempc;
    }
}
