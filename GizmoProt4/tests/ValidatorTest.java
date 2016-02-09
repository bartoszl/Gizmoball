import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by John Watt on 08/02/2016.
 */
public class ValidatorTest {
    private Validator reader;
    @org.junit.Before
    public void setUp() throws Exception {
        reader = new Validator();
    }

    @Test
    public void testValidateExistingIdentifier_Expected_True () {
        Validator localReader = new Validator();
        localReader.addIdentifier("T1");
        boolean actual = localReader.validateExistingIdentifier("T1");
        assertThat(actual, is(equalTo(true)));
    }
    @Test
    public void testValidateRotateAndDelete_Correct_Semantics_Expected_True() {
        Validator localReader = new Validator();
        localReader.addIdentifier("T1");
        String[] test = new String[] {"Rotate", "T1"};
        boolean actual = localReader.validateRotateAndDelete(test);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateRotateAndDelete_Incorrect_Semantics_No_Indentifier_Expected_True() {
        Validator localReader = new Validator();
        localReader.addIdentifier("T1");
        String[] test = new String[] {"Rotate"};
        boolean actual = localReader.validateRotateAndDelete(test);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateIdentifier_Expected_True() {
        boolean actual = reader.validateIdentifier("T5");
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateIdentifier_Length_Zero_Expected_False () {
        boolean actual = reader.validateIdentifier("");
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateIdentifier_Incorrect_OuterWalls_Expected_False () {
        boolean actual = reader.validateIdentifier("OuterWalls");
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateFloat_Valid_One_Point_Zero_Expected_True () {
        boolean actual = reader.validateFloat("1.0");
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateFloat_Valid_Zero_Point_Zero_Expected_True () {
        boolean actual = reader.validateFloat("0.0");
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateFloat_Zero_Expected_False () {
        boolean actual = reader.validateFloat("0");
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateFloat_Zero_Point_Nothing_Expected_False () {
        boolean actual = reader.validateFloat("0.");
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateGizmoOp_Triangle_Expected_True () {
        Validator localReader = new Validator();
        String[] test = new String[] {"Triangle", "T1", "1", "2"};
        boolean actual = localReader.validateGizmoOp(test);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateGizmoOp_Blob_Expected_False () {
        Validator localReader = new Validator();
        String[] test = new String[] {"Blob", "Blob1", "1", "2"};
        boolean actual = localReader.validateGizmoOp(test);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateInt_5_Expected_True () {
        boolean actual = reader.validateInt("5");
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateInt_5_Point_5_Expected_False () {
        boolean actual = reader.validateInt("5.5");
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateInt_Non_Numeric_Expected_False () {
        boolean actual = reader.validateInt("NonNumeric");
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateInt_Empty_String_Expected_False () {
        boolean actual = reader.validateInt("");
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateBall_Correct_Values_Expected_True () {
        String[] test = new String[]{"Ball", "B", "1.0", "11.0", "0.0", "0.0"};
        boolean actual = reader.validateBall(test);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateBall_Two_Too_Few_Parameters_Expected_False () {
        String[] test = new String[]{"Ball", "B", "1.0", "11.0"};
        boolean actual = reader.validateBall(test);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateBall_Int_Instead_Of_Float_Expected_False () {
        String[] test = new String[]{"Ball", "B", "1", "11.0", "0.0", "0.0"};
        boolean actual = reader.validateBall(test);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateAbsorber_Correct_Values_Expected_True () {
        Validator localReader = new Validator();
        String[] testAbsorber = new String[] {"Absorber", "Absorber1", "1", "2", "1", "10"};
        boolean actual = localReader.validateAbsorber(testAbsorber);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Valid_Rotate_Expected_True () {
        Validator localReader = new Validator();
        localReader.addIdentifier("T1");
        String[] testRotate = new String[] {"Rotate", "T1"};
        boolean actual = localReader.validateCommand(testRotate);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Rotate_Expected_False () {
        Validator localReader = new Validator();
        localReader.addIdentifier("T2");
        String[] testRotate = new String[] {"Rotate", "T1"};
        boolean actual = localReader.validateCommand(testRotate);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Delete_Expected_True () {
        Validator localReader = new Validator();
        localReader.addIdentifier("T1");
        String[] testRotate = new String[] {"Delete", "T1"};
        boolean actual = localReader.validateCommand(testRotate);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Delete_Expected_False () {
        Validator localReader = new Validator();
        localReader.addIdentifier("T2");
        String[] testRotate = new String[] {"Delete", "T1"};
        boolean actual = localReader.validateCommand(testRotate);
        assertThat(actual, is(equalTo(false)));
    }
    @Test
    public void testValidateCommand_Valid_Ball_Expected_True () {
        Validator localReader = new Validator();
        String[] testBall = new String[] {"Ball", "B", "1.0", "11.0", "0.0", "0.0"};
        boolean actual = localReader.validateCommand(testBall);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Ball_Expected_False () {
        Validator localReader = new Validator();
        String[] testBall = new String[] {"Ball", "OuterWalls", "1.0", "11.0", "0.0", "0.0"};
        boolean actual = localReader.validateCommand(testBall);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Absorber_Expected_True () {
        Validator localReader = new Validator();
        String[] testAbsorber = new String[] {"Absorber", "Absorber1", "1", "1", "5", "5"};
        boolean actual = localReader.validateCommand(testAbsorber);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Absorber_Expected_False () {
        Validator localReader = new Validator();
        String[] testAbsorber = new String[] {"Absorber", "OuterWalls", "1.0", "11.0", "0.0", "0.0"};
        boolean actual = localReader.validateCommand(testAbsorber);
        assertThat(actual, is(equalTo(false)));
    }


    @org.junit.After
    public void tearDown() throws Exception {
        reader = null;
    }
}