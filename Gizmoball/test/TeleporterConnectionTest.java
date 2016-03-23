import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Ball;
import model.Bumper;
import model.IBall;
import model.TeleporterBumper;
import model.TeleporterConnection;

public class TeleporterConnectionTest {
	TeleporterConnection tpConnect;
	Bumper tp1, tp2;
	
	@Before
	public void setUp(){
		tp1 = new TeleporterBumper(0,0,0,"tp1");
		tp2 = new TeleporterBumper(20,20,0,"tp2");
		tpConnect = new TeleporterConnection(tp1,tp2);
	}
	
	@Test
	public void getConnectionsTest() {
		List<Bumper> tpBumps = tpConnect.getConnection();
		assertTrue(tpBumps.size()==2);
		assertTrue(tpBumps.contains(tp1));
		assertTrue(tpBumps.contains(tp2));
	}
	
	@Test
	public void coordinatesTest() {
		tpConnect.setNewCoordinatesOfCollidedBall(0, 0);
		assertTrue(tpConnect.getNewX()==0);
		assertTrue(tpConnect.getNewY()==0);
	}

	@Test
	public void ballTest() {
		IBall ball = new Ball("B2", 200, 200, 200, 200);
		tpConnect.setBall(ball);
		assertEquals(tpConnect.getBall(),ball);
	}
}
