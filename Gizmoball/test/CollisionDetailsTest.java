import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;
import physics.*;

public class CollisionDetailsTest {
    private CollisionDetails cd;
    private Vect v;
    private Bumper b;

    @Before
    public void setUp() {
        v = new Vect(10, 10);
        b = new SquareBumper(10, 10, 0, "Square");
        cd = new CollisionDetails(10.0, v, false, b);
    }

    @Test
    public void testGetTime() {
        Double time = cd.getTime();
        int timeInt = time.intValue();
        assertEquals(timeInt, 10);
    }

    @Test
    public void testGetVelocity() {
        Vect velo = cd.getVelocity();
        assertEquals(new Vect(10, 10), velo);
    }

    @Test
    public void testGetAbsorbed() {
        assertEquals(cd.getAbsorbed(), false);
    }

    @Test
    public void testGetBumper() {
        assertEquals(cd.getBumper(), b);
    }

    @Test
    public void testSetBumper() {
        Bumper b2 = new CircularBumper(25, 25, 0, "Circle");
        cd.setBumper(b2);
        assertEquals(cd.getBumper(), b2);
    }
}
