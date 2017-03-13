import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Silver on 2/23/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class Maze {

    private final Adventurer adventurer;
    private final int height;
    private final int width;
    private final Point startPoint;
    private final Point endPoint;
    private final HashMap<Point, MazeTile> mazeMap;

    public Maze(int[] dimension, HashMap<Point, MazeTile> tileMap, Point startCoordinates, Point endCoordinates) {

        height = dimension[1];
        width = dimension[0];
        startPoint = startCoordinates;
        endPoint = endCoordinates;
        mazeMap = tileMap;

        adventurer = new Adventurer(this);

        //providing Feedback
        System.out.println("---Maze loaded---");
        System.out.println("Size :" + getWidth() + " BY " + getHeight() );
        System.out.println("Entrance X :" + getStartPoint().x + " Y :" +getStartPoint().y);
        System.out.println("Exit X :" + getEndPoint().x + " Y :" +getEndPoint().y);
    }

    public void solve() {
        //place the adventurer on the start tile and start his journey
        ArrayList<LogEntry> result = adventurer.startQuest(startPoint);

        if (result!=null){
            //process the log entries to generate a solution for the console.
            displaySolution(adventurer.travelLog);
        }
        else {
            //show a fail message
            failed();
        }
    }

    private void failed() {
        System.out.println("Maze could not be solved.");
    }

    private void displaySolution(ArrayList<LogEntry> result) {
        System.out.println("---PATH OUT---");
        markSolution(result);

        //use the dimensions of the maze as key to pull out the marked tiles from the map

        for (int y=0; y<getHeight(); y++){
            String solutionLine = "";

            for (int x=0; x<getWidth(); x++){
//                System.out.println("sol : x:"+x+" y:"+y);
                MazeTile currentTile = getTile( new Point(x,y) );
                solutionLine = solutionLine + " " + currentTile.display();
            }
            System.out.println(solutionLine);
        }

    }

    private MazeTile getTile(Point reference){
        return mazeMap.get(reference);
    }

    public MazeTile[] getAdjacentTiles(Point location) {
        MazeTile[] result = new MazeTile[4];
        //check N S E W
        Point[] adjacentPoints = new Point[4];
        
        adjacentPoints[0] = new Point(location.x ,location.y+1);
        adjacentPoints[1] = new Point(location.x ,location.y-1);
        adjacentPoints[2] = new Point(location.x+1 ,location.y);
        adjacentPoints[3] = new Point(location.x-1 ,location.y);

        for(int i=0; i<adjacentPoints.length; i++){
            MazeTile tile = getTile(adjacentPoints[i]);
            if( tile!=null && tile.getType().equals("tunnel")){
                result[i]=tile;
            }
        }

        return result;
    }

    public void markTile(Point location) {
        //marks tile as has been visited
        MazeTile tile = mazeMap.get(location);
        tile.setVisited();
        mazeMap.put(location,tile);
    }

    private void markSolution(ArrayList<LogEntry> result) {
        Iterator<LogEntry> solution = result.iterator();

        while(solution.hasNext()) {
            LogEntry currentEntry = solution.next();
            Point location = currentEntry.getLocation();
            //marks tile as part of the solution
            MazeTile tile = mazeMap.get(location);
            tile.setType("solution");
            mazeMap.put(location,tile);
        }
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public Point getEndPoint() {
        return endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }
}
