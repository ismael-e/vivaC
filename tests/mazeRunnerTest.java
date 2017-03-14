import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Silver on 3/14/2017.
 */
public class mazeRunnerTest {
    @Test(timeout = 1000)
    public void testMazeRunnerUnreacheableExit()  {
        MazeRunner mazeRunner = new MazeRunner();
        String result = mazeRunner.solveMaze("tests/output/Maze.txt",new int[]{0,0},new int[]{4,3});
        assertNull(result);
    }

    @Test(timeout = 1000)
    public void testMazeRunnerSimple()  {
        MazeRunner mazeRunner = new MazeRunner();
        String result = mazeRunner.solveMaze("tests/output/Maze.txt",new int[]{0,0},new int[]{4,2});

        assertEquals("00102021313242",result);
    }

}
