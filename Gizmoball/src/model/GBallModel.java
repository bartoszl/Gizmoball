package model;

import physics.*;
import physics.Geometry.VectPair;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created by John Watt on 01/03/2016.
 */
public class GBallModel extends Observable implements IGBallModel {

    private double gravity, xFriction, yFriction;
    private List<Bumper> bumpers;
    private List<Flipper> flippers;
    private List<Connection> connections;
    private List<KeyConnectionAbs> keyConnectionsAbs;
    private List<KeyConnectionFlipper> keyConnectionsFlipper;
    private List<Ball> balls;
    private Absorber absorber;
    private boolean [][] occupiedSpaces;
    private Walls walls;
    private File loadFile;
    
    /**
     * Constructor for GBallModel. Creates empty lists for each gizmo.
     * Initialises gravity to 25 and x and y friction to 0.025.
     */
    public GBallModel() {
        bumpers = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnectionsAbs = new ArrayList<KeyConnectionAbs>();
        keyConnectionsFlipper = new ArrayList<KeyConnectionFlipper>();
        flippers = new ArrayList<Flipper>();
        balls = new ArrayList<Ball>();
        walls = new Walls(0,0,400,400);
        occupiedSpaces = new boolean [20][20];
        gravity = 25;
        xFriction = 0.025;
        yFriction = 0.025;
    }
    
    // Build Mode Methods
    
