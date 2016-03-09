package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John Watt on 08/03/2016.
 */
public class Writer {

    private File file;
    private FileWriter writer;
    private int scale;

    public Writer () {
        scale = 20;
    }

    public void writeModelToFile(IGBallModel model, String fileName) {
        file = new File(fileName + ".txt");
        List<String> syntax;
        try {
            file.createNewFile();
            writer = new FileWriter(file);

            /* Write all bumpers */
            for(Bumper bumper : model.getGizmos()) {
                syntax = convertBumperToFileSyntax(bumper);
                writer.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3));
            }

            /* Write absorber */
            IAbsorber absorber = model.getAbsorber();
            syntax = convertAbsorberToFileSyntax(absorber);
            writer.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + " " + syntax.get(4) + " " + syntax.get(5));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<String> convertAbsorberToFileSyntax(IAbsorber absorber) {
        String gizmoOp = "Absorber",
                name = absorber.getName(),
                x1 = String.valueOf(absorber.getXTopLeft() / scale),
                y1 = String.valueOf(absorber.getYTopLeft() / scale),
                x2 = String.valueOf(absorber.getXBottomRight() / scale),
                y2 = String.valueOf(absorber.getYBottomRight() / scale);
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(x1);
        syntax.add(y1);
        syntax.add(x2);
        syntax.add(y2);
        return syntax;
    }

    public List<String> convertBumperToFileSyntax(Bumper bumper) {
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

    public List<String> generateSquareBumperSyntax(SquareBumper bumper) {
        String gizmoOp = "Square",
                xCoordinate = String.valueOf(bumper.getX() / scale),
                yCoordinate = String.valueOf(bumper.getY() / scale),
                name = bumper.getName();
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(xCoordinate);
        syntax.add(yCoordinate);
        return syntax;
    }

    public List<String> generateTriangularBumperSyntax(TriangularBumper bumper) {
        String gizmoOp = "Triangle",
                xCoordinate = String.valueOf(bumper.getX() / scale),
                yCoordinate = String.valueOf(bumper.getY() / scale),
                name = bumper.getName();
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(xCoordinate);
        syntax.add(yCoordinate);
        return syntax;
    }

    public List<String> generateCircularBumperSyntax(CircularBumper bumper) {
        String gizmoOp = "Circle",
                xCoordinate = String.valueOf(bumper.getX() / scale),
                yCoordinate = String.valueOf(bumper.getY() / scale),
                name = bumper.getName();
        List<String> syntax = new ArrayList<String>();
        syntax.add(gizmoOp);
        syntax.add(name);
        syntax.add(xCoordinate);
        syntax.add(yCoordinate);
        return syntax;
    }
}
