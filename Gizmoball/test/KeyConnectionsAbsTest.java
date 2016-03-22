import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

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
    
    @Test
    public void testEquals() {
    	assertFalse(kca.equals(null));
    	assertFalse(kca.equals(new Object()));
    	assertFalse(kca.equals(new KeyConnectionAbs(10, abs, "up")));
    	assertFalse(kca.equals(new KeyConnectionAbs(12, abs, "up")));
    	assertFalse(kca.equals(new KeyConnectionAbs(11, new Absorber("abs", 0, 60, 80, 80), "up")));
    	assertFalse(kca.equals(new KeyConnectionAbs(10, abs, "down")));
    	assertTrue(kca.equals(new KeyConnectionAbs(11, abs, "up")));
    }
}
