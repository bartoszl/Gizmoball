package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import physics.*;

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
     * @param color specifies the color of flipper
     */
    public Flipper(int cx, int cy, boolean isLeft, Color color, String name) {
        this.name = name;
        double off = isLeft ? 0 : 30;
        this.rotationPerTick = 15*RAD;
        this.rotation = 0;
        this.isLeft = isLeft;
        this.color=color;
        this.currentRotation = Angle.ZERO;
        origin = new Vect((double)cx, (double)cy);
        lines = new ArrayList<LineSegment>();
        /** o **/
        topCircle = new Circle(origin.x()+5+off, origin.y()+5, 5);
        /**| **/
        lines.add(new LineSegment(origin.x()+off, origin.y()+5, origin.x()+off, origin.y()+35));
        /**  |**/
        lines.add(new LineSegment(origin.x()+off+10, origin.y()+5, origin.x()+off+10, origin.y()+35));
        /** o **/
        bottomCircle = new Circle(origin.x()+off+5, origin.y()+35, 5);
        movement = Movement.NONE;
        
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
    
    public double getAngSpeed(){
    	if(isLeft)
    		return -rotationPerTick*20;
    	else
    		return rotationPerTick*20;
    }

    /**
     * This method changes coordinates of flipper
     * @param cx new coordinate of left upper cell which is used for flipper
     * @param cy new coordinate of left upper cell which is used for flipper
     */
    public void move(double cx, double cy) {
    	
        double off = isLeft ? 0 : 30;
        origin = new Vect(cx, cy);
        lines = new ArrayList<LineSegment>();
        bottomCircle = new Circle(origin.x()+off+5, origin.y()+35, 5);
        topCircle = new Circle(origin.x()+5+off, origin.y()+5, 5);
        /**| **/
        lines.add(new LineSegment(origin.x()+off, origin.y()+5, origin.x()+off, origin.y()+35));
        /**  |**/
        lines.add(new LineSegment(origin.x()+off+10, origin.y()+5, origin.x()+off+10, origin.y()+35));
        /** o **/
        bottomCircle = new Circle(origin.x()+off+5, origin.y()+35, 5);
        
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

    public void rotatePerTime(double time) {
        switch(movement) {
            case FORWARDS:
            	if(currentRotation.radians()<Angle.DEG_90.radians()){
            		double r = rotationPerTick * (time/0.05);
            		if(currentRotation.radians()+r>Angle.DEG_90.radians()){
            			r=Angle.DEG_90.radians()-currentRotation.radians();
            		}
            		currentRotation = new Angle(currentRotation.radians()+r);
	            	rotateInRun(new Angle(r));
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
            		rotateInRun(Angle.ZERO.minus(new Angle(r)));
            	} else {
            		movement = Movement.NONE;
            	}
                break;
        }
    }

    /**
     * This method rotates the flipper at a given Angle,
     * also considering flipper's position (Left or Right)
     * @param a Angle of rotation
     */
    public void rotateInRun(Angle a) {
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

    /**
     * @return color of flipper
     */
    public Color getColor() {
        return color;
    }

    public int getRotation() {
        return rotation;
    }

    public void press() {
        movement = Movement.FORWARDS;
    }

    public void release() {
        movement = Movement.BACKWARDS;
    }

    public void rotate() {
        rotateInRun(Angle.DEG_90);
        //swap circles
        Circle temp = bottomCircle;
        bottomCircle = topCircle;
        topCircle = temp;
        if(rotation+90 == 360) rotation=0;
        else rotation += 90;
        switch(rotation){
            case 0: lines = getVerticalFlipperLines(isLeft ? 0 : 30);
                    break;
            case 90: lines = getHorizontalFlipperLines(0);
                    break;
            case 180: lines = getVerticalFlipperLines(isLeft ? 30 : 0);
                break;
            case 270: lines = getHorizontalFlipperLines(isLeft ? 0 : 30);
                break;
            default: break;
        }
    }

    private List<LineSegment> getVerticalFlipperLines(int off){ // Left: 0, Right: 30
        List<LineSegment> newLines = new ArrayList<>();
        /** - **/
        newLines.add(new LineSegment(origin.x()+off, origin.y()+5, origin.x()+off+10, origin.y()+5));
        /**| **/
        newLines.add(new LineSegment(origin.x()+off, origin.y()+5, origin.x()+off, origin.y()+35));
        /**  |**/
        newLines.add(new LineSegment(origin.x()+off+10, origin.y()+5, origin.x()+off+10, origin.y()+35));
        /** _ **/
        newLines.add(new LineSegment(origin.x()+off, origin.y()+35, origin.x()+off+10, origin.y()+35));
        return newLines;
    }

    private List<LineSegment> getHorizontalFlipperLines(int off){ // Top: 0, Bottom0: 30
        List<LineSegment> newLines = new ArrayList<>();
        /** - **/
        newLines.add(new LineSegment(origin.x()+5, origin.y()+off, origin.x()+5, origin.y()+off+10));
        /**| **/
        newLines.add(new LineSegment(origin.x()+5, origin.y()+off, origin.x()+35, origin.y()+off));
        /**  |**/
        newLines.add(new LineSegment(origin.x()+5, origin.y()+off+10, origin.x()+35, origin.y()+off+10));
        /** _ **/
        newLines.add(new LineSegment(origin.x()+35, origin.y()+off, origin.x()+35, origin.y()+off+10));
        return newLines;
    }

    public String getName() {
        return name;
    }

    /**
     * @param color new color of flipper
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return current direction of flippers' rotation
     */
    public IFlipper.Movement getMovement() {
        return movement;
    }

    /**
     * @param movement new direction of flippers' rotation
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    /**
     * @return position of flipper (VERTICAL, HORIZONTAL or BETWEEN)
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position new position of flipper (VERTICAL, HORIZONTAL or BETWEEN)
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return current angle left for rotation
     */
    

    /**
     * @return center of top circle of flipper
     */
    public Circle getOriginCircle() {
        return topCircle;
    }

    /**
     * @return center of end circle of flipper
     */
    public Circle getEndCircle() {
        return bottomCircle;
    }

    /**
     * @return origin
     */
    public Vect getOrigin() {
        return origin;
    }

    public void setOrigin(Vect origin) {
        this.origin = origin;
    }

	@Override
	public boolean isLeft() {
		return isLeft;
	}
	
	public List<LineSegment> getLines(){
		return lines;
	}
	
	public List<Circle> getCircles(){
		List<Circle> l = new ArrayList<Circle>();
		l.add(topCircle);
		l.add(bottomCircle);
		l.addAll(lineCircles);
		return l;
	}
}
