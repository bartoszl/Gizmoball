import model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

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
    public void addKeyConnectionTest() {
    	assertTrue(model.addKeyConnectionAbs(10, new Absorber("ABS1", 0, 380, 400, 400), "down"));
    	assertTrue(model.addKeyConnectionFlipper(20, new Flipper(0, 0, true, "F1"), "down"));
    }
}