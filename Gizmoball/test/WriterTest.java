import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<String> actual = writer.generateSquareBumperSyntax(sBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Square");
        expected.add("S1");
        expected.add("1.0");
        expected.add("2.0");
        assertEquals(actual, expected);
    }

    @Test
    public void testGenerateTriangularBumperSyntax() {
        TriangularBumper tBumper = new TriangularBumper(60, 60, 0, "T1");
        List<String> actual = writer.generateTriangularBumperSyntax(tBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Triangle");
        expected.add("T1");
        expected.add("3.0");
        expected.add("3.0");
        assertEquals(actual, expected);
    }

    @Test
    public void testGenerateCircularBumperSyntax() {
        CircularBumper cBumper = new CircularBumper(80, 80, 0, "C1");
        List<String> actual = writer.generateCircularBumperSyntax(cBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Circle");
        expected.add("C1");
        expected.add("4.0");
        expected.add("4.0");
        assertEquals(actual, expected);
    }

    @Test
    public void testConvertAbsorberToFileSyntax() {
        Absorber absorber = new Absorber("ABS", 20, 20, 80, 80);
        List<String> actual = writer.convertAbsorberToFileSyntax(absorber);
        List<String> expected = new ArrayList<String>();
        expected.add("Absorber");
        expected.add("ABS");
        expected.add("1.0");
        expected.add("1.0");
        expected.add("4.0");
        expected.add("4.0");
        assertEquals(actual, expected);
    }
}