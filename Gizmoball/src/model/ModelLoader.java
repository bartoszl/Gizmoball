package model;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The ModelLoader class provides the functionality to create a GBallModel
 * based on the contents of a file (that follows the formal file syntax rules).
 * @author John Watt
 */
public class ModelLoader {

    private Reader fileReader;
    private GBallModel model;
    private List<String[]> commands;
    private int scale;

    /**
     * @param filename The name of the file that the model is to be created from
     */
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

    /**
     * Get the model created using the contents of the given file
     * @return The model created using the contents of the given file
     */
    public GBallModel getModel() {
        return model;
    }

    /**
     * Build the model up using all of the (valid) commands read from the file
     */
    public void makeModel() {
        for(String[] command : commands) {
            String gizmoOp = command[0];
            switch (gizmoOp) {
                case "Triangle" :
                    createTriangularBumper(command);
                    break;

                case "Square" :
                    createSquareBumper(command);
                    break;

                case "Circle" :
                    createCircularBumper(command);
                    break;

                case "LeftFlipper" :
                case "RightFlipper" :
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

                case "TeleporterConnect" :
                    break;

                case "KeyConnect" :
                    createKeyConnection(command);
                    break;

                case "ABSNOTCONNECTED" :
                    model.getAbsorber().setConnectedToItself(false);
                    break;

                case "Teleporter" :
                    createTeleporterBumper(command);
                    break;
            }
        }
    }

    /**
     * Perform a rotation on the given bumper or flipper
     * @param command The command that specifies the bumper or flipper to rotate
     * @return True if the rotation was successful - the rotation will be successful if the bumper/flipper exists in the model,
     * or false if it could not be rotated
     */
    private boolean applyRotation(String[] command) {
        String name = command[1];
        if(model.getBumper(name) != null) {
            model.getBumper(name).rotate();
            return true;
        }

        if(model.getFlipper(name) != null) {
            model.getFlipper(name).rotate();
            return true;
        }
        return false;
    }

    /**
     * Add a TeleporterBumper to the model
     * @param command The command that specifies the details of the TeleporterBumper
     */
    private void createTeleporterBumper(String[] command) {
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        model.addTeleporterBumper(xCoord, yCoord, 0, name);
    }

    /**
     * Add a TriangularBumper to the model
     * @param command The command that specifies the details of the TriangularBumper
     */
    private void createTriangularBumper(String[] command) {
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        model.addTriangularBumper(xCoord, yCoord,0, name);
    }

    /**
     * Add a SquareBumper to the model
     * @param command The command that specifies the details of the SquareBumper
     */
    private void createSquareBumper(String[] command) {
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        model.addSquareBumper(xCoord, yCoord, 0, name);
    }

    /**
     * Add a CircularBumper to the model
     * @param command The command that specifies the details of the CircularBumper
     */
    private void createCircularBumper(String[] command) {
        int xCoord = Integer.parseInt(command[2]) * scale;
        int yCoord = Integer.parseInt(command[3]) * scale;
        String name = command[1];
        model.addCircularBumper(xCoord, yCoord, 0, name);
    }

    /**
     * Add a Flipper to the model
     * @param command The command that specifies the details of the Flipper
     */
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

    /**
     * Add an Absorber to the model
     * @param command The command that specifies the details of the Absorber
     */
    private void createAbsorber(String[] command) {
        int leftX = Integer.parseInt(command[2]) * scale;
        int rightX = Integer.parseInt(command[4]) * scale;
        int topY = Integer.parseInt(command[3]) * scale;
        int bottomY = Integer.parseInt(command[5]) * scale;
        String name = command[1];
        model.addAbsorber(name, leftX, topY, rightX, bottomY);
    }

    /**
     * Add a Ball to the model
     * @param command The command that specifies the details of the Ball
     */
    private void createBall(String[] command) {
        double xCoord = Double.parseDouble(command[2]) * scale;
        double yCoord = Double.parseDouble(command[3]) * scale;
        double xVelo = Double.parseDouble(command[4]);
        double yVelo = Double.parseDouble(command[5]);
        String name = command[1];
        model.addBall(name, xCoord, yCoord, xVelo, yVelo);
    }

    /**
     * Get the value for gravity from the command for gravity
     * @param command The command that specifies the details for gravity
     * @return The value for gravity
     */
    private double getGravityValue(String[] command) {
        return Double.parseDouble(command[1]);
    }

    /**
     * Get the value for horizontal friction
     * @param command The command read from the file that specifies the details for horizontal friction
     * @return The value for horizontal friction
     */
    private double getHorizontalFriction(String[] command) {
        return Double.parseDouble(command[1]);
    }

    /**
     * Get the value for vertical friction
     * @param command The command that specifies the details for vertical friction
     * @return The value for vertical friction
     */
    private double getVerticalFriction(String[] command) {
        return Double.parseDouble(command[2]);
    }

    /**
     * Add a Connection to the model
     * @param command The command that specifies the details of the Connection
     */
    private void createConnection(String[] command) {
        String circularBumperName = command[1];
        String flipperName = command[2];
        model.loadConnection(circularBumperName, flipperName);
    }

    /**
     * Add a KeyConnection to the model
     * @param command The command that specifies the details of the KeyConnection
     */
    private void createKeyConnection(String[] command) {
        if(model.getObjectTypeForKeyConnection(command[4]).equals("Absorber")) {
            createKeyConnectionAbs(command);
        } else if(model.getObjectTypeForKeyConnection(command[4]).equals("Flipper")) {
            createKeyConnectionFlipper(command);
        }
    }

    /**
     * Add a KeyConnectionFlipper, i.e. a key connection to a Flipper, to the model
     * @param command The command that specifies the details of the KeyConnection
     */
    private void createKeyConnectionFlipper(String[] command) {
        Integer keyID = Integer.parseInt(command[2]);
        String upDown = command[3];
        String flipperName = command[4];
        Flipper flipper = model.getFlipper(flipperName);
        if(flipper != null) {
            model.addKeyConnectionFlipper(keyID, flipper, upDown);
        }
    }

    /**
     * Add a KeyConnectionAbs, i.e. a key connection to an absorber, to the model
     * @param command The command that specifies the details of the KeyConnection
     */
    private void createKeyConnectionAbs(String[] command) {
        Integer keyID = Integer.parseInt(command[2]);
        String upDown = command[3];
        String absorberName = command[4];
        Absorber absorber = model.getAbsorber();
        if(absorber != null) {
            if(absorber.getName().equals(absorberName)) {
                model.addKeyConnectionAbs(keyID, absorber, upDown);
            }
        }
    }
}