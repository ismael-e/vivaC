import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by silver on 13/03/2017.
 */
public class MazeRunner {
    public String solveMaze(String mazePath ,int[] startCoordinates, int[] endCoordinates){
        //building maze from text file
        String result = null;
        try {
            Point start = new Point(startCoordinates[0],startCoordinates[1]);
            Point end = new Point(endCoordinates[0],endCoordinates[1]);

            MazeImporter mazeImporter = new MazeImporter(mazePath ,start,end);
            Maze maze = mazeImporter.getMaze();
            result = maze.solve();
        } catch (FileNotFoundException e) {
            System.out.println("Something weird has happened. the maze file wasn't found, check the path and try again.");
        }
        return result;
    }
}
