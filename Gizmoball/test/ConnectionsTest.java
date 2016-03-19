import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class ConnectionsTest {
    private CircularBumper b;
    private Flipper f;
    private Connection c;

    @Before
    public void setUp() {
        b = new CircularBumper(100, 100, 0, "circle");
        f = new Flipper(10, 10, true, "flippy flipper");
        c = new Connection(b, f);
    }

    @Test
    public void testGetBumper() {
        assertEquals(b, c.getTrigger());
    }

    @Test
    public void testGetFlipper() {
        assertEquals(f, c.getFlipper());
    }

    @Test
    public void testSetFlipper() {
        Flipper f2 = new Flipper(20, 20, false, "flipper2");
        c.setFlipper(f2);
        assertEquals(f2, c.getFlipper());
    }

}
