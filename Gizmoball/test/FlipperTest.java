import org.junit.Before;
import model.*;
import org.junit.*;
import physics.*;

import java.awt.*;

import static org.junit.Assert.*;

public class FlipperTest {
    private Flipper flipper;

    @Before
    public void setUp() {
        flipper = new Flipper(0, 0, true, Color.RED, "F1");
    }

    @Test
    public void testMove() {
        flipper.move(1, 1);
        Double d = flipper.getOrigin().x();
        assertEquals(d.intValue(), 1);
    }

    @Test
    public void testRotatePerTick() {
        flipper.setMovement(Flipper.Movement.FORWARDS);
        Angle a = flipper.rotatePerTick(Angle.DEG_90);
        assertEquals(a, Angle.DEG_90.minus(new Angle(0.95)));
    }

    @Test
    public void testFullRotation() {
        flipper.setMovement(Flipper.Movement.FORWARDS);
        Angle a = flipper.rotatePerTick(Angle.DEG_90);
        Angle b = flipper.rotatePerTick(a);
        assertEquals(b, Angle.ZERO);
    }

    @Test
    public void testBackLimit() {
        flipper.setMovement(IFlipper.Movement.BACKWARDS);
        flipper.setPosition(IFlipper.Position.VERTICAL);
        Angle a = flipper.rotatePerTick(Angle.DEG_90);
        assertEquals(a, Angle.ZERO);
    }

    @Test
    public void testFrontLimit() {
        flipper.setMovement(IFlipper.Movement.FORWARDS);
        flipper.setPosition(IFlipper.Position.HORIZONTAL);
        Angle a = flipper.rotatePerTick(Angle.DEG_90);
        assertEquals(a, Angle.ZERO);
    }
}