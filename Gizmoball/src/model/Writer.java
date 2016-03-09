package model;

import java.io.BufferedWriter;
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
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private int scale;

    public Writer () {
        scale = 20;
    }

    public void writeModelToFile(IGBallModel model, String fileName) {
        file = new File(fileName + ".txt");
        List<String> syntax;
        try {
            //file.createNewFile();

            if(!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
            /* Write all bumpers */
            for(Bumper bumper : model.getGizmos()) {
                syntax = convertBumperToFileSyntax(bumper);
                bufferedWriter.write(syntax.get(0) + " " + syntax.get(1) + " " + syntax.get(2) + " " + syntax.get(3) + "\n");
            }

            /* Write absorber */
            IAbsorber absorber = model.getAbsorber();
            if(absorber != null) {
                syntax = convertAbsorberToFileSyntax(absorber);
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
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> convertAbsorberToFileSyntax(IAbsorber absorber) {
        String gizmoOp = "Absorber",
                name = absorber.getName(),
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
                xCoordinate = String.valueOf((int) bumper.getX() / scale),
                yCoordinate = String.valueOf((int) bumper.getY() / scale),
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
                xCoordinate = String.valueOf((int) bumper.getX() / scale),
                yCoordinate = String.valueOf((int) bumper.getY() / scale),
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
                xCoordinate = String.valueOf((int) bumper.getX() / scale),
                yCoordinate = String.valueOf((int) bumper.getY() / scale),
                name = bumper.getName();
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
                name = ball.getName();
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
        if(flipper.isLeft()) {
            syntax.add("LeftFlipper");
        } else {
            syntax.add("RightFlipper");
        }
        syntax.add(flipper.getName());
        syntax.add(String.valueOf((int) flipper.getOrigin().x()));
        syntax.add(String.valueOf((int) flipper.getOrigin().y()));
        return syntax;
    }
}
