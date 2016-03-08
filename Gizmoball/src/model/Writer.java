package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by John Watt on 08/03/2016.
 */
public class Writer {

    private File file;
    private FileWriter writer;

    public Writer () {

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

    public String[] convertBumperToFileSyntax(Bumper bumper) {
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

    public String[] generateSquareBumperSyntax(SquareBumper bumper) {
        return null;
    }

    public String[] generateTriangularBumperSyntax(TriangularBumper bumper) {
        return null;
    }

    public String[] generateCircularBumperSyntax(CircularBumper bumper) {
        return null;
    }
}
