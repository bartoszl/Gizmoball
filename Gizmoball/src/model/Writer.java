package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Writer provides the functionality to save an IGBallModel instance to a file.
 * @author John Watt
 */
public class Writer {

    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private int scale;

    /**
     * Creates an instance of the Writer class, with the scale set to 20. The scale
     * is the factor between the coordinates used in the formal file syntax and the exact pixel values used
     * within the model.
     */
    public Writer () {
        scale = 20;
    }

    /**
     * Store a given IGBallModel in a file named fileName.
     * @param model -> The instance of a concrete class that implementsa the IGBallModel interface
     * @param fileName  -> The name that the file containing the model is to be given
     */
    public void writeModelToFile(IGBallModel model, String fileName) {
        file = new File(fileName + ".txt");
        List<String> syntax;
        try {

            if(!file.exists()) {
                file.createNewFile();
            }

            fileWriter = new FileWriter(file.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);

            /* Write all bumpers */
            for(Bumper bumper : model.getGizmos()) {
                syntax = generateBumperSyntax(bumper);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + "\n");
            }

            /* Write absorber */
            IAbsorber absorber = model.getAbsorber();
            if(absorber != null) {
                syntax = generateAbsorberSyntax(absorber);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + " " + syntax.get(4) + " " + syntax.get(5) + "\n");
            }

