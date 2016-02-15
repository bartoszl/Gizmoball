package model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class GBallModelTest {

    private iGBallModel model;

    @Test
    public void testAddGizmo_TwoCircularBumpersSameCoordinates_Expected_False() {
        model = new GBallModel();
        CircularBumper c1 = new CircularBumper(5,5,10,"C1");
        CircularBumper c2 = new CircularBumper(5,5,10,"C2");
        model.addGizmo(c1);
        boolean actual = model.addGizmo(c2);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testAddGizmo_TwoTriangularBumpersSameCoordinates_Expected_False() {
        model = new GBallModel();
        TriangularBumper t1 = new TriangularBumper(10,10, "T1");
        TriangularBumper t2 = new TriangularBumper(10,10, "T2");
        model.addGizmo(t1);
        boolean actual = model.addGizmo(t2);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testAddGizmo_TwoSquareBumpersSameCoordinates_Expected_False() {
        model = new GBallModel();

    }

    @Test
    public void testAddAbsorber() throws Exception {

    }

    @Test
    public void testAddBall() throws Exception {

    }
}