import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Ball;
import physics.Vect;
import java.awt.Color;
public class BallTest {
	
	private Ball ball;
	
	@Before
	public void setUp() throws Exception {
		ball = new Ball("B2", 200, 200, 200, 200);
	}

	@Test
	public void testVelocity() {
		ball.setVelocity(new Vect(100, 150));
		assertTrue(ball.getVelocity().x()==100);
		assertTrue(ball.getVelocity().y()==150);
	}
	
	@Test
	public void testVelocity2() {
		ball.setVelocity(50, 100);
		assertTrue(ball.getVelocity().x()==50);
		assertTrue(ball.getVelocity().y()==100);
	}
	
	@Test
	public void testCoordinates() {
		ball.setXY(100, 150);
		assertTrue(ball.getX()==100);
		assertTrue(ball.getY()==150);
	}
	
	@Test
	public void testColor(){
		ball.setColor(Color.RED);
		assertEquals(ball.getColor(), Color.RED);
	}
	
	@Test
	public void testMoving(){
		assertTrue(ball.isMoving());
		ball.setMoving(false);
		assertFalse(ball.isMoving());
	}
	
	@Test
	public void testAbsorbed(){
		assertFalse(ball.isAbsorbed());
		ball.setAbsorbed(true);
		assertTrue(ball.isAbsorbed());
	}
	
	@Test
	public void testRadius(){
		assertTrue(ball.getRadius()==5);
		ball.setRadius(10);
		assertTrue(ball.getRadius()==10);
	}
	
	@Test
	public void testCircle(){
		ball.setRadius(5);
		ball.setXY(100, 150);
		assertTrue(ball.getCircle().getRadius()==5);
		assertTrue(ball.getCircle().getCenter().x()==100);
		assertTrue(ball.getCircle().getCenter().y()==150);
	}

	@Test
	public void testGetName() {
		assertEquals(ball.getName(), "B2");
	}

	@Test
	public void testMove() {
		ball.move(100, 100);
		Double ballX = ball.getX();
		int ballXInt = ballX.intValue();
		assertEquals(ballXInt, 110);
	}

	@Test
	public void testReset() {
		ball.move(100, 100);
		ball.reset();
		Double ballX = ball.getX();
		int ballXInt = ballX.intValue();
		assertEquals(ballXInt, 210);
	}



}
