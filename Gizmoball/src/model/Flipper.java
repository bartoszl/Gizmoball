package model;

import java.awt.Color;
import java.util.Observable;

import physics.*;

public class Flipper extends Observable implements IFlipper {

    private boolean isLeft;
    private Color color;
    private Circle topCircle;
    private Circle bottomCircle;
    private LineSegment topSide;
    private LineSegment leftSide;
    private LineSegment rightSide;
    private LineSegment bottomSide;
    private static Movement movement;
    private Position position;
    private Angle rotation;
    private Angle leftToRotate;
    private Vect centerOfRotation;
    private Vect origin;
    private String name;

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
        leftToRotate = Angle.DEG_90;
        double off = isLeft ? 0 : 1.5;
        //For the right flipper it is 54 degrees -> 0.95 rad
        //For the left it is 360 - 54 = 304 ->
        rotation = new Angle(0.95);
        this.isLeft = isLeft;
        this.color=color;
        origin = new Vect((double)cx*20, (double)cy*20);
        /** o **/
        topCircle = new Circle(origin.x()+0.25+off, origin.y()+0.25, 0.25);
        /** - **/
        topSide = new LineSegment(origin.x()+off, origin.y()+0.25, origin.x()+off+0.5, origin.y()+0.25);
        /**| **/
        leftSide = new LineSegment(origin.x()+off, origin.y()+0.25, origin.x()+off, origin.y()+1.75);
        /**  |**/
        rightSide = new LineSegment(origin.x()+off+0.5, origin.y()+0.25, origin.x()+off+0.5, origin.y()+1.75);
        /** _ **/
        bottomSide = new LineSegment(origin.x()+off, origin.y()+1.75, origin.x()+off+0.5, origin.y()+1.75);
        /** o **/
        bottomCircle = new Circle(origin.x()+off+0.25, origin.y()+1.75, 0.25);

        centerOfRotation = topCircle.getCenter();
        movement = Movement.NONE;
        position = Position.VERTICAL;

    }

    /**
     * This method changes coordinates of flipper
     * @param cx new coordinate of left upper cell which is used for flipper
     * @param cy new coordinate of left upper cell which is used for flipper
     */
    public void move(int cx, int cy) {
        double off = isLeft ? 0 : 1.5;
        origin = new Vect(cx, cy);
        topSide = new LineSegment(cx+off, cy, cx+off+0.5, cy);
        leftSide = new LineSegment(cx+off, cy, cx+off, cy+2);
        rightSide = new LineSegment(cx+off+0.5, cy, cx+off+0.5, cy+2);
        bottomSide = new LineSegment(cx+off, cy+2, cx+off+0.5, cy+2);
        this.notifyObservers();
    }

    /**
     * Rotate at angular velocity of 1080 degrees per second
     * One tick is approx 0.05 sec -> 1080/100 * 5 = 54 (about 0.95 radians)
     * degrees per tick.
     * If possible rotate to 0.95 radians, if not -> rotate the remaining number of degrees.
     * @param left the number of degrees left to rotate
     * @return number of degrees left to rotate for the next rotation
     */
    public Angle rotatePerTick(Angle left) {
        switch(movement) {
            case FORWARDS:
                //moving forwards
                if(getPosition() != Position.HORIZONTAL) {
                    if (left.compareTo(rotation) > 0) {
                        //there are more than 0.95 left -> rotate at 0.95 and return remainder
                        rotate(rotation);
                        leftToRotate = left.minus(rotation);
                        setPosition(Position.BETWEEN);
                        return leftToRotate;
                    } else {
                        //there are less than 0.95 left -> rotate the remainder, stop movement, return 0
                        rotate(left);
                        setMovement(Movement.NONE);
                        setPosition(Position.HORIZONTAL);
                        leftToRotate = Angle.ZERO;
                        return Angle.ZERO;
                    }
                }
                break;
            case BACKWARDS:
                //moving backwards
                if(getPosition() != Position.VERTICAL) {
                    if (left.compareTo(rotation) > 0) {
                        //there are more than 0.95 left -> rotate at 0.95 and return remainder
                        rotate(Angle.ZERO.minus(rotation));
                        leftToRotate = left.minus(rotation);
                        setPosition(Position.BETWEEN);
                        return leftToRotate;
                    } else {
                        //there are less than 0.95 left -> rotate the remainder, stop movement, return 0
                        rotate(Angle.ZERO.minus(left));
                        movement = Movement.NONE;
                        leftToRotate = Angle.DEG_90;
                        setPosition(Position.VERTICAL);
                        return Angle.ZERO;
                    }
                }
                break;
        }
        return Angle.ZERO;
    }

    /**
     * This method rotates the flipper at a given Angle,
     * also considering flipper's position (Left or Right)
     * @param a Angle of rotation
     */
    public void rotate(Angle a) {
        //Flipper is left -> reverse rotation, otherwise -> do nothing
        a = isLeft ? Angle.ZERO.minus(a) : a;

        bottomCircle = Geometry.rotateAround(bottomCircle, centerOfRotation, a);
        topSide = Geometry.rotateAround(topSide, centerOfRotation, a);
        leftSide = Geometry.rotateAround(leftSide, centerOfRotation, a);
        rightSide = Geometry.rotateAround(rightSide, centerOfRotation, a);
        bottomSide = Geometry.rotateAround(bottomSide, centerOfRotation, a);
        topCircle = Geometry.rotateAround(topCircle, centerOfRotation, a);
        setChanged();
        notifyObservers();
    }

    /**
     * @return reserved area for flipper
     */
    public int reservedArea() {
        return 4;
    }

    /**
     * @return color of flipper
     */
    public Color getColor() {
        return color;
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
    public Movement getMovement() {
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

    public String getName() {
        return name;
    }
}
