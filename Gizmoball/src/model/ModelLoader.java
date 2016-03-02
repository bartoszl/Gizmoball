package model;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ModelLoader {

    private Reader fileReader;
    private GBallModel model;
    private List<String[]> commands;
    private int scale;

    public ModelLoader(File filename) {
        scale = 20;
        fileReader = new Reader();
        model = new GBallModel();

        try {
            commands =  fileReader.readFromFile(filename);
            makeModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GBallModel getModel() {
        return model;
    }

    public void makeModel() {
        for(String[] command : commands) {
            String gizmoOp = command[0];
            switch (gizmoOp) {
                case "Triangle" :
                    //model.addGizmo(createTriangularBumper(command));
                    break;

                case "Square" :
                    //model.addGizmo((createSquareBumper(command)));
                    break;

                case "Circle" :
                    createCircularBumper(command);
                    break;

                /*case "LeftFlipper" :
                case "RightFlipper" :
                    model.addGizmo(createFlipper(command));
                    break;*/

                case "Absorber" :
                    //model.addAbsorber(createAbsorber(command));
                    break;

                case "Ball" :
                    //model.addBall(createBall(command));
                    break;

                case "Gravity" :
                    model.setGravity(getGravityValue(command));
                    break;

                case "Friction" :
                    model.setFriction(getHorizontalFriction(command), getVerticalFriction(command));
                    break;

                case "Rotate" :
                    applyRotation(command);

                    //case "Connect" :
                    //model.addConnection(createConnection(command));
                    //break;

                    //case "KeyConnect" :
                    //    model.addKeyConnection(createKeyConnection(command));
                    //    break;
            }
        }
    }

    private boolean applyRotation(String[] command) {
        String gizmoName = command[1];
        if(model.getGizmo(gizmoName) == null) {
            return false;
        }
        model.getGizmo(gizmoName).rotate();
        return true;
    }

    private iGizmo createTriangularBumper(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        String name = command[1];
        return new TriangularBumper(xCoord, yCoord, name);
    }

    private iGizmo createSquareBumper(String[] command) {
        //double xCoord = Double.parseDouble(command[2]) * scale;
        //double yCoord = Double.parseDouble(command[3]) * scale;
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        return new SquareBumper(xCoord, yCoord, name);
    }

    private void createCircularBumper(String[] command) {
        model.addCircularBumper(Integer.parseInt(command[2]), Integer.parseInt(command[3]), command[1]);
    }

    /*private IFlipper createFlipper(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        String name = command[1];
        if(command[0].equals("LeftFlipper")) {
            return new Flipper(xCoord, yCoord, FlipperOrientation.LEFT, name);
        }
        Flipper f = new  Flipper(xCoord, yCoord, FlipperOrientation.RIGHT, name);
        return f;
    }*/

    private IAbsorber createAbsorber(String[] command) {
        double leftX = Double.parseDouble(command[2]) * scale;
        double rightX = Double.parseDouble(command[4]) * scale;
        double topY = Double.parseDouble(command[3]) * scale;
        double bottomY = Double.parseDouble(command[5]) * scale;
        String name = command[1];
        return new Absorber(leftX, rightX, topY, bottomY);
    }

    private IBall createBall(String[] command) {
        double xCoord = Double.parseDouble(command[2]);
        double yCoord = Double.parseDouble(command[3]);
        double xVelo = Double.parseDouble(command[4]);
        double yVelo = Double.parseDouble(command[5]);
        String name = command[1];
        return new Ball(name, xCoord, yCoord, xVelo, yVelo);
    }

    private double getGravityValue(String[] command) {
        return Double.parseDouble(command[1]);
    }

    private double getHorizontalFriction(String[] command) {
        return Double.parseDouble(command[1]);
    }

    private double getVerticalFriction(String[] command) {
        return Double.parseDouble(command[2]);
    }

    private Connection createConnection(String[] command) {
        String connectingFrom = command[1];
        String connectingTo = command[2];
        return new Connection(connectingFrom, connectingTo);
    }

    private KeyConnection createKeyConnection(String[] command) {
        System.out.println(command[1]);
        int keyID = Integer.parseInt(command[1]);
        String connectingTo = command[2];
        return new KeyConnection(keyID, connectingTo);
    }
}