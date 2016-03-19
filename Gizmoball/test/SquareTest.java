import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import model.SquareBumper;
import physics.Circle;
import physics.LineSegment;

/**
 * 
 * @author Bartosz Lewandowski
 *
 */
public class SquareTest {

	private SquareBumper sq;
	private static final double L = 20;

	@Before
	public void setUp() throws Exception {
		sq = new SquareBumper(100,200,0,"SQUAAAAAARE");
	}

	@Test
	public void testGetLines() {
		assertTrue(sq.getLines().size()==4);
		assertEquals(sq.getLines().get(0), new LineSegment(100, 200, 100+L, 200));
		assertEquals(sq.getLines().get(1), new LineSegment(100+L, 200, 100+L, 200+L));
		assertEquals(sq.getLines().get(2), new LineSegment(100, 200+L, 100+L, 200+L));
		assertEquals(sq.getLines().get(3), new LineSegment(100, 200, 100, 200+L));
	}
	
	@Test
	public void testGetCircles() {
		assertTrue(sq.getCircles().size()==4);
		assertEquals(sq.getCircles().get(0), new Circle(100, 200, 0));
		assertEquals(sq.getCircles().get(1), new Circle(100+L, 200, 0));
		assertEquals(sq.getCircles().get(2), new Circle(100+L, 200+L, 0));
		assertEquals(sq.getCircles().get(3), new Circle(100, 200+L, 0));
	}
	
	@Test
	public void testColor() {
		assertEquals(sq.getColor(), Color.RED);
	}
	
	@Test
	public void testRotation() {
		assertTrue(sq.getRotation()==0);
		sq.rotate();
		assertTrue(sq.getRotation()==1);
		sq.rotate();
		sq.rotate();
		sq.rotate();
		assertTrue(sq.getRotation()==0);
	}
	
	@Test
	public void testGetName() {
		assertEquals(sq.getName(), "SQUAAAAAARE");
	}
	
	@Test
	public void testMove() {
		sq.move(20, 40);
		assertTrue(sq.getX()==20);
		assertTrue(sq.getY()==40);
	}
	
	@Test
	public void testGetLinesAfterMove() {
		assertTrue(sq.getLines().size()==4);
		//TODO check all lines
	}
	
	@Test
	public void testGetCirclesAfterMove() {
		assertTrue(sq.getCircles().size()==4);
		//TODO check all circles
	}

	@Test
	public void testSetColor() {
		sq.setColor(Color.BLACK);
		assertEquals(sq.getColor(), Color.BLACK);
	}

}
