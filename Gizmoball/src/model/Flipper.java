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
    private Position position;
    private Angle rotationPerTick;
    private Angle leftToRotate;
    private Vect origin;
    private String name;
    private int rotation;

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
        this.rotationPerTick = Angle.DEG_45;
        this.rotation = 0;
        this.isLeft = isLeft;
        this.color=color;
        origin = new Vect((double)cx, (double)cy);
        lines = new ArrayList<>();
        /** o **/
        topCircle = new Circle(origin.x()+5+off, origin.y()+5, 5);
        /**| **/
        lines.add(new LineSegment(origin.x()+off, origin.y()+5, origin.x()+off, origin.y()+35));
        /**  |**/
        lines.add(new LineSegment(origin.x()+off+10, origin.y()+5, origin.x()+off+10, origin.y()+35));
        /** o **/
        bottomCircle = new Circle(origin.x()+off+5, origin.y()+35, 5);
        movement = Movement.NONE;
        position = Position.VERTICAL;

    }
    
    public double getAngSpeed(){
    	return rotationPerTick.radians()/0.05;
    }

    /**
     * This method changes coordinates of flipper
     * @param cx new coordinate of left upper cell which is used for flipper
     * @param cy new coordinate of left upper cell which is used for flipper
     */
    public void move(double cx, double cy) {
    	
        double off = isLeft ? 0 : 30;
        origin = new Vect(cx, cy);
        topCircle = new Circle(origin.x()+5+off, origin.y()+5, 5);
        List<LineSegment> newLines = new ArrayList<>();
        newLines.add(new LineSegment(cx+off, cy, cx+off+10, cy));
        newLines.add(new LineSegment(cx+off, cy, cx+off, cy+40));
        newLines.add(new LineSegment(cx+off+10, cy, cx+off+10, cy+40));
        newLines.add(new LineSegment(cx+off, cy+40, cx+off+10, cy+40));
        lines = newLines;
        bottomCircle = new Circle(origin.x()+off+5, origin.y()+35, 5);
        
    }

    public void rotatePerTick() {
        switch(movement) {
            case FORWARDS:
                //moving forwards
                if(position != Position.HORIZONTAL) {
                    rotateInRun(rotationPerTick);
                    if(position == Position.VERTICAL){
                    	position = Position.BETWEEN;
                    } else 
                    if(position == Position.BETWEEN){
                    	position = Position.HORIZONTAL;
                    	movement = Movement.NONE;
                    }
                }
                break;
            case BACKWARDS:
                if(position != Position.VERTICAL) {
                	rotateInRun(Angle.ZERO.minus(rotationPerTick));
                	if(position == Position.HORIZONTAL){
                		position = Position.BETWEEN;
                	} else
                	if(position == Position.BETWEEN){
                		position = Position.VERTICAL;
                		movement = Movement.NONE;
                	}
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
        for(LineSegment l : lines) {
            l = Geometry.rotateAround(l, topCircle.getCenter(), a);
        }
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
    public Angle getLeft() {
        return leftToRotate;
    }

    /**
     * @param left new angle left for rotation
     */
    public void setLeft(Angle left) {
        leftToRotate = left;
    }

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
		return l;
	}
}
