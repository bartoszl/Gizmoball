import model.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ModelLoader {

    private Reader fileReader;
    private iGBallModel model;
    private List<String[]> commands;
    private int scale;

    public ModelLoader() {
        scale = 20;
        fileReader = new Reader();
        model = new GBallModel();

        try {
            commands =  fileReader.readFromFile("text.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeModel() {
        for(String[] command : commands) {
            String gizmoOp = command[0];
            switch (gizmoOp) {
                case "Triangle" :
                    model.addGizmo(createTriangularBumper(command));
                    break;

                case "Square" :
                    model.addGizmo((createSquareBumper(command)));
                    break;

                case "Circle" :
                    model.addGizmo(createCircularBumper(command));
                    break;

                case "LeftFlipper" :
                case "RightFlipper" :
                    model.addGizmo(createFlipper(command));
                    break;

                case "Absorber" :
                    model.addAbsorber(createAbsorber(command));
                    break;

                case "Ball" :
                    model.addBall(createBall(command));
                    break;
            }
        }
    }

    private iGizmo createTriangularBumper(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        String name = command[1];
        return new TriangularBumper(xCoord, yCoord, name);
    }

    private iGizmo createSquareBumper(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        String name = command[1];
        return new SquareBumper(xCoord, yCoord, name);
    }

    private iGizmo createCircularBumper(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        String name = command[1];
        return new CircularBumper(xCoord, yCoord, 10, name);
    }

    private iGizmo createFlipper(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        String name = command[1];
        if(command[0].equals("LeftFlipper")) {
            return new Flipper(xCoord, yCoord, FlipperOrientation.LEFT, name);
        }
        return new Flipper(xCoord, yCoord, FlipperOrientation.RIGHT, name);
    }

    private iAbsorber createAbsorber(String[] command) {
        double leftX = Double.parseDouble(command[2]) * scale;
        double rightX = Double.parseDouble(command[4]) * scale;
        double topY = Double.parseDouble(command[3]) * scale;
        double bottomY = Double.parseDouble(command[5]) * scale;
        String name = command[1];
        return new Absorber(name, leftX, rightX, topY, bottomY);
    }

    private iBall createBall(String[] command) {
        double xCoord = Double.parseDouble(command[2]);
        double yCoord = Double.parseDouble(command[3]);
        double xVelo = Double.parseDouble(command[4]);
        double yVelo = Double.parseDouble(command[5]);
        String name = command[1];
        return new Ball(xCoord, yCoord, xVelo, yVelo, name);
    }
}
