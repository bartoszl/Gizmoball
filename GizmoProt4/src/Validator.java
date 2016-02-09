import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John Watt on 08/02/2016.
 */
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
        }
        return false;
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

    public boolean validateInt(String possibleInt) {
        return possibleInt.matches("\\d+");
    }

    public boolean validateExistingIdentifier(String existingIdentifier) {
        return identifiers.contains(existingIdentifier);
    }


}

