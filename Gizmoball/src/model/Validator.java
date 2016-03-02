package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    private List<String> identifiers;
    public Validator() {
        identifiers = new ArrayList<>();
    }

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
        }
        return false;
    }

    public boolean validateFriction(String[] command) {
        return command.length == 3 && validateFloatPair(command[1], command[2]);
    }

    public boolean validateGravity(String[] command) {
        return command.length == 2 && validateFloat(command[1]);
    }

    public boolean validateKeyConnect(String[] command) {
        return command.length == 5 && command[1].matches("key")
                && command[2].matches("\\d+") && command[3].matches("down|up")
                && validateExistingIdentifier(command[4]);
    }

    public boolean validateConnect(String[] command) {
        return command.length == 3 && validateExistingIdentifier(command[1])
                && validateExistingIdentifier(command[2]);
    }

    public boolean validateMove(String[] command) {
        return command.length == 4 && validateExistingIdentifier(command[1])
                && validateNumberPair(command[2], command[3]);
    }

    public boolean validateNumberPair(String x, String y) {
        return validateIntPair(x, y) || validateFloatPair(x, y);
    }

    public void addIdentifier(String identifier) {
        identifiers.add(identifier);
    }

    public boolean validateAbsorber(String[] command) {
        return command.length == 6 && validateIdentifier(command[1])
                && validateIntPair(command[2], command[3])
                && validateIntPair(command[4], command[5]);
    }

    public boolean validateIntPair(String x, String y) {
        return x.matches("\\d+") && y.matches("\\d+");
    }

    public boolean validateFloatPair(String x, String y) {
        return x.matches("[0-9]+\\.[0-9]+") && y.matches("[0-9]+\\.[0-9]+");
    }

    public boolean validateRotateAndDelete(String[] row) {
        return row.length == 2 && validateIdentifier(row[1]) && validateExistingIdentifier(row[1]);
    }

    public boolean validateFloat(String possibleFloat) {
        return possibleFloat.matches("[0-9]+\\.[0-9]+");
    }

    public boolean validateIdentifier(String identifier) {
        return identifier.matches("[a-zA-Z0-9_]+") && !identifier.equals("OuterWalls");
    }

    public boolean validateBall(String[] row) {
        return row.length == 6 && validateIdentifier(row[1]) && validateFloatPair(row[2], row[3]) &&
                validateFloatPair(row[4], row[5]);
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
