import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import model.CircularBumper;
import physics.Circle;

public class CircularTest {
	
	private CircularBumper c;
	@Before
	public void setUp() throws Exception {
		c = new CircularBumper(100,150, 0,"CB1");
	}

	@Test
	public void testGetLineSegments() {
		assertNull(c.getLines());
	}
	
	@Test
	public void testGetCircles() {
		assertTrue(c.getCircles().size()==1);
		Circle circ = c.getCircles().get(0);
		assertTrue(circ.getRadius()==10);
		assertTrue(circ.getCenter().x()==100);
		assertTrue(circ.getCenter().y()==150);
	}
	
	@Test
	public void testColor(){
		assertEquals(c.getColor(),Color.GREEN);
		c.setColor(Color.RED);
		assertEquals(c.getColor(), Color.RED);
	}
	
	@Test
	public void testGetName(){
		assertEquals(c.getName(), "CB1");
	}
	
	@Test
	public void testRotation(){
		assertTrue(c.getRotation()==0);
		c.rotate();
		assertTrue(c.getRotation()==1);
		c.rotate();
		c.rotate();
		c.rotate();
		assertTrue(c.getRotation()==0);
	}
	
	@Test
	public void testConstructorRotationBiggerThan4(){
		CircularBumper ci = new CircularBumper(100, 150, 21, "CB2");
		assertTrue(ci.getRotation()==1);
	}
	
	//TODO add test after move and test move;

}
