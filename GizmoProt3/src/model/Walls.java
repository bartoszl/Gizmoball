package model;

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
}
