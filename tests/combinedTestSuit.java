import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Silver on 3/14/2017.
 */
    @RunWith(Suite.class)

    @Suite.SuiteClasses({
            CoordinateParserTest.class,
            mazeRunnerTest.class,
            hashCollisionBasicTest.class,
            integrationTest.class
    })

    public class combinedTestSuit {

    }
