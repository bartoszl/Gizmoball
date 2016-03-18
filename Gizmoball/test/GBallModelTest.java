import model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GBallModelTest {

    private IGBallModel model;

    @Before
    public void setUp() {
        model = new GBallModel();
    }
    
    @Test
    public void addBumpersTest() {
    	model.addSquareBumper(0, 0, 0, "sBump");
        model.addCircularBumper(0, 20, 0, "cBump");
        model.addTriangularBumper(0, 40, 0, "tBump");
        assertFalse(model.getGizmo("sBump")==null);
        assertTrue(model.getGizmo("")==null);
        assertTrue(model.getGizmo("sBump") instanceof SquareBumper);
        assertTrue(model.getGizmo("cBump") instanceof CircularBumper);
        assertTrue(model.getGizmo("tBump") instanceof TriangularBumper);
        model.addSquareBumper(0, 0, 0, "sBump");
        model.addCircularBumper(0, 20, 0, "cBump");
        model.addTriangularBumper(0, 40, 0, "tBump");
        assertTrue(model.getGizmos().size()==3);
    }
    
    @Test
    public void addFlipperTest() {
    	model.addFlipper(0, 0, true, "flipper");
    	assertTrue(model.getFlippers().size()==1);
    	model.addFlipper(0, 0, true, "flipper");
    	assertTrue(model.getFlippers().size()==1);
    	model.addFlipper(60, 0, true, "flipper");
    	assertTrue(model.getFlippers().size()==2);
    }

    @Test
    public void addAbsorberTest() {
    	assertTrue(model.getAbsorber()==null);
    	model.addAbsorber("absorb", 0, 0, 20, 20);
    	model.addAbsorber("absorb", 0, 20, 60, 80);
    	assertFalse(model.getAbsorber()==null);
    	assertTrue(model.getAbsorber().getXTopLeft()==0);
    	assertTrue(model.getAbsorber().getYTopLeft()==20);
    	assertTrue(model.getAbsorber().getXBottomRight()==60);
    	assertTrue(model.getAbsorber().getYBottomRight()==80);
    }
    
    @Test
    public void addBallTest() {
    	assertEquals(model.getBalls(), new ArrayList<Ball>());
    	model.addBall("b1", 10, 10, 50, 50);
    	model.addBall("b2", 10, 10, 50, 50);
    	assertTrue(model.getBalls().size()==1);
    	
    }
    
    @Test
    public void clearTest() {
    	model.addSquareBumper(0, 0, 0, "sBump");
        model.addCircularBumper(0, 20, 0, "cBump");
        model.addTriangularBumper(0, 40, 0, "tBump");
        model.clear();
        assertTrue(model.getGizmo("sBump")==null);
        assertTrue(model.getGizmo("cBump")==null);
        assertTrue(model.getGizmo("tBump")==null);
        assertTrue(model.getGizmos().size()==0);
    }
    
    @Test
    public void connectionTest() {
    	assertEquals(model.getConnections(), new ArrayList<Connection>());
    	model.addConnection("cBump", "f1");
    	assertEquals(model.getConnections(), new ArrayList<Connection>());
    	model.addCircularBumper(0, 20, 0, "cBump");
    	model.addConnection("cBump", "f1");
    	assertEquals(model.getConnections(), new ArrayList<Connection>());
    	model.addFlipper(60, 0, true, "f1");
    	model.addConnection("cBump", "f1");
    	assertTrue(model.getConnections().size()==1);
    	model.addConnection("cBump", "f1");
    	assertTrue(model.getConnections().size()==1);
    }
    
    @Test
    public void gravityTest() {
    	assertTrue(model.getGravity()==25);
    	model.setGravity(50);
    	assertTrue(model.getGravity()==50);
    }
    
    @Test
    public void frictionTest() {
    	assertTrue(model.getFrictionX()==0.025);
    	assertTrue(model.getFrictionY()==0.025);
    	model.setFriction(1.5, 1.5);
    	assertTrue(model.getFrictionX()==1.5);
    	assertTrue(model.getFrictionY()==1.5);
    }
    
    @Test
    public void fileTest() {
    	assertTrue(model.getLoadFile()==null);
    	model.setLoadFile(new File("test.txt"));
    	assertEquals(model.getLoadFile(), new File("test.txt"));
    }
    
    @Test
    public void keyConnectionTest() {
    	assertTrue(model.addKeyConnectionAbs(10, new Absorber("ABS1", 0, 380, 400, 400), "down"));
    	assertTrue(model.getKeyConnectionsAbs().size()==1);
    	assertTrue(model.getKeyConnectionsAbs().get(0).getKeyID()==10);
    	assertTrue(model.addKeyConnectionFlipper(20, new Flipper(0, 0, true, "F1"), "down"));
    	assertTrue(model.getKeyConnectionsFlipper().size()==1);
    	assertTrue(model.getKeyConnectionsFlipper().get(0).getKeyID()==20);
    }
    
    @Test
    public void setGizmosTest() {
    	List<Bumper> bumpers = new ArrayList<Bumper>();
    	Bumper cBump = new CircularBumper(0,0,0,"cBump");
    	bumpers.add(cBump);
    	model.setGizmos(bumpers);
    	assertTrue(model.getGizmos().size()==1);
    	assertEquals(model.getGizmos().get(0), cBump);
    }
    
    @Test
    public void setBallsTest() {
    	List<Ball> balls = new ArrayList<Ball>();
    	Ball ball = new Ball("b1", 5, 5, 50, 50);
    	balls.add(ball);
    	model.setBalls(balls);
    	assertTrue(model.getBalls().size()==1);
    	assertEquals(model.getBalls().get(0), ball);
    }
    
    @Test
    public void setAbsorberTest() {
    	Absorber absorb = new Absorber("absorb", 0, 0, 60, 60);
    	model.setAbsorber(absorb);
    	assertTrue(model.getAbsorber()!=null);
    	assertEquals(model.getAbsorber(), absorb);
    }
    
    @Test
    public void setFlippersTest() {
    	List<Flipper> flippers = new ArrayList<Flipper>();
    	Flipper flip = new Flipper(0,0,true,"flip");
    	flippers.add(flip);
    	model.setFlippers(flippers);
    	assertTrue(model.getFlippers().size()==1);
    	assertEquals(model.getFlippers().get(0), flip);
    }
    
    @Test
    public void rotateElementTest() {
    	assertFalse(model.rotateElement(0, 0));
    	model.addTriangularBumper(0, 40, 0, "tBump");
    	model.addFlipper(0, 0, true, "flipper");
    	assertTrue(model.rotateElement(0, 40));
    	assertTrue(model.getGizmo("tBump").getRotation()==1);
    	assertTrue(model.rotateElement(0, 0));
    }
    
    @Test
    public void deleteElementTest() {
    	assertFalse(model.deleteElement(0, 0));
    	model.addSquareBumper(0, 0, 0, "sBump");
    	assertTrue(model.deleteElement(0, 0));
    	model.addFlipper(0, 0, true, "flipper");
    	assertTrue(model.deleteElement(0, 0));
    	model.addAbsorber("absorb", 0, 0, 20, 20);
    	assertTrue(model.deleteElement(0, 0));
    	model.addBall("b1", 10, 10, 50, 50);
    	assertTrue(model.deleteElement(0, 0));
    }
    
    @Test
    public void moveElementTest() {
    	assertFalse(model.moveElement(0, 0, 60, 60));
    	model.addSquareBumper(40, 0, 0, "sBump");
    	model.addFlipper(0, 0, true, "flipper");
    	model.addAbsorber("absorb", 0, 40, 100, 60);
    	model.addBall("b1", 70, 10, 50, 50);
    	assertFalse(model.moveElement(40, 0, 0, 0));
    	assertFalse(model.moveElement(0, 0, 20, 0));
    	assertTrue(model.moveElement(40, 0, 80, 0));
    	assertTrue(model.moveElement(0, 40, 0, 80));
    	assertTrue(model.moveElement(0, 0, 0, 40));
    	assertTrue(model.moveElement(60, 0, 10, 10));
    }
    
    @Test
    public void occupiedSpacesTest() {
    	boolean[][] occupied = model.getOccupiedSpaces();
    	for(int i=0; i<occupied.length; i++){
    		for(int j=0; j<occupied[i].length; j++){
    			assertFalse(occupied[i][j]);
    		}
    	}
    	model.addCircularBumper(0, 0, 0, "cBump");
    	assertTrue(model.getOccupiedSpaces()[0][0]);
    }
    
    @Test
    public void moveBallTest() {
    	model.addSquareBumper(40, 0, 0, "sBump");
    	model.addFlipper(60, 60, false, "flipper");
    	model.addAbsorber("absorb", 0, 180, 200, 200);
    	model.addBall("b1", 10, 10, 50, 50);
    	model.moveBall();
    	assertTrue(model.getBalls().get(0).getX()!=10);
    	assertTrue(model.getBalls().get(0).getY()!=10);
    	model.reset();
    	assertTrue(model.getBalls().get(0).getX()==10);
    	assertTrue(model.getBalls().get(0).getX()==10);
    }
    
    @Test
    public void objectNameTest() {
    	assertTrue(model.getObjectTypeForKeyConnection("sBump")==null);
    	model.addSquareBumper(40, 0, 0, "sBump");
    	assertTrue(model.getObjectTypeForKeyConnection("sBump")==null);
    	model.addFlipper(60, 60, false, "flipper");
    	model.addAbsorber("absorb", 0, 180, 200, 200);
    	assertEquals(model.getObjectTypeForKeyConnection("flipper"),"Flipper");
    	assertEquals(model.getObjectTypeForKeyConnection("absorb"),"Absorber");
    }
}