import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Walls;
import physics.LineSegment;

public class WallsTest {
	private Walls walls;

	@Before
	public void setUp() throws Exception {
		walls = new Walls(0,0,20,20);
	}

	@Test
	public void linesTest() {
		List<LineSegment> lines = walls.getLines();
		assertFalse(lines==null);
		assertTrue(lines.size()==4);
		assertEquals(lines.get(0),new LineSegment(0,0,20,0));
		assertEquals(lines.get(1),new LineSegment(20,0,20,20));
		assertEquals(lines.get(2),new LineSegment(20,20,0,20));
		assertEquals(lines.get(3),new LineSegment(0,0,0,20));
	}

}
