import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

/**
 * Created by Silver on 3/14/2017.
 */
public class CoordinateParserTest {
    @Test(timeout = 1000)
    public void testCoordinateParserGivenValue()  {

        CoordinateParser coordinateParser = new CoordinateParser();
        coordinateParser.parseCoordinates("tests/resources/","tests/output/");

        File mazeFile = new File("tests/output/Maze.txt");
        BufferedReader lineReader = null;

        try {
            lineReader = new BufferedReader(new FileReader(mazeFile));

            String expectedContents = "..........x..x...x";
            String fileContents = "";
            String readLine;

            while ((readLine = lineReader.readLine()) != null)
            {
                fileContents = fileContents + readLine;
            }
            assertEquals(expectedContents,fileContents );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
