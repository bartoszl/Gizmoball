import static org.junit.Assert.*;

import java.awt.Color;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.TriangularBumper;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class TriangularTest {
	private TriangularBumper tBump;
	
	@Before
	public void setUp() throws Exception {
		tBump = new TriangularBumper(0,0,0,"triangle1");
	}

	@Test
	public void nameTest() {
		assertEquals(tBump.getName(),"triangle1");
	}
	
	@Test
	public void coordinatesTest() {
		assertTrue(tBump.getX()==0);
		assertTrue(tBump.getY()==0);
		tBump.move(5, 5);
		assertTrue(tBump.getX()==5);
		assertTrue(tBump.getY()==5);
	}
	
	@Test
	public void colorTest() {
		assertEquals(tBump.getColor(),Color.BLUE);
		tBump.setColor(Color.RED);
		assertEquals(tBump.getColor(), Color.RED);
	}
	
	@Test
	public void rotateTest() {
		for(int i=0;i<5;i++){
			assertTrue(tBump.getRotation()==i%4);
			tBump.rotate();
		}
	}

	@Test
	public void circlesTest() {
		//0 rotation
		List<Circle> tCircles = tBump.getCircles();
		assertFalse(tCircles==null);
		assertTrue(tCircles.size()==3);
		assertEquals(tCircles.get(0).getCenter(),new Vect(tBump.getX(),tBump.getY()));
		
		//1 rotation
		tBump.rotate();
		tCircles = tBump.getCircles();
		assertFalse(tCircles==null);
		assertTrue(tCircles.size()==3);
		assertEquals(tCircles.get(0).getCenter(),new Vect(tBump.getX(),tBump.getY()));
		
		//2 rotation
		tBump.rotate();
		tCircles = tBump.getCircles();
		assertFalse(tCircles==null);
		assertTrue(tCircles.size()==3);
		assertEquals(tCircles.get(0).getCenter(),new Vect(tBump.getX()+20,tBump.getY()));
		
		//3 rotation
		tBump.rotate();
		tCircles = tBump.getCircles();
		assertFalse(tCircles==null);
		assertTrue(tCircles.size()==3);
		assertEquals(tCircles.get(0).getCenter(),new Vect(tBump.getX(),tBump.getY()));
	}
	
	@Test
	public void lineSegmentTest() {
		//0 rotation
		List<LineSegment> lines = tBump.getLines();
		assertFalse(lines==null);
		assertTrue(lines.size()==3);
		assertEquals(lines.get(0),new LineSegment(tBump.getX(),tBump.getY(),tBump.getX()+20,tBump.getY()));
		
		//1 rotation
		tBump.rotate();
		lines = tBump.getLines();
		assertFalse(lines==null);
		assertTrue(lines.size()==3);
		assertEquals(lines.get(0),new LineSegment(tBump.getX(),tBump.getY(),tBump.getX()+20,tBump.getY()));
		
		//2 rotation
		tBump.rotate();
		lines = tBump.getLines();
		assertFalse(lines==null);
		assertTrue(lines.size()==3);
		assertEquals(lines.get(0),new LineSegment(tBump.getX()+20,tBump.getY(),tBump.getX()+20,tBump.getY()+20));
		
		//3 rotation
		tBump.rotate();
		lines = tBump.getLines();
		assertFalse(lines==null);
		assertTrue(lines.size()==3);
		assertEquals(lines.get(0),new LineSegment(tBump.getX(),tBump.getY(),tBump.getX()+20,tBump.getY()+20));
	}
	
}
