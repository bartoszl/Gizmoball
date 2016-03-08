import model.CircularBumper;
import model.SquareBumper;
import model.TriangularBumper;
import model.Writer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by John Watt on 08/03/2016.
 */

public class WriterTest {

    private Writer writer;

    @Before
    public void setUp() throws Exception {
        writer = new Writer();
    }

    @Test
    public void testGenerateSquareBumperSyntax() {
        SquareBumper sBumper = new SquareBumper(20, 40, 0, "S1");
        String[] actual = writer.generateSquareBumperSyntax(sBumper);
        String[] expected = new String[]{"Square", "S1", "20", "40"};
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testGenerateTriangularBumperSyntax() {
        TriangularBumper tBumper = new TriangularBumper(60, 60, 0, "T1");
        String[] actual = writer.generateTriangularBumperSyntax(tBumper);
        String[] expected = new String[]{"Triangle", "T1", "60", "60"};
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testGenerateCircularBumperSyntax() {
        CircularBumper cBumper = new CircularBumper(80, 80, 0, "C1");
        String[] actual = writer.generateCircularBumperSyntax(cBumper);
        String[] expected = new String[]{"Circle", "C1", "80", "80"};
        assertTrue(expected.equals(actual));
    }
}