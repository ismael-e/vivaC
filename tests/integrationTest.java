import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Silver on 3/14/2017.
 */
public class integrationTest {

    @Test(timeout = 1000)
    public void testEndToEndIntegration(){

        String directoryPath = "tests/resources/";
        String outputPath = "tests/output/";
        String fileName = "Maze.txt";
        int[] startCoordinates = new int[]{0,0};
        int[] endCoordinates = new int[]{4,2};
        int prefixedZeros = 2;

        CoordinateParser parser = new CoordinateParser();
        parser.parseCoordinates(directoryPath,outputPath);
        MazeRunner mazeRunner = new MazeRunner();
        String pathOut = mazeRunner.solveMaze(outputPath + fileName,startCoordinates,endCoordinates);
        HashCollider collider = new HashCollider();
        String answer = collider.bruteForce(pathOut,prefixedZeros);

        assertEquals("dd416dce06",answer);
    }

}
