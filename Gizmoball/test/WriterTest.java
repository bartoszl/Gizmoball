import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        SquareBumper sBumper = new SquareBumper(20, 40, 0, "square");
        List<String> actual = writer.generateSquareBumperSyntax(sBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Square");
        expected.add("S12");
        expected.add("1");
        expected.add("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateTriangularBumperSyntax() {
        TriangularBumper tBumper = new TriangularBumper(60, 60, 0, "triangle");
        List<String> actual = writer.generateTriangularBumperSyntax(tBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Triangle");
        expected.add("T33");
        expected.add("3");
        expected.add("3");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateCircularBumperSyntax() {
        CircularBumper cBumper = new CircularBumper(80, 80, 0, "circle");
        List<String> actual = writer.generateCircularBumperSyntax(cBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Circle");
        expected.add("C44");
        expected.add("4");
        expected.add("4");
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertAbsorberToFileSyntax() {
        Absorber absorber = new Absorber("ABS", 20, 20, 80, 80);
        List<String> actual = writer.generateAbsorberSyntax(absorber);
        List<String> expected = new ArrayList<String>();
        expected.add("Absorber");
        expected.add("ABS");
        expected.add("1");
        expected.add("1");
        expected.add("4");
        expected.add("4");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateBallSyntax() {
        Ball ball = new Ball("ball", 100, 100, 50, 50);
        List<String> actual = writer.generateBallSyntax(ball);
        List<String> expected = new ArrayList<String>();
        expected.add("Ball");
        expected.add("B55");
        expected.add("5.0");
        expected.add("5.0");
        expected.add("50.0");
        expected.add("50.0");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateRotationSyntax_0_Rotations() {
        SquareBumper squareBumper = new SquareBumper(100, 100, 0, "S1");
        List<String> actual = writer.generateRotateSyntax(squareBumper);
        List<String> expected = new ArrayList<String>();
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateRotationSyntax_1_Rotation() {
        SquareBumper squareBumper = new SquareBumper(100, 100, 1, "S1");
        List<String> actual = writer.generateRotateSyntax(squareBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Rotate S1");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateRotationSyntax_2_Rotations() {
        SquareBumper squareBumper = new SquareBumper(100, 100, 2, "S1");
        List<String> actual = writer.generateRotateSyntax(squareBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Rotate S1");
        expected.add("Rotate S1");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateRotationSyntax_3_Rotations() {
        SquareBumper squareBumper = new SquareBumper(100, 100, 3, "S1");
        List<String> actual = writer.generateRotateSyntax(squareBumper);
        List<String> expected = new ArrayList<String>();
        expected.add("Rotate S1");
        expected.add("Rotate S1");
        expected.add("Rotate S1");
        assertEquals(expected, actual);
    }



    @Test
    public void testGenerateFlipperSyntax_LeftFlipper() {
        Flipper flipper = new Flipper(180, 40, true, "flipper");
        List<String> actual = writer.generateFlipperSyntax(flipper);
        List<String> expected = new ArrayList<String>();
        expected.add("LeftFlipper");
        expected.add("LF92");
        expected.add("9");
        expected.add("2");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateFlipperSyntax_RightFlipper() {
        Flipper flipper = new Flipper(200, 60, false, "flipper");
        List<String> actual = writer.generateFlipperSyntax(flipper);
        List<String> expected = new ArrayList<String>();
        expected.add("RightFlipper");
        expected.add("RF103");
        expected.add("10");
        expected.add("3");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateConnectionSyntax() {
        CircularBumper cBumper = new CircularBumper(80, 80, 0, "C1");
        Flipper flipper = new Flipper(10, 3, false, "RF103");
        Connection connection = new Connection(cBumper, flipper);
        List<String> actual = writer.generateConnectionSyntax(connection);
        List<String> expected = new ArrayList<String>();
        expected.add("Connect");
        expected.add("C1");
        expected.add("RF103");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenertaeKeyConnectionFlipperSyntax() {
        Flipper flipper = new Flipper(10, 3, false, "RF103");
        KeyConnectionFlipper conn = new KeyConnectionFlipper(57, flipper, "down");
        List<String> actual = writer.generateKeyConnectionFlipperSyntax(conn);
        List<String> expected = new ArrayList<String>();
        expected.add("KeyConnect");
        expected.add("key");
        expected.add("57");
        expected.add("down");
        expected.add("RF103");
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateKeyConnectionAbsSyntax() {
        Absorber abs = new Absorber("ABS1", 100, 100, 200, 200);
        KeyConnectionAbs conn = new KeyConnectionAbs(50, abs, "down");
        List<String> actual = writer.generateKeyConnectionAbsSyntax(conn);
        List<String> expected = new ArrayList<String>();
        expected.add("KeyConnect");
        expected.add("key");
        expected.add("50");
        expected.add("down");
        expected.add("ABS1");
        assertEquals(expected, actual);
    }

    @Test
    public void testWriteModelToFile() {
        GBallModel m = new GBallModel();
        m.addTriangularBumper(40, 40, 0, "T22");
        m.addBall("Ball1", 20, 20, 20, 20);
        writer.writeModelToFile(m, "newFile");
        File file1 = new File("newFile.txt");
        File file2 = new File("testWriter.txt");
        List<String> content1 = new ArrayList<>();
        List<String> content2 = new ArrayList<>();
        try {
            content1 = Files.readAllLines(file1.toPath(), Charset.defaultCharset());
            content2 = Files.readAllLines(file2.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(content1, content2);
    }
}