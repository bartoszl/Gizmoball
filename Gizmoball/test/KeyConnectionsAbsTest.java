import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;
import physics.*;

public class KeyConnectionsAbsTest {
    private IAbsorber abs;
    private KeyConnectionAbs kca;

    @Before
    public void setUp() {
        abs = new Absorber("Absorber", 10, 10, 10, 10);
        kca = new KeyConnectionAbs(11, abs, "up");
    }

    @Test
    public void testGetKeyID() {
        assertEquals(kca.getKeyID(), 11);
    }

    @Test
    public void testGetAbs() {
        assertEquals(kca.getAbs(), abs);
    }

    @Test
    public void testGetUpDown() {
        assertEquals(kca.getUpDown(), "up");
    }
}
