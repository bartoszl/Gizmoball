import org.junit.Test;

import model.Validator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class ValidatorTest {
    private Validator reader;
    @org.junit.Before
    public void setUp() throws Exception {
        reader = new Validator();
    }

    @Test
    public void testValidateExistingIdentifier_Expected_True () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        boolean actual = localValidator.validateExistingIdentifier("T1");
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateRotateAndDelete_Correct_Semantics_Expected_True() {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        String[] test = new String[] {"Rotate", "T1"};
        boolean actual = localValidator.validateRotateAndDelete(test);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateRotateAndDelete_Incorrect_Semantics_No_Indentifier_Expected_True() {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        String[] test = new String[] {"Rotate"};
        boolean actual = localValidator.validateRotateAndDelete(test);
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
        Validator localValidator = new Validator();
        String[] test = new String[] {"Triangle", "T1", "1", "2"};
        boolean actual = localValidator.validateGizmoOp(test);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateGizmoOp_Blob_Expected_False () {
        Validator localValidator = new Validator();
        String[] test = new String[]{"Blob", "Blob1", "1", "2"};
        boolean actual = localValidator.validateGizmoOp(test);
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
        Validator localValidator = new Validator();
        String[] testAbsorber = new String[] {"Absorber", "Absorber1", "1", "2", "1", "10"};
        boolean actual = localValidator.validateAbsorber(testAbsorber);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Valid_Rotate_Expected_True () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        String[] testRotate = new String[] {"Rotate", "T1"};
        boolean actual = localValidator.validateCommand(testRotate);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Rotate_Expected_False () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T2");
        String[] testRotate = new String[] {"Rotate", "T1"};
        boolean actual = localValidator.validateCommand(testRotate);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Delete_Expected_True () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        String[] testRotate = new String[] {"Delete", "T1"};
        boolean actual = localValidator.validateCommand(testRotate);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Delete_Expected_False () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T2");
        String[] testRotate = new String[] {"Delete", "T1"};
        boolean actual = localValidator.validateCommand(testRotate);
        assertThat(actual, is(equalTo(false)));
    }
    @Test
    public void testValidateCommand_Valid_Ball_Expected_True () {
        Validator localValidator = new Validator();
        String[] testBall = new String[] {"Ball", "B", "1.0", "11.0", "0.0", "0.0"};
        boolean actual = localValidator.validateCommand(testBall);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Ball_Expected_False () {
        Validator localValidator = new Validator();
        String[] testBall = new String[] {"Ball", "OuterWalls", "1.0", "11.0", "0.0", "0.0"};
        boolean actual = localValidator.validateCommand(testBall);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Absorber_Expected_True () {
        Validator localValidator = new Validator();
        String[] testAbsorber = new String[] {"Absorber", "Absorber1", "1", "1", "5", "5"};
        boolean actual = localValidator.validateCommand(testAbsorber);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Absorber_Expected_False () {
        Validator localValidator = new Validator();
        String[] testAbsorber = new String[] {"Absorber", "OuterWalls", "1.0", "11.0", "0.0", "0.0"};
        boolean actual = localValidator.validateCommand(testAbsorber);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Triangle_Expected_True () {
        Validator localValidaotr = new Validator();
        String[] testTriangle = new String[] {"Triangle", "T1", "5", "5"};
        boolean actual = localValidaotr.validateCommand(testTriangle);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Square_Expected_True () {
        Validator localValidator = new Validator();
        String[] testSquare = new String[] {"Square", "S1", "5.6", "5"};
        boolean actual = localValidator.validateCommand(testSquare);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Move_Int_Pair_Expected_True () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        String[] testMove = new String[] {"Move", "T1", "5", "6"};
        boolean actual = localValidator.validateCommand(testMove);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Valid_Move_Float_Pair_Expected_True () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        String[] testMove = new String[] {"Move" ,"T1", "5.0", "6.5"};
        boolean actual = localValidator.validateCommand(testMove);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Move_Expected_False () {
        Validator localValidator = new Validator();
        String[] testMove = new String[] {"Move", "T1", "5", "5"};
        boolean actual = localValidator.validateCommand(testMove);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Connect_Expected_True () {
        Validator localValidator = new Validator();
        localValidator.addIdentifier("T1");
        localValidator.addIdentifier("T2");
        String[] testConnect = new String[] {"Connect", "T1", "T2"};
        boolean actual = localValidator.validateCommand(testConnect);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Connect_No_Existing_Identifier_Expected_False () {
        Validator localValidator = new Validator();
        String[] testConnect = new String[] {"Connect", "T1", "T2"};
        boolean actual = localValidator.validateCommand(testConnect);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_KeyConnect_Expected_True () {
        Validator localValidator = new Validator();
        String[] testKeyConnect = new String[] {"KeyConnect", "key", "100", "down", "T1"};
        localValidator.addIdentifier("T1");
        boolean actual = localValidator.validateCommand(testKeyConnect);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_KeyConnect_Invalid_Name_Expected_False () {
        Validator localValidator = new Validator();
        String[] testKeyConnect = new String[] {"KeyConnect", "key", "100", "down", "T1"};
        boolean actual = localValidator.validateCommand(testKeyConnect);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Gravity_Expected_True () {
        Validator localValidator = new Validator();
        String[] testGravity = new String[] {"Gravity", "5.6"};
        boolean actual = localValidator.validateCommand(testGravity);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Gravity_Non_Float_Expected_False () {
        Validator localValidator = new Validator();
        String[] testGravity = new String[] {"Gravity", "5"};
        boolean actual = localValidator.validateCommand(testGravity);
        assertThat(actual, is(equalTo(false)));
    }

    @Test
    public void testValidateCommand_Valid_Friction_Expected_True () {
        Validator localValidator = new Validator();
        String[] testFriction = new String[] {"Friction", "6.0", "4.0"};
        boolean actual = localValidator.validateCommand(testFriction);
        assertThat(actual, is(equalTo(true)));
    }

    @Test
    public void testValidateCommand_Invalid_Friction_Non_Float_Expected_True() {
        Validator localValidator = new Validator();
        String[] testFriction = new String[] {"Friction", "4", "6"};
        boolean actual = localValidator.validateCommand(testFriction);
        assertThat(actual, is(equalTo(false)));
    }
    @org.junit.After
    public void tearDown() throws Exception {
        reader = null;
    }
}