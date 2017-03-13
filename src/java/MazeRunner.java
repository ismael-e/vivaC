import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by silver on 13/03/2017.
 */
public class MazeRunner {
    public void solveMaze(String mazePath ,int startX, int startY, int endX, int endY){
        //building maze from text file
        try {
            Point start = new Point(startX,startY);
            Point end = new Point(endX,endY);

            MazeImporter mazeImporter = new MazeImporter(mazePath ,start,end);
            Maze maze = mazeImporter.getMaze();
            maze.solve();
        } catch (FileNotFoundException e) {
            System.out.println("Something weird has happened. the maze file wasn't found, check the path and try again.");
        }
    }
}
