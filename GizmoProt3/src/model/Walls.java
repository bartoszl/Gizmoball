package model;

<<<<<<< HEAD
import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 22/02/2016.
 */
public class Walls {
    private LineSegment top, bottom, left, right;

    public Walls(){
        top = new LineSegment(0,-10,400,-10);
        bottom = new LineSegment(0,400,400,400);
        left = new LineSegment(-10,-10,0,400);
        right = new LineSegment(400,0,400,400);
    }

    public LineSegment getTop(){ return top; }

    public LineSegment getBottom(){ return bottom; }

    public LineSegment getLeft(){ return left; }

    public LineSegment getRight(){ return right; }

    public List<LineSegment> getWalls(){
        List<LineSegment> walls = new ArrayList<LineSegment>();
        walls.add(top);
        walls.add(bottom);
        walls.add(left);
        walls.add(right);
        return walls;
    }
=======
import java.util.ArrayList;

import physics.LineSegment;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Walls {

    private int xpos1;
    private int ypos1;
    private int ypos2;
    private int xpos2;

    // Walls are the enclosing Rectangle - defined by top left corner and bottom
    // right
    public Walls(int x1, int y1, int x2, int y2) {
        xpos1 = x1;
        ypos1 = y1;
        xpos2 = x2;
        ypos2 = y2;
    }

    public ArrayList<LineSegment> getLineSegments() {
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(xpos1, ypos1, xpos2, ypos1);
        LineSegment l2 = new LineSegment(xpos1, ypos1, xpos1, ypos2);
        LineSegment l3 = new LineSegment(xpos2, ypos1, xpos2, ypos2);
        LineSegment l4 = new LineSegment(xpos1, ypos2, xpos2, ypos2);
        ls.add(l1);
        ls.add(l2);
        ls.add(l3);
        ls.add(l4);
        return ls;
    }

>>>>>>> master
}