            /* Write balls */
            for(Ball ball : model.getBalls()) {
                syntax = generateBallSyntax(ball);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + " " + syntax.get(4) + " " + syntax.get(5) + "\n");
            }

            /* Write rotations */
            for(Bumper bumper : model.getGizmos()) {
                for(String s : generateRotateSyntax(bumper)) {
                    bufferedWriter.write(s + "\n");
                }
            }

            /* Write left and right flippers */
            for(Flipper flipper : model.getFlippers()) {
                syntax = generateFlipperSyntax(flipper);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + "\n");
            }

            /* Write connections */
            for(Connection connection : model.getConnections()) {
                syntax = generateConnectionSyntax(connection);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + "\n");
            }

            /* Write KeyConnectionFlippers */
            for(KeyConnectionFlipper connF : model.getKeyConnectionsFlipper()) {
                syntax = generateKeyConnectionFlipperSyntax(connF);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + " " + syntax.get(4));
            }

            /* Write KeyConnectionAbs */
            for(KeyConnectionAbs connA : model.getKeyConnectionsAbs()) {
                syntax = generateKeyConnectionAbsSyntax(connA);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + " " + syntax.get(4));
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate the file syntax for an absorber.
     * The formal file syntax for an absorber is:
     * Absorber <name> <int-pair> <int-pair>
     * For example: Absorber myAbsorber 1 17 19 19
     * @param absorber  -> The absorber for which the file syntax is to be generated
     * @return  -> A list of strings containing the file syntax for the given absorber
     */
    public List<String> generateAbsorberSyntax(IAbsorber absorber) {
        String gizmoOp = "Absorber",
                name = "ABS",
                x1 = String.valueOf((int) absorber.getXTopLeft() / scale),
                y1 = String.valueOf((int) absorber.getYTopLeft() / scale),
                x2 = String.valueOf((int) absorber.getXBottomRight() / scale),
                y2 = String.valueOf((int)absorber.getYBottomRight() / scale);
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(x1);
        syntax.add(y1);
        syntax.add(x2);
        syntax.add(y2);
        return syntax;
    }

    /**
     * Generate the appropriate file syntax for the given Bumper.
     * @param bumper -> The Bumper to generate the file syntax for
     * @return -> A list of strings containing the file syntax for the given Bumper
     */
    public List<String> generateBumperSyntax(Bumper bumper) {
        if(bumper instanceof SquareBumper) {
            SquareBumper sBumper = (SquareBumper) bumper;
            return generateSquareBumperSyntax(sBumper);
        } else if(bumper instanceof TriangularBumper) {
            TriangularBumper tBumper = (TriangularBumper) bumper;
            return generateTriangularBumperSyntax(tBumper);
        } else if(bumper instanceof CircularBumper) {
            CircularBumper cBumper = (CircularBumper) bumper;
            return generateCircularBumperSyntax(cBumper);
        }
        return null;
    }

    /**
     * Generate the file syntax for a SquareBumper.
     * The formal file syntax for a SquareBumper is:
     * Square <name> <int-pair>
     * For example: Square mySquare 4 5
     * @param bumper -> The SquareBumper for which the file syntax is to be generated
     * @return -> A list of strings containing the file syntax for the given SquareBumper
     */
    public List<String> generateSquareBumperSyntax(SquareBumper bumper) {
        String gizmoOp = "Square",
                xCoordinate = String.valueOf((int) bumper.getX() / scale),
                yCoordinate = String.valueOf((int) bumper.getY() / scale),
                name = "S" + xCoordinate + yCoordinate;
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(xCoordinate);
        syntax.add(yCoordinate);
        return syntax;
    }

    public List<String> generateTriangularBumperSyntax(TriangularBumper bumper) {
        String gizmoOp = "Triangle",
                xCoordinate = String.valueOf((int) bumper.getX() / scale),
                yCoordinate = String.valueOf((int) bumper.getY() / scale),
                name = "T" + xCoordinate + yCoordinate;
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(xCoordinate);
        syntax.add(yCoordinate);
        return syntax;
    }

    public List<String> generateCircularBumperSyntax(CircularBumper bumper) {
        String gizmoOp = "Circle",
                xCoordinate = String.valueOf((int) bumper.getX() / scale),
                yCoordinate = String.valueOf((int) bumper.getY() / scale),
                name = "C" + xCoordinate + yCoordinate;
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(xCoordinate);
        syntax.add(yCoordinate);
        return syntax;
    }

    public List<String> generateBallSyntax(Ball ball) {
        List<String> syntax = new ArrayList<String>();
        String gizmoOp = "Ball",
                xCoordinate = String.valueOf((ball.getX() - 10) / scale),
                yCoordinate = String.valueOf((ball.getY() - 10)/ scale),
                xVelo = String.valueOf(ball.getVelocity().x()),
                yVelo = String.valueOf(ball.getVelocity().y()),
                name = "B" + String.valueOf((int) (ball.getX() - 10) / scale) + String.valueOf((int) (ball.getY() - 10) / scale);
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(xCoordinate);
        syntax.add(yCoordinate);
        syntax.add(xVelo);
        syntax.add(yVelo);
        return syntax;
    }

    public List<String> generateRotateSyntax(Bumper bumper) {
        List<String> syntax = new ArrayList<String>();
        for(int i = 0; i < bumper.getRotation(); i++) {
            syntax.add("Rotate " + bumper.getName());
        }
        return syntax;
    }

    public List<String> generateFlipperSyntax(Flipper flipper) {
        List<String> syntax = new ArrayList<String>();
        String name;
        if(flipper.isLeft()) {
            syntax.add("LeftFlipper");
            name = "LF";
        } else {
            syntax.add("RightFlipper");
            name = "RF";
        }
        syntax.add(name + String.valueOf((int) flipper.getOrigin().x() / scale) + String.valueOf((int) flipper.getOrigin().y() / scale));
        syntax.add(String.valueOf((int) flipper.getOrigin().x() / scale));
        syntax.add(String.valueOf((int) flipper.getOrigin().y() / scale));
        return syntax;
    }

    public List<String> generateConnectionSyntax(Connection connection) {
        ArrayList<String> syntax = new ArrayList<String>();
        syntax.add("Connect");
        syntax.add(connection.getTrigger().getName());
        syntax.add(connection.getFlipper().getName());
        return syntax;
    }

    public List<String> generateKeyConnectionFlipperSyntax(KeyConnectionFlipper conn) {
        ArrayList<String> syntax = new ArrayList<String>();
        syntax.add("KeyConnect");
        syntax.add("key");
        syntax.add(String.valueOf(conn.getKeyID()));
        syntax.add(conn.getUpDown());
        syntax.add(conn.getFlipper().getName());
        return syntax;
    }

    public List<String> generateKeyConnectionAbsSyntax(KeyConnectionAbs conn) {
        ArrayList<String> syntax = new ArrayList<String>();
        syntax.add("KeyConnect");
        syntax.add("key");
        syntax.add(String.valueOf(conn.getKeyID()));
        syntax.add(conn.getUpDown());
        syntax.add(conn.getAbs().getName());
        return syntax;
    }
}
