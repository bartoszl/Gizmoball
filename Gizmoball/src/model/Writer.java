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

    public String[] convertToFileSyntax(Bumper bumper) {

        return null;
    }
}
