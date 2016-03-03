import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import model.SquareBumper;

/**
 * 
 * @author Bartosz Lewandowski
 *
 */
public class SquareTest {

	private SquareBumper sq;
	@Before
	public void setUp() throws Exception {
		sq = new SquareBumper(100,200,0,"SQUAAAAAARE");
	}

	@Test
	public void testGetLines() {
		assertTrue(sq.getLines().size()==4);
		//TODO check each line
	}
	
	@Test
	public void testGetCircles() {
		assertTrue(sq.getCircles().size()==4);
		//TODO check each circle
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

}
