import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

public class KeyConnectionsFlipperTest {
    private Flipper f;
    private KeyConnectionFlipper kcf;

    @Before
    public void setUp() {
        f = new Flipper(10, 10, false, "flipper");
        kcf = new KeyConnectionFlipper(100, f, "down");
    }

    @Test
    public void testGetKeyID() {
        assertEquals(kcf.getKeyID(), 100);
    }

    @Test
    public void testGetFlipper() {
        assertEquals(kcf.getFlipper(), f);
    }

    @Test
    public void testSetFlipper() {
        Flipper f2 = new Flipper(15, 15, true, "flippa");
        kcf.setFlipper(f2);
        assertEquals(kcf.getFlipper(), f2);
    }

    @Test
    public void testGetUpDown() {
        assertEquals(kcf.getUpDown(), "down");
    }
}
