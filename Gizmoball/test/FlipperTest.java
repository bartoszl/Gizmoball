import org.junit.Before;
import model.*;
import org.junit.*;
import physics.*;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.*;

public class FlipperTest {
    private Flipper flipper;

    @Before
    public void setUp() {
        flipper = new Flipper(0, 0, true, "F1");
    }

    @Test
    public void rotateTest() {
    	assertTrue(flipper.getRotation()==0);
    	flipper.rotate();
    	assertTrue(flipper.getRotation()==1);
    	flipper.rotate();
    	assertTrue(flipper.getRotation()==2);
    	flipper.rotate();
    	assertTrue(flipper.getRotation()==3);
    	flipper.rotate();
    	assertTrue(flipper.getRotation()==0);
    }
    
    @Test
    public void nameTest() {
    	assertEquals(flipper.getName(),"F1");
    	flipper.setName("flipper");
    	assertEquals(flipper.getName(),"flipper");
    }
    
    @Test
    public void colorTest() {
    	assertEquals(flipper.getColor(),Color.YELLOW);
    	flipper.setColor(Color.CYAN);
    	assertEquals(flipper.getColor(),Color.CYAN);
    }
    
    @Test
    public void movementTest() {
        assertEquals(flipper.getMovement(),IFlipper.Movement.NONE);
        flipper.press();
        assertEquals(flipper.getMovement(),IFlipper.Movement.FORWARDS);
        flipper.release();
        assertEquals(flipper.getMovement(),IFlipper.Movement.BACKWARDS);
        flipper.setMovement(IFlipper.Movement.NONE);
        assertEquals(flipper.getMovement(),IFlipper.Movement.NONE);
    }
    
    @Test
    public void endCirclesTest() {
    	assertTrue(flipper.getOriginCircle().getCenter().x()==5);
    	assertTrue(flipper.getOriginCircle().getCenter().y()==5);
    	assertTrue(flipper.getEndCircle().getCenter().x()==5);
    	assertTrue(flipper.getEndCircle().getCenter().y()==35);
    }
    
    @Test
    public void moveTest() {
        flipper.move(20, 20);
        assertTrue(flipper.getOrigin().x()==20);
        assertTrue(flipper.getOrigin().y()==20);
        
        flipper.rotate();
        flipper.move(40, 40);
        assertTrue(flipper.getOrigin().x()==40);
        assertTrue(flipper.getOrigin().y()==40);
    }
    
    @Test
    public void angularSpeedTest() {
    	double RAD = 0.017;
    	assertTrue(flipper.getAngSpeed()==-15*RAD*20);
    	
    	flipper = new Flipper(0, 0, false, "F1");
    	assertTrue(flipper.getAngSpeed()==15*RAD*20);
    }

    @Test
    public void getOrientationTest() {
    	assertTrue(flipper.isLeft());
    }
    
    @Test
    public void originTest() {
    	flipper.setOrigin(new Vect(5,5));
    	assertEquals(flipper.getOrigin(),new Vect(5,5));
    }
    
    @Test
    public void currentRotationTest() {
    	assertEquals(flipper.getCurrentRotation(), new Angle(1.0, 0.0));
    }
    
    @Test
    public void testRotatePerTick() {
    	Angle a = flipper.getCurrentRotation();
        flipper.setMovement(Flipper.Movement.FORWARDS);
        flipper.rotatePerTime(0.05);
        assertTrue(flipper.getCurrentRotation().radians()>a.radians());
        flipper.setMovement(Flipper.Movement.BACKWARDS);
        flipper.rotatePerTime(0.05);
        assertEquals(flipper.getCurrentRotation(), a);
    }

    @Test
    public void testFullRotation() {
        flipper.setMovement(Flipper.Movement.FORWARDS);
        while(flipper.getCurrentRotation().radians()<Angle.DEG_90.radians()){
	        flipper.rotatePerTime(0.05);
        }
        assertTrue(flipper.getCurrentRotation().radians()==Angle.DEG_90.radians());
        flipper.rotatePerTime(0.05);
        assertTrue(flipper.getCurrentRotation().radians()==Angle.DEG_90.radians());
        
    }

    @Test
    public void lineTest() {
    	Vect origin = flipper.getOrigin();
        assertTrue(flipper.getLines().size()==2);
        assertEquals(flipper.getLines().get(0),new LineSegment(origin.x(), origin.y()+5, origin.x(), origin.y()+35));
        assertEquals(flipper.getLines().get(1),new LineSegment(origin.x()+10, origin.y()+5, origin.x()+10, origin.y()+35));
    }
    
    @Test
    public void allCirclesTest() {
    	List<LineSegment> lines = flipper.getLines();
    	assertTrue(flipper.getCircles().size()==6);
    	assertEquals(flipper.getCircles().get(0), flipper.getOriginCircle());
    	assertEquals(flipper.getCircles().get(1), flipper.getEndCircle());
    	assertEquals(flipper.getCircles().get(2), new Circle(lines.get(0).p1().x(), lines.get(0).p1().y(), 0));
    	assertEquals(flipper.getCircles().get(3), new Circle(lines.get(0).p2().x(), lines.get(0).p2().y(), 0));
    	assertEquals(flipper.getCircles().get(4), new Circle(lines.get(1).p1().x(), lines.get(1).p1().y(), 0));
    	assertEquals(flipper.getCircles().get(5), new Circle(lines.get(1).p2().x(), lines.get(1).p2().y(), 0));
    }
}