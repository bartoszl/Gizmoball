import model.GBallModel;
import model.IGBallModel;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GBallModelTest {

    private IGBallModel model;

    @Before
    public void setUp() {
        model = new GBallModel();
    }

    @Test
    public void testAddCircularBumperNameUnique() {
        boolean actual = model.addCircularBumper(200, 200, 0, "C1");
        assertThat(actual, is(equalTo(true)));
    }

}