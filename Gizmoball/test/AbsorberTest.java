import static org.junit.Assert.*;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Absorber;
import model.Ball;
import physics.Circle;
import physics.LineSegment;

public class AbsorberTest {

	private Absorber abs;
	
	@Before
	public void setUp() throws Exception {
		abs = new Absorber("ABS1", 0, 380, 400, 400);
	}

	@Test
	public void testGetTopLeftX() {
		assertTrue(abs.getXTopLeft()==0);
	}
	
	@Test
	public void testGetTopLeftY() {
		assertTrue(abs.getYTopLeft()==380);
	}
	
	@Test
	public void testGetBottomRightX() {
		assertTrue(abs.getXBottomRight()==400);
	}
	
	@Test
	public void testGetBottomRightY() {
		assertTrue(abs.getYBottomRight()==400);
	}
	
	@Test
	public void testGetWidth() {
		assertTrue(abs.getWidth()==400);
	}
	
	@Test
	public void testGetHeight() {
		assertTrue(abs.getHeight()==20);
	}
	
	@Test
	public void testGetLines() {
		List<LineSegment> lines = abs.getLines();
		assertTrue(lines.size()==4);
		
		assertTrue(lines.get(0).p1().x()==0);
		assertTrue(lines.get(0).p1().y()==380);
		assertTrue(lines.get(0).p2().x()==400);
		assertTrue(lines.get(0).p2().y()==380);
		
		assertTrue(lines.get(1).p1().x()==400);
		assertTrue(lines.get(1).p1().y()==380);
		assertTrue(lines.get(1).p2().x()==400);
		assertTrue(lines.get(1).p2().y()==400);
		
		assertTrue(lines.get(2).p1().x()==0);
		assertTrue(lines.get(2).p1().y()==400);
		assertTrue(lines.get(2).p2().x()==400);
		assertTrue(lines.get(2).p2().y()==400);
		
		assertTrue(lines.get(3).p1().x()==0);
		assertTrue(lines.get(3).p1().y()==380);
		assertTrue(lines.get(3).p2().x()==0);
		assertTrue(lines.get(3).p2().y()==400);
	}
	
	@Test
	public void testGetCircles() {
		List<Circle> circles = abs.getCircles();
		assertTrue(circles.size()==4);
		
		assertTrue(circles.get(0).getCenter().x()==0);
		assertTrue(circles.get(0).getCenter().y()==380);
		assertTrue(circles.get(0).getRadius()==0);
		
		assertTrue(circles.get(1).getCenter().x()==400);
		assertTrue(circles.get(1).getCenter().y()==380);
		assertTrue(circles.get(1).getRadius()==0);
		
		assertTrue(circles.get(2).getCenter().x()==400);
		assertTrue(circles.get(2).getCenter().y()==400);
		assertTrue(circles.get(2).getRadius()==0);
		
		assertTrue(circles.get(3).getCenter().x()==0);
		assertTrue(circles.get(3).getCenter().y()==400);
		assertTrue(circles.get(3).getRadius()==0);
	}
	
	@Test
	public void testColor() {
		abs.setColor(Color.RED);
		assertEquals(abs.getColor(), Color.RED);
	}
	
	@Test
	public void testAbsorbed() {
		Ball ball = new Ball("B1", 200, 200, 100, 100);
		abs.absorb(ball);
		assertTrue(ball.getVelocity().x()==0);
		assertTrue(ball.getVelocity().y()==-1000);
		assertTrue(ball.getX()==abs.getXBottomRight()-15);
		assertTrue(ball.getY()==abs.getYBottomRight()-15);
		assertTrue(ball.isAbsorbed());
	}
	
}
