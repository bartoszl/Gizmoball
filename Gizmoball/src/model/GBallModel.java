package model;

import view.Board;

import java.awt.*;
import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by John Watt on 01/03/2016.
 */
public class GBallModel extends Observable implements IGBallModel {

    private double gravity, xFriction, yFriction;
    private List<Bumper> gizmos;
    private List<Flipper> flippers;
    private List<Connection> connections;
    private List<KeyConnectionAbs> keyConnectionsAbs;
    private List<KeyConnectionFlipper> keyConnectionsFlipper;
    private List<Ball> balls;
    private Absorber absorber;
    private boolean [][] occupiedSpaces;
    private int lX, lY;
    private Walls walls;
    private File loadFile;

    public GBallModel() {
        gizmos = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnectionsAbs = new ArrayList<KeyConnectionAbs>();
        keyConnectionsFlipper = new ArrayList<KeyConnectionFlipper>();
        flippers = new ArrayList<Flipper>();
        balls = new ArrayList<Ball>();
        walls = new Walls(0,0,400,400);
        occupiedSpaces = new boolean [20][20];
    }

    private void notifyObs() {
        setChanged();
        notifyObservers();
        //System.out.println("changed");
    }

    @Override
    public boolean addSquareBumper(int x, int y, int rotation, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            gizmos.add(new SquareBumper((double) x, (double) y, rotation, name));
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addFlipper(int x, int y, boolean isLeft, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(lX > 18 || lY > 18) return false;
        if(!occupiedSpacesFlipper(lX, lY)) {
            occupyFlipper(lX,lY);
            Flipper f = new Flipper(x, y, isLeft, Color.YELLOW, name);
            flippers.add(f);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public void checkKeyConnectionsFlipper(int keyCode) {

    }

    @Override
    public boolean addTriangularBumper(int x, int y, int rotation, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            TriangularBumper tBumper = new TriangularBumper((double) x - x%20, (double) y - y%20, rotation, name);
            gizmos.add(tBumper);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addCircularBumper(int x, int y, int rotation, String name) {
        x -= x%20;
        y -= y%20;
        lX = x/20;
        lY = y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            CircularBumper cBumper = new CircularBumper((double) x, (double) y, rotation, name);
            gizmos.add(cBumper);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public Bumper getGizmo(String gizmoName) {
        for(Bumper b : gizmos) {
            if(b.getName().equals(gizmoName)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean addAbsorber(String name, int x, int y, int x1, int y1) {
        x -= x%20;
        y -= y%20;
        x1 -= (x1%20);
        y1 -= (y1%20);
        lX = x/20;
        lY = y/20;
        for(int i = Math.min(x,x1)/20; i < Math.max(x,x1)/20; i++) {
            for(int j = Math.min(y,y1)/20; j < Math.max(y,y1)/20; j++) {
                if(occupiedSpaces[i][j]) return false;
            }
        }
        if(this.getAbsorber()!=null){
            unoccupyAbs(absorber.getXTopLeft(), absorber.getYTopLeft(),
                        absorber.getXBottomRight(), absorber.getYBottomRight());
            if(occupiedSpacesAbs(Math.min(x,x1)/20, Math.min(y,y1)/20)) return false;
        }
        absorber = new Absorber(name, (double) x, (double) y, (double) x1, (double) y1);
        occupyAbs(x, y, x1, y1);
        notifyObs();
        return true;
    }

    @Override
    public boolean addBall(String name, double x, double y, double xv, double yv) {
        x -= x%20;
        y -= y%20;
        lX = (int) x/20;
        lY = (int) y/20;
        if(!occupiedSpaces[lX][lY]) {
            occupiedSpaces[lX][lY] = true;
            Ball b = new Ball(name, x, y, xv, yv);
            balls.add(b);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
        notifyObs();
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Override
    public void setFriction(double xFriction, double yFriction) {
        this.xFriction = xFriction;
        this.yFriction = yFriction;
        notifyObs();
    }

    @Override
    public double getFrictionX() {
        return xFriction;
    }

    @Override
    public double getFrictionY() {
        return yFriction;
    }

    @Override
    public boolean addConnection(Connection connection) {
        return connections.add(connection);
    }


    public boolean addKeyConnectionAbs(KeyConnectionAbs keyConnectionAbs) {
        return keyConnectionsAbs.add(keyConnectionAbs);
    }

    public boolean addKeyConnectionFlipper(KeyConnectionFlipper keyConnectionFlipper) {
        return keyConnectionsFlipper.add(keyConnectionFlipper);
    }

    @Override
    public List<Bumper> getGizmos() {
        return gizmos;
    }

    @Override
    public Absorber getAbsorber() {
        return absorber;
    }

    @Override
    public List<Ball> getBalls() {
        return balls;
    }

	@Override
	public List<Flipper> getFlippers() {
		// TODO Auto-generated method stub
		return flippers;
	}

    @Override
    public void setGizmos(List<Bumper> bumpers) {
        this.gizmos = bumpers;
    }

    @Override
    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public void setAbsorber(Absorber absorber) {
        this.absorber = absorber;
    }

    @Override
    public void setFlippers(List<Flipper> flippers) {
        this.flippers = flippers;
    }

    @Override
	public boolean rotateElement(double x, double y) {
		x=x-(x%20);
		y=y-(y%20);
		Bumper b = findBumper(x,y);
		Flipper f = findFlipper(x,y);
		if(b==null && f==null) return false;
		if(b!=null)
			b.rotate();
		if(f!=null)
			f.rotate();
		notifyObs();
		return true;
	}

    public boolean deleteElement(double x, double y) {
        x=x-(x%20);
        y=y-(y%20);
        Bumper b = findBumper(x,y);
        Flipper f = findFlipper(x,y);
        Ball ball = findBall(x, y);
        if(b==null && f==null && ball == null && absorber == null) return false;
        if(b!=null) {
            getGizmos().remove(b);
            occupiedSpaces[(int) x / 20][(int) y / 20] = false;
        }
        if(f!=null) {
            getFlippers().remove(f);
            unoccupyFlipper((int) f.getOrigin().x() / 20, (int) f.getOrigin().y() / 20);
        }
        if(ball!=null) {
            getBalls().remove(ball);
            occupiedSpaces[(int) x / 20][(int) y / 20] = false;
        }
        if(absorber!=null && findAbs(x,y)){
            unoccupyAbs(absorber.getXTopLeft(), absorber.getYTopLeft(),
                    absorber.getXBottomRight(), absorber.getYBottomRight());
            absorber=null;
        }
        notifyObs();
        return true;
    }

	@Override
	public boolean moveElement(double x, double y, double newX, double newY) {
		x=x-(x%20);
		y=y-(y%20);
		newX=newX-(newX%20);
		newY=newY-(newY%20);
		Bumper b = findBumper(x,y);
		Flipper f = findFlipper(x,y);
		Ball ball = findBall(x,y);
        if(b==null && f==null && ball==null && absorber==null) return false;
		if(b!=null){
			if(occupiedSpaces[(int)newX/20][(int)newY/20]==true) return false;
			b.move(newX, newY);
	        occupiedSpaces[(int)x/20][(int)y/20] = false;
	        occupiedSpaces[(int)newX/20][(int)newY/20] = true;
	        notifyObs();
			return true;
		}
		if(f!=null){
			if(occupiedSpacesFlipper((int)newX/20, (int)newY/20)) return false;
            unoccupyFlipper((int) f.getOrigin().x() / 20, (int) f.getOrigin().y() / 20);
			f.move(newX, newY);
			occupyFlipper((int)newX/20, (int)newY/20);
			notifyObs();
			return true;
		}
		if(ball!=null) {
            if (occupiedSpaces[(int) newX / 20][(int) newY / 20] == true) return false;
            ball.move(newX, newY);
            occupiedSpaces[(int) x / 20][(int) y / 20] = false;
            occupiedSpaces[(int) newX / 20][(int) newY / 20] = true;
            notifyObs();
            return true;
        }
        if(absorber!=null && findAbs(x,y)){
            if(occupiedSpacesAbs(newX, newY)) return false;
            if((newX/20) > 20-(absorber.getWidth()/20)) return false;
            if((newY/20) > 20-(absorber.getHeight()/20)) return false;
            unoccupyAbs(absorber.getXTopLeft(), absorber.getYTopLeft(),
                        absorber.getXBottomRight(), absorber.getYBottomRight());
            absorber.move(newX,newY);
            occupyAbs(absorber.getXTopLeft(), absorber.getYTopLeft(),
                      absorber.getXBottomRight(), absorber.getYBottomRight());
            notifyObs();
            return true;
        }
		return false;
	}

    private boolean occupiedSpacesAbs(double x, double y){
        for(int i = (int)x/20; i < (x+absorber.getWidth())/20; i++){
            for(int j = (int)y/20; j < (y+absorber.getHeight())/20; j++){
                if(occupiedSpaces[i][j]) return true;
            }
        }
        return false;
    }

    private boolean findAbs(double x, double y){
        if(x < absorber.getXTopLeft() || x > absorber.getXBottomRight()){
            return false;
        }
        if(y < absorber.getYTopLeft() || y > absorber.getYBottomRight()){
            return false;
        }
        return true;
    }

    private void occupyAbs(double x, double y, double x1, double y1){
        for(int i = (int)x/20; i < x1/20; i++) {
            for(int j = (int)y/20; j < y1/20; j++) {
                occupiedSpaces[i][j] = true;
            }
        }
    }

    private void unoccupyAbs(double x, double y, double x1, double y1){
        for(int i = (int)x/20; i < x1/20; i++) {
            for(int j = (int)y/20; j < y1/20; j++) {
                occupiedSpaces[i][j] = false;
            }
        }
    }
	
	private void unoccupyFlipper(int x, int y){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
                occupiedSpaces[x+i][y+j]=false;
			}
		}
	}
	
	private void occupyFlipper(int x, int y){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
                occupiedSpaces[x+i][y+j]=true;
			}
		}
	}
	
	private boolean occupiedSpacesFlipper(int x, int y){
		for(int i=0;i<2;i++){
			for(int j=0;j<2;j++){
                if(occupiedSpaces[x+i][y+j]) return true;
			}
		}
		return false;
	}
	
	private Ball findBall(double x, double y){
		for(Ball b: balls){
			if(b.getX()-10==x && b.getY()-10==y)
				return b;
		}
		return null;
	}
	
	public Bumper findBumper(double x, double y){
		for(Bumper b: gizmos){
			if(b.getX()==x && b.getY()==y)
				return b;
		}
		return null;
	}
	
	public Flipper findFlipper(double x, double y){
		for(Flipper f:flippers){
			double xx = f.getOrigin().x();
			double yy = f.getOrigin().y();
			System.out.println(xx+" "+yy);
			for(int i=0;i<2;i++){
				for(int j=0;j<2;j++){
					if(xx==x-(i*20) && yy==y-(20*j)) return f;
				}
			}
		}
		return null;
	}

    public void moveFlippers() {
        for(Flipper f : getFlippers()) {
            if(f.getMovement() == IFlipper.Movement.FORWARDS) {
                f.rotatePerTick();
            } else if(f.getMovement() == IFlipper.Movement.BACKWARDS) {
                f.rotatePerTick();
            }
        }
    }

    public boolean[][] getOccupiedSpaces() {
        return occupiedSpaces;
    }
    
    public void clear(){
    	gizmos = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnectionsAbs = new ArrayList<KeyConnectionAbs>();
        keyConnectionsFlipper = new ArrayList<KeyConnectionFlipper>();
        flippers = new ArrayList<Flipper>();
        balls = new ArrayList<Ball>();
        occupiedSpaces = new boolean [20][20];
        absorber = null;
        notifyObs();
    }

    public List<KeyConnectionAbs> getKeyConnectionsAbs() {
        return keyConnectionsAbs;
    }

    public List<KeyConnectionFlipper> getKeyConnectionsFlipper() {
        return keyConnectionsFlipper;
    }

    public List<Connection> getConnections() {
        return connections;
    }
    // ?
	@Override
	public void moveBall() {
		double moveTime = 0.05;
		List<CollisionDetails> cl = new ArrayList<CollisionDetails>();
		for(Ball ball: balls){
            Vect temp = new Vect(ball.getVelocity().x(), ball.getVelocity().y() + (500*moveTime));
            Vect Vnew = applyFriction(temp, moveTime);
            ball.setVelocity(Vnew);
			cl.add(timeUntilCollision(ball));
		}
		for(Ball ball: balls){
			if(ball!=null && ball.isMoving()){
				if(ball.isAbsorbed()){
					ball = moveBallForTime(ball, moveTime);
					if(ball.getY()<absorber.getYTopLeft())
						ball.setAbsorbed(false);
				} else {
					CollisionDetails cd = cl.get(balls.indexOf(ball));
					double tuc = cd.getTime();
					if(tuc>moveTime){
						ball = moveBallForTime(ball, moveTime);
					} else {
						if(!cd.getAbsorbed()){
                            if(cd.getBumper() != null) {
                                collidedWithBumper(cd.getBumper());
                            }
							ball = moveBallForTime(ball, tuc);
							ball.setVelocity(cd.getVelocity());
						} else {
							absorber.absorb(ball);

						}
					}
				}
				notifyObs();
			}
			
		}
	}

    public void collidedWithBumper(Bumper bumper) {
        for(Connection c : getConnections()) {
            System.out.println("Fire!");
            if(c.getTrigger().equals(bumper)) {
                c.getFlipper().press();
                c.getFlipper().release();
            }
        }
    }

    public void resetBalls() {
        for(Ball b : balls){
            b.reset();
            b.setMoving(true);
            b.setVelocity(new Vect(0,0));
        }
    }

    public Vect applyFriction(Vect Vold, double time){
        double newVect = Math.sqrt((Math.pow(Vold.x(), 2)+Math.pow(Vold.y(), 2)));
        return Vold.times((1 - (0.025 * time) - ((0.025) * (newVect/20) * time)));
    }

    public Ball moveBallForTime(Ball ball, double time){
		double vx = ball.getVelocity().x();
		double vy = ball.getVelocity().y();
		double newX = ball.getX() + (vx*time);
		double newY = ball.getY() + (vy*time);
        // Very badly done stopping detection
        Vect Vold = ball.getVelocity();
        if(ball.getX() == newX && ball.getY() == newY && !ball.isAbsorbed()
                && ball.getVelocity().equals(Vold)
                && Vold.x() < 20 && Vold.x() > -20
                && Vold.y() < 20 && Vold.y() > -20){
            ball.setMoving(false);
            return ball;
        }
		ball.setXY(newX, newY);
		return ball;
	}
	
	private CollisionDetails timeUntilCollision(Ball ball) {
        Bumper collidedWith = null;
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelocity();
		Vect newVelocity = new Vect(0,0);
		double shortest = Double.MAX_VALUE;
		double time = 0.0;
		// Check walls
		for(LineSegment line: walls.getLines()){
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if(time<shortest){
				shortest=time;
				newVelocity = Geometry.reflectWall(line, ballVelocity, 1.0);
			}
		}
		// Check Bumpers
		for(Bumper bumper: gizmos){
			for(LineSegment line: bumper.getLines()){
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if(time<shortest){
					shortest=time;
					newVelocity = Geometry.reflectWall(line, ballVelocity, 1.0);
                    collidedWith = bumper;
				}
			}
			for(Circle circle: bumper.getCircles()){
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if(time<shortest){
                    collidedWith = bumper;
					shortest=time;
					newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity);
				}
			}
		}
		// Check flippers
		for(Flipper flipper: flippers){
			for(LineSegment line: flipper.getLines()){
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if(time<shortest){
					shortest=time;
					newVelocity = Geometry.reflectWall(line, ballVelocity, 1.0);
				}
			}
			for(Circle circle: flipper.getCircles()){
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if(time<shortest){
					shortest=time;
					newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity);
				}
			}
		}
		// Check absorber
		boolean abs=false;
		if(absorber!=null){
			for(LineSegment line: absorber.getLines()){
				time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
				if(time<shortest){
					abs=true;
					shortest=time;
					newVelocity = Geometry.reflectWall(line, ballVelocity, 1.0);
					
				}
			}
			for(Circle circle: absorber.getCircles()){
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if(time<shortest){
					abs=true;
					shortest=time;
					newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity);
				}
			}
		}
		
		//Check other balls
		for(Ball anotherBall:balls){
			if(!anotherBall.equals(ball)){
				time = Geometry.timeUntilBallBallCollision(ballCircle, ballVelocity, anotherBall.getCircle(), anotherBall.getVelocity());
				if(time<shortest){
					shortest=time;
					VectPair velocities = Geometry.reflectBalls(ballCircle.getCenter(),
																1,
																ballVelocity,
																anotherBall.getCircle().getCenter(),
																1, 
																anotherBall.getVelocity());
					newVelocity = velocities.v1;
					abs=false;
				}
			}
		}
		
		return new CollisionDetails(shortest, newVelocity, abs, collidedWith);
	}

    public void setLoadFile(File f){
        this.loadFile = f;
    }

    public File getLoadFile(){
        return loadFile;
    }
}
