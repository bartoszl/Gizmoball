import model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

public class ModelLoadTests {
    private ModelLoader ml;
    private File f;

    @Before
    public void setUp() {
        f= new File("test.txt");
        ml = new ModelLoader(f);
    }

    @Test
    public void testCreateCircle() {
        assertEquals(new CircularBumper(80, 60, 0, "C43").getCircles(),
                ml.getModel().getGizmos().get(17).getCircles());
    }

    @Test
    public void testCreateTriangle() {
        assertEquals(new TriangularBumper(20, 20, 0, "T2").getCircles(),
                ml.getModel().getGizmos().get(1).getCircles());
    }

    @Test
    public void testCreateSquare() {
        assertEquals(new SquareBumper(0, 40, 0, "S02").getCircles(),
                ml.getModel().getGizmos().get(2).getCircles());
    }

    @Test
    public void testCreateFlipper() {
        assertEquals(new Flipper(180, 40, true, "LF92").getCircles(),
                ml.getModel().getFlippers().get(0).getCircles());
    }

    @Test
    public void testCreateAbsorber() {
        assertEquals(new Absorber("A0", 0, 380, 400, 400).getCircles(),
                ml.getModel().getAbsorber().getCircles());
    }

    @Test
    public void testCreateBall() {
        assertEquals(new Ball("B", 20, 220, 0, 0).getCircle(),
                ml.getModel().getBalls().get(0).getCircle());
    }

    @Test
    public void testGravity() {
        Double g = ml.getModel().getGravity();
        int gInt = g.intValue();
        assertEquals(gInt, 10);
    }

    @Test
    public void testHorizontalFriction() {
        Double hf = ml.getModel().getFrictionX();
        int hfInt = hf.intValue();
        assertEquals(hfInt, 7);
    }

    @Test
    public void testVerticalFriction() {
        Double vf = ml.getModel().getFrictionY();
        int vfInt = vf.intValue();
        assertEquals(vfInt, 8);
    }
}
