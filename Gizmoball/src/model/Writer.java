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
        try {
            file.createNewFile();
            writer = new FileWriter(file);

            for(Bumper bumper : model.getGizmos()) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


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
        return null;
    }
}
