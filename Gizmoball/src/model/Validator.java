package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Validator class provides the functionality to validate the commands read from the file
 * @author John Watt
 */
public class Validator {

    private List<String> identifiers;
    public Validator() {
        identifiers = new ArrayList<>();
    }

    /**
     * Validate a given command by determining what type of command it is and pass it on to the appropriate validating
     * method
     * @param command The command to be validated
     * @return True if the command matches the formal file syntax, or false if it does not
     */
    public boolean validateCommand(String[] command) {
        if (command.length > 1) {
            if (validateGizmoOp(command)) {
                if (!validateExistingIdentifier(command[1])) {
                    addIdentifier(command[1]);
                    return true;
                }
            }

            if (command[0].matches("Rotate|Delete")) {
                return validateRotateAndDelete(command);
            }

            if(command[0].matches("Ball")) {
                if(validateBall(command) && !validateExistingIdentifier(command[1])) {
                    addIdentifier(command[1]);
                    return true;
                }
            }

            if(command[0].matches("Absorber")) {
                if(validateAbsorber(command) && !validateExistingIdentifier(command[1])) {
                    addIdentifier(command[1]);
                    return true;
                }
            }

            if(command[0].matches("Move")) {
                return validateMove(command);
            }

            if(command[0].matches("Connect")) {
                return validateConnect(command);
            }

            if(command[0].matches("KeyConnect")) {
                return validateKeyConnect(command);
            }

            if(command[0].matches("Gravity")) {
                return validateGravity(command);
            }

            if(command[0].matches("Friction")) {
                return validateFriction(command);
            }
        } else if(command.length == 1) {
            if(command[0].matches("ABSNOTCONNECTED")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Validate a Friction command
     * @param command The Friction command to validate
     * @return True if the Friction command matches the formal file syntax, or false if it doesn't
     */
    public boolean validateFriction(String[] command) {
        return command.length == 3 && validateFloatPair(command[1], command[2]);
    }

    /**
     * Validate a Gravity command
     * @param command The Gravity command to validate
     * @return True if the Gravity command matches the formal file syntax, or false if it doesn't
     */
    public boolean validateGravity(String[] command) {
        return command.length == 2 && validateFloat(command[1]);
    }

    /**
     * Validate a KeyConnect command
     * @param command The KeyConnect command to validate
     * @return True if the KeyConnect command matches the formal file syntax, or false if it doesn't
     */
    public boolean validateKeyConnect(String[] command) {
        return command.length == 5 && command[1].matches("key")
                && command[2].matches("\\d+") && command[3].matches("down|up")
                && validateExistingIdentifier(command[4]);
    }

    /**
     * Validate a Connect command
     * @param command The Connect command to validate
     * @return True if the Connect command matches the formal file syntax, or false if it doesn't
     */
    public boolean validateConnect(String[] command) {
        return command.length == 3 && validateExistingIdentifier(command[1])
                && validateExistingIdentifier(command[2]);
    }

    /**
     * Validate a Move command
     * @param command The Move command to validate
     * @return True if the Move command matches the formal file syntax, or false if it doesn't
     */
    public boolean validateMove(String[] command) {
        return command.length == 4 && validateExistingIdentifier(command[1])
                && validateNumberPair(command[2], command[3]);
    }

    /**
     * Validate the given number pair, i.e. validate it that either both numbers are integers, or that
     * both numbers are floats
     * @param x The first number of the pair
     * @param y The second number of the pair
     * @return True if both numbers are either integers or floats
     */
    public boolean validateNumberPair(String x, String y) {
        return validateIntPair(x, y) || validateFloatPair(x, y);
    }

    /**
     * Add an identifier to the list of identifiers
     * @param identifier The identifier to add
     */
    public void addIdentifier(String identifier) {
        identifiers.add(identifier);
    }

    /**
     * Validate an Absorber command
     * @param command The Absorber command to validate
     * @return True if the Absorber command matches the formal file syntax, or false if it doesn't
     */
    public boolean validateAbsorber(String[] command) {
        return command.length == 6 && validateIdentifier(command[1])
                && validateIntPair(command[2], command[3])
                && validateIntPair(command[4], command[5]);
    }

    /**
     * Validate that the two given numbers are integers
     * @param x The first number of the pair
     * @param y The second number of the pair
     * @return True if both numbers are integers, false otherwise
     */
    public boolean validateIntPair(String x, String y) {
        return x.matches("\\d+") && y.matches("\\d+");
    }

    /**
     * Validate that the two given numbers are floats
     * @param x The first number of the pair
     * @param y The second number of the pair
     * @return True if both numbers are floats, false otherwise
     */
    public boolean validateFloatPair(String x, String y) {
        return x.matches("[0-9]+\\.[0-9]+") && y.matches("[0-9]+\\.[0-9]+");
    }

    /**
     * Validate Rotate and Delete commands
     * @param command The Rotate or Delete command to validate
     * @return True if the command matches the formal file syntax or false if it doesn't
     */
    public boolean validateRotateAndDelete(String[] command) {
        return command.length == 2 && validateIdentifier(command[1]) && validateExistingIdentifier(command[1]);
    }

    /**
     * Validate that the given number is a float
     * @param possibleFloat The possible float to validate
     * @return True if the given number is a float, false otherwise
     */
    public boolean validateFloat(String possibleFloat) {
        return possibleFloat.matches("[0-9]+\\.[0-9]+");
    }

    /**
     * Validate that an identifier conforms to the standard naming convention of the formal file syntax
     * @param identifier The identifier to validate
     * @return True if the identifier comforms to the naming convention, false otherwise
     */
    public boolean validateIdentifier(String identifier) {
        return identifier.matches("[a-zA-Z0-9_]+") && !identifier.equals("OuterWalls");
    }

    /**
     * Validate a Ball command
     * @param command The Ball command to validate
     * @return True if the Ball command matches the formal file syntax, or false if it doesn't
     */
    public boolean validateBall(String[] command) {
        return command.length == 6 && validateIdentifier(command[1]) && validateFloatPair(command[2], command[3]) &&
                validateFloatPair(command[4], command[5]);
    }

    public boolean validateGizmoOp(String[] command) {
        return command[0].matches("Square|Circle|Triangle|RightFlipper|LeftFlipper")
                && command.length == 4 && validateIdentifier(command[1])
                && validateIntPair(command[2], command[3]);
    }

    public boolean validateExistingIdentifier(String existingIdentifier) {
        return identifiers.contains(existingIdentifier);
    }


}
