package model;

import java.awt.*;
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
                    createTriangularBumper(command);
                    break;

                case "Square" :
                    //model.addGizmo((createSquareBumper(command)));
                    createSquareBumper(command);
                    break;

                case "Circle" :
                    createCircularBumper(command);
                    break;

                case "LeftFlipper" :
                case "RightFlipper" :
                    //model.addGizmo(createFlipper(command));
                    createFlipper(command);
                    break;

                case "Absorber" :
                    createAbsorber(command);
                    break;

                case "Ball" :
                    createBall(command);
                    break;

                case "Gravity" :
                    model.setGravity(getGravityValue(command));
                    break;

                case "Friction" :
                    model.setFriction(getHorizontalFriction(command), getVerticalFriction(command));
                    break;

                case "Rotate" :
                    applyRotation(command);
                    break;

                case "Connect" :
                    createConnection(command);
                    break;

                case "KeyConnect" :
                    model.addKeyConnection(createKeyConnection(command));
                    break;
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

    private void createTriangularBumper(String[] command) {
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        model.addTriangularBumper(xCoord, yCoord,0, name);
    }

    private void createSquareBumper(String[] command) {
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        model.addSquareBumper(xCoord, yCoord, 0, name);
    }

    private void createCircularBumper(String[] command) {
        model.addCircularBumper(Integer.parseInt(command[2]) * scale, Integer.parseInt(command[3]) * scale, 0, command[1]);
    }

    private void createFlipper(String[] command) {
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        if(command[0].equals("LeftFlipper")) {
            model.addFlipper(xCoord, yCoord, true, name);
        } else {
            model.addFlipper(xCoord, yCoord, false, name);
        }
    }

    private void createAbsorber(String[] command) {
        int leftX = Integer.parseInt(command[2]) * scale;
        int rightX = Integer.parseInt(command[4]) * scale;
        int topY = Integer.parseInt(command[3]) * scale;
        int bottomY = Integer.parseInt(command[5]) * scale;
        String name = command[1];
        model.addAbsorber(name, leftX, topY, rightX, bottomY);
    }

    private void createBall(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        double xVelo = Double.parseDouble(command[4]);
        double yVelo = Double.parseDouble(command[5]);
        String name = command[1];
        model.addBall(name, xCoord, yCoord, xVelo, yVelo);
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

    private void createConnection(String[] command) {
        String circularBumperName = command[1];
        String flipperName = command[2];
        model.addConnection(circularBumperName, flipperName);
    }

    private void createKeyConnection(String[] command) {
        if(model.getObjectTypeForKeyConnection(command[4]).equals("Absorber")) {
            createKeyConnectionAbs(command);
        } else if(model.getObjectTypeForKeyConnection(command[4]).equals("Flipper")) {
            createKeyConnectionFlipper(command);
        }
    }

    private void createKeyConnectionFlipper(String[] command) {
        Integer keyID = Integer.parseInt(command[2]);
        String upDown = command[3];
        String flipperName = command[4];
        Flipper flipper = model.getFlipper(flipperName);
        model.addKeyConnectionFlipper(keyID, flipper, upDown);
    }

    private KeyConnectionAbs createKeyConnectionAbs(String[] command) {
        Integer connectingFrom = Integer.parseInt(command[1]);
        String connectingTo = command[2];
        Absorber abs = null;


        if(model.getAbsorber().getName().equals(connectingTo)) {
            return new KeyConnectionAbs(connectingFrom, model.getAbsorber(), "down");
        } else {
            return null;
        }
    }
}