    @Override
    public boolean addSquareBumper(int x, int y, int rotation, String name) {
    	Vect xy = translateXY(x,y);
        x = (int) xy.x();
        y = (int) xy.y();
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            bumpers.add(new SquareBumper(x*20, y*20, rotation, name));
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addFlipper(int x, int y, boolean isLeft, String name) {
    	Vect xy = translateXY(x,y);
        x = (int) xy.x();
        y = (int) xy.y();
        if(x > 18 || y > 18) return false;
        if(!occupiedSpacesFlipper(x, y)) {
            occupyFlipper(x,y);
            Flipper f = new Flipper(x*20, y*20, isLeft, name);
            flippers.add(f);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addTriangularBumper(int x, int y, int rotation, String name) {
    	Vect xy = translateXY(x,y);
        x = (int) xy.x();
        y = (int) xy.y();
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            TriangularBumper tBumper = new TriangularBumper(x*20, y*20, rotation, name);
            bumpers.add(tBumper);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public boolean addCircularBumper(int x, int y, int rotation, String name) {
    	Vect xy = translateXY(x,y);
        x = (int) xy.x();
        y = (int) xy.y();
        if(!occupiedSpaces[x][y]) {
            occupiedSpaces[x][y] = true;
            CircularBumper cBumper = new CircularBumper(x*20, y*20, rotation, name);
            bumpers.add(cBumper);
            notifyObs();
            return true;
        }
        return false;
    }
    
    @Override
    public Bumper getBumper(String gizmoName) {
        for(Bumper b : bumpers) {
            if(b.getName().equals(gizmoName)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean addAbsorber(String name, int x, int y, int x1, int y1) {
    	Vect xy = translateXY(x,y);
        x = (int) xy.x();
        y = (int) xy.y();
        Vect xy1 = translateXY(x1,y1);
        x1 = (int) xy1.x();
        y1 = (int) xy1.y();
        
        for(int i = x; i < x1; i++) {
            for(int j = y; j < y1; j++) {
                if(occupiedSpaces[i][j]) return false;
            }
        }
        if(absorber!=null)
        	unoccupyAbs(absorber.getXTopLeft(), absorber.getYTopLeft(),
                    	absorber.getXBottomRight(), absorber.getYBottomRight());
        absorber = new Absorber(name, x*20, y*20, x1*20, y1*20);
        occupyAbs(x*20, y*20, x1*20, y1*20);
        notifyObs();
        return true;
    }

    @Override
    public boolean addBall(String name, double x, double y, double xv, double yv) {
    	Vect xy = translateXY((int) x ,(int) y);
        int x1 = (int) xy.x();
        int y1 = (int) xy.y();
        if(!occupiedSpaces[x1][y1]) {
            occupiedSpaces[x1][y1] = true;
            Ball b = new Ball(name, x1*20, y1*20, xv, yv);
            balls.add(b);
            notifyObs();
            return true;
        }
        return false;
    }

    @Override
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public double getGravity() {
        return gravity;
    }

    @Override
    public void setFriction(double xFriction, double yFriction) {
        this.xFriction = xFriction;
        this.yFriction = yFriction;
    }

    @Override
    public double getFrictionX() {
        return xFriction;
    }

    @Override
    public double getFrictionY() {
        return yFriction;
    }

    public boolean loadConnection(String cBumperName, String flipperName) {
         if(safeToAddConnection(cBumperName, flipperName)) {
            CircularBumper circularBumper = getCircularBumper(cBumperName);
            Flipper flipper = getFlipper(flipperName);
            Connection connection = new Connection(circularBumper, flipper);
            return connections.add(connection);
        } else {
             return false;
         }
    }

    @Override
    public boolean addConnection(Bumper bumper, Flipper flipper) {
        Connection connection = new Connection(bumper, flipper);
        if(connections.contains(connection)) {
            return false;
        } else {
            return connections.add(connection);
        }
    }
    
    @Override
    public Flipper getFlipper(String flipperName) {
        for(Flipper flipper : flippers) {
            if(flipper.getName().equals(flipperName)) {
                return flipper;
            }
        }
        return null;
    }
    
    @Override
    public boolean addKeyConnectionAbs(int keyID, IAbsorber abs, String upDown) {
        KeyConnectionAbs keyConnectionAbs = new KeyConnectionAbs(keyID, abs, upDown);
        if(keyConnectionsAbs.contains(keyConnectionAbs)) {
            return false;
        } else {
            return keyConnectionsAbs.add(keyConnectionAbs);
        }
    }
    
    @Override
    public boolean addKeyConnectionFlipper(int keyID, IFlipper flipper, String upDown) {
        KeyConnectionFlipper keyConnectionFlipper = new KeyConnectionFlipper(keyID, flipper, upDown);
        if(keyConnectionsFlipper.contains(keyConnectionFlipper)) {
            return false;
        } else {
            return keyConnectionsFlipper.add(keyConnectionFlipper);
        }

    }

    @Override
    public List<Bumper> getBumpers() {
        return bumpers;
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
		return flippers;
	}

    @Override
    public void setBumpers(List<Bumper> bumpers) {
        this.bumpers = bumpers;
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
    
    @Override
    public boolean deleteElement(double x, double y) {
        x=x-(x%20);
        y=y-(y%20);
        Bumper b = findBumper(x,y);
        Flipper f = findFlipper(x,y);
        Ball ball = findBall(x, y);
        if(b==null && f==null && ball == null && absorber == null) return false;
        if(b!=null) {
            getBumpers().remove(b);
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
			if(occupiedSpaces[(int)newX/20][(int)newY/20]) return false;
			b.move(newX, newY);
	        occupiedSpaces[(int)x/20][(int)y/20] = false;
	        occupiedSpaces[(int)newX/20][(int)newY/20] = true;
	        notifyObs();
			return true;
		}
		if(f!=null){
            if((newX/20) > 18 || (newY/20) > 18) return false;
			if(occupiedSpacesFlipper((int)newX/20, (int)newY/20)) return false;
            unoccupyFlipper((int) f.getOrigin().x() / 20, (int) f.getOrigin().y() / 20);
			f.move(newX, newY);
			occupyFlipper((int)newX/20, (int)newY/20);
			notifyObs();
			return true;
		}
		if(ball!=null) {
            if(occupiedSpaces[(int) newX / 20][(int) newY / 20]) return false;
            ball.move(newX, newY);
            occupiedSpaces[(int) x / 20][(int) y / 20] = false;
            occupiedSpaces[(int) newX / 20][(int) newY / 20] = true;
            notifyObs();
            return true;
        }
        if(findAbs(x,y)){
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
	
	@Override
    public boolean[][] getOccupiedSpaces() {
        return occupiedSpaces;
    }
    
	@Override
    public void clear(){
    	bumpers = new ArrayList<Bumper>();
        connections = new ArrayList<Connection>();
        keyConnectionsAbs = new ArrayList<KeyConnectionAbs>();
        keyConnectionsFlipper = new ArrayList<KeyConnectionFlipper>();
        flippers = new ArrayList<Flipper>();
        balls = new ArrayList<Ball>();
        occupiedSpaces = new boolean [20][20];
        absorber = null;
        notifyObs();
    }
	
	@Override
    public List<KeyConnectionAbs> getKeyConnectionsAbs() {
        return keyConnectionsAbs;
    }
	
	@Override
    public List<KeyConnectionFlipper> getKeyConnectionsFlipper() {
        return keyConnectionsFlipper;
    }
	
	@Override
    public List<Connection> getConnections() {
        return connections;
    }
	
	@Override
    public String getObjectTypeForKeyConnection(String objectName) {
        if(absorber != null) {
            if(absorber.getName().equals(objectName)) {
                return "Absorber";
            }
        }

        for(Flipper flipper : flippers) {
            if(flipper.getName().equals(objectName)) {
                return "Flipper";
            }
        }
        return null;
    }

    public void setConnectedToAbs(boolean set){
        absorber.setConnectedToItself(set);
    }
    
    @Override
	public Flipper findFlipper(double x, double y){
		for(Flipper f:flippers){
			double xx = f.getOrigin().x();
			double yy = f.getOrigin().y();
			for(int i=0;i<2;i++){
				for(int j=0;j<2;j++){
					if(xx==x-(i*20) && yy==y-(20*j)) return f;
				}
			}
		}
		return null;
	}
    
    @Override
    public Bumper findBumper(double x, double y){
		for(Bumper b: bumpers){
			if(b.getX()==x && b.getY()==y)
				return b;
		}
		return null;
	}
	
	// Run Mode Methods
    
	@Override
	public void moveModel() {
		double moveTime = 0.05;
		double minTime = moveTime;
		List<CollisionDetails> cl = new ArrayList<CollisionDetails>();
		for(Ball ball: balls){
			cl.add(timeUntilCollision(ball));
			CollisionDetails current = cl.get(cl.size()-1);
			if(current.getTime()<minTime) 
				if(current.getTime()!=0 && !ball.isAbsorbed() && ball.isMoving())
					minTime = cl.get(cl.size()-1).getTime();
		}
		
		moveFlippers(minTime);
		
		for(Ball ball: balls){
			if(!ball.isMoving())
				continue;
			if(ball.isAbsorbed()){
				ball = moveBallForTime(ball, moveTime);
				if(ball.getY()<absorber.getYTopLeft())
					ball.setAbsorbed(false);
				notifyObs();
				continue;
			}
			CollisionDetails cd = cl.get(balls.indexOf(ball));
			double tuc = cd.getTime();
			if(tuc>moveTime){
				ball = moveBallForTime(ball, moveTime);
				notifyObs();
				continue;
			}
			if(cd.getAbsorbed()){
				absorber.absorb(ball);
				notifyObs();
				continue;
			}
            if(cd.getBumper() != null && (tuc != 0)) {
                collidedWithBumper(cd.getBumper());
            }
			ball = moveBallForTime(ball, tuc);
			ball.setVelocity(cd.getVelocity());
			Vect v = calcVelocity(ball, moveTime);
			ball.setVelocity(v);
			
			notifyObs();
		}
	}
    
    @Override
    public void reset() {
        resetBalls();
        resetFlippers();
        resetBumpers();
    }

    public void resetBumpers() {
        for(Bumper b : getBumpers()) {
            if(b instanceof CircularBumper) {
                b.setColor(Color.GREEN);
            } else if(b instanceof TriangularBumper) {
                b.setColor(Color.BLUE);
            } else if(b instanceof SquareBumper) {
                b.setColor(Color.RED);
            }
        }
    }
    
    // Neutral Methods

    public void setLoadFile(File f){
        this.loadFile = f;
    }

    public File getLoadFile(){
        return loadFile;
    }
    
    // Private Methods
    
    private void notifyObs() {
        setChanged();
        notifyObservers();
    }
    
    private Vect translateXY(int x, int y){
    	x -= x%20;
    	y -= y%20;
    	x /=20;
    	y /=20;
    	return new Vect(x,y);
    }
    
    private CircularBumper getCircularBumper(String circularBumperName) {
        for(Bumper gizmo : bumpers) {
            if(gizmo instanceof CircularBumper) {
                if(gizmo.getName().equals(circularBumperName)) {
                    return (CircularBumper) gizmo;
                }
            }
        }
        return null;
    }
    
    
    private boolean safeToAddConnection(String circularBumperName, String flipperName) {
        return !checkForExistingConnection(circularBumperName, flipperName)
                && checkCircularBumperExists(circularBumperName)
                && checkFlipperExists(flipperName);
    }

    private boolean checkForExistingConnection(String circularBumperName, String flipperName) {
        for(Connection connection : connections) {
            if(connection.getFlipper().getName().equals(flipperName)
            && connection.getTrigger().getName().equals(circularBumperName)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCircularBumperExists(String circularBumperName) {
        for(Bumper gizmo : bumpers) {
            if(gizmo instanceof CircularBumper) {
                if(gizmo.getName().equals(circularBumperName)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkFlipperExists(String flipperName) {
        for(Flipper f : flippers) {
            if(f.getName().equals(flipperName)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean occupiedSpacesAbs(double x, double y){
        for(int i = (int)x/20; i < (x+absorber.getWidth())/20; i++){
            for(int j = (int)y/20; j < (y+absorber.getHeight())/20; j++){
                if(i > 19 || j > 19) return true;
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
        for(int i = (int)x/20; i < (int)x1/20; i++) {
            for(int j = (int)y/20; j < (int)y1/20; j++) {
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
	
	private void moveFlippers(double time) {
        for(Flipper f : getFlippers()) {
            f.rotatePerTime(time);
            notifyObs();
        }
    }
	
	private Vect calcVelocity(Ball ball, double moveTime){
    	Vect temp = new Vect(ball.getVelocity().x(), ball.getVelocity().y() + (gravity*20*moveTime));
        return applyFriction(temp, moveTime);
    }
	
	private void resetBalls() {
        for(Ball b : balls){
            b.reset();
            b.setMoving(true);
            b.setVelocity(new Vect(50,50));
        }
    }

    private void resetFlippers() {
        ArrayList<Flipper> newFlippers = new ArrayList<>();
        for(Flipper f : flippers){
            Flipper temp = new Flipper((int)f.getOrigin().x(), (int)f.getOrigin().y(), f.isLeft(), "flipper");
            for(int i = 0; i < f.getRotation(); i++){
                temp.rotate();
            }
            for(Connection c : connections){
                if(c.getFlipper().equals(f)) c.setFlipper(temp);
            }
            for(KeyConnectionFlipper kc : keyConnectionsFlipper){
                if(kc.getFlipper().equals(f)) kc.setFlipper(temp);
            }
            newFlippers.add(temp);
        }
        flippers = newFlippers;
    }
    
    private Vect applyFriction(Vect Vold, double time){
        double length = Vold.length();
        return Vold.times((1 - (xFriction * time) - (yFriction * (length/20) * time)));
    }
    
    private Ball moveBallForTime(Ball ball, double time){
		double vx = ball.getVelocity().x();
		double vy = ball.getVelocity().y();
		double newX = ball.getX() + (vx*time);
		double newY = ball.getY() + (vy*time);
		ball.setXY(newX, newY);
		Vect newV = calcVelocity(ball, time);
		ball.setVelocity(newV);
		return ball;
	}
    
    private CollisionDetails timeUntilCollision(Ball ball) {
        Bumper collidedWith = null;
		Circle ballCircle = ball.getCircle();
		Vect ballVelocity = ball.getVelocity();
		Vect newVelocity = new Vect(0,0);
		double shortest = Double.MAX_VALUE;
		double time;
		// Check walls
		for(LineSegment line: walls.getLines()){
			time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
			if(time<shortest){
				shortest=time;
				newVelocity = Geometry.reflectWall(line, ballVelocity, 1.0);
			}
		}
		// Check Bumpers
		for(Bumper bumper: bumpers){
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
					newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1.0);
				}
			}
		}
		// Check flippers
		for(Flipper flipper: flippers){
			for(LineSegment line: flipper.getLines()){
				if(flipper.getMovement()==IFlipper.Movement.NONE){
					time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
					if(time<shortest){
						shortest=time;
						newVelocity = Geometry.reflectWall(line, ballVelocity, 0.95);
						collidedWith = null;
					}
				} else {
					time = Geometry.timeUntilRotatingWallCollision(line, flipper.getCircles().get(0).getCenter(), flipper.getAngSpeed(), ballCircle, ballVelocity);
					if(time<shortest){
						shortest=time;
						newVelocity = Geometry.reflectRotatingWall(line, flipper.getCircles().get(0).getCenter(), flipper.getAngSpeed(), ballCircle, ballVelocity);
						collidedWith = null;
					}
				}
				
			}
			for(Circle circle: flipper.getCircles()){
				if(flipper.getMovement()==IFlipper.Movement.NONE) {
					time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
					if(time<shortest){
						shortest=time;
						newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 0.95);
						collidedWith = null;
					}
				} else {
					time = Geometry.timeUntilRotatingCircleCollision(circle, flipper.getCircles().get(0).getCenter(), flipper.getAngSpeed(), ballCircle, ballVelocity);
					if(time<shortest){
						shortest=time;
						newVelocity = Geometry.reflectRotatingCircle(circle, flipper.getCircles().get(0).getCenter(), flipper.getAngSpeed(), ballCircle, ballVelocity);
						collidedWith = null;
					}
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
					collidedWith = null;
					shortest=time;
					newVelocity = Geometry.reflectWall(line, ballVelocity, 1.0);
					
				}
			}
			for(Circle circle: absorber.getCircles()){
				time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
				if(time<shortest){
					abs=true;
					collidedWith = null;
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
					collidedWith = null;
				}
			}
		}
		
		return new CollisionDetails(shortest, newVelocity, abs, collidedWith);
	}
    
    private void collidedWithBumper(Bumper bumper) {

        for(Connection c : getConnections()) {
            if(c.getTrigger().equals(bumper)) {
                c.getFlipper().press();
                if(c.getFlipper().getCurrentRotation().radians()==Angle.DEG_90.radians())
                	c.getFlipper().release();
            }
        }
        //and change the color
        Color color = null;
        Random random = new Random();
        int randomNumber = random.nextInt(6);
        switch (randomNumber) {
            case 0:
                color = Color.GREEN;
                break;
            case 1:
                color = Color.RED;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.ORANGE;
                break;
            case 5:
                color = Color.BLACK;
                break;
            default:
        }
        bumper.setColor(color);
    }
}
