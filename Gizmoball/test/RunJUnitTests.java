import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// specify a runner class: Suite.class
@RunWith(Suite.class)

// specify an array of test classes
@Suite.SuiteClasses({
  AbsorberTest.class, 
  BallTest.class,
  CircularTest.class,
  CollisionDetailsTest.class,
  ConnectionsTest.class,
  FlipperTest.class,
  GBallModelTest.class,
  KeyConnectionsAbsTest.class,
  KeyConnectionsFlipperTest.class,
  ModelLoadTests.class,
  SquareTest.class,
  TeleporterBumperTest.class,
  TeleporterConnectionTest.class,
  TriangularTest.class,
  ValidatorTest.class,
  WallsTest.class,
  WriterTest.class}
)

public class RunJUnitTests {

}
