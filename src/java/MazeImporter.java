import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Silver on 2/23/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
 class MazeImporter {


    private Point start , end;
    private final List<String> mazeArrayList = new ArrayList<String>();
    private Maze maze;

    public MazeImporter(String mazePath, Point start, Point end) throws FileNotFoundException,InputMismatchException {
       this.start = start;
       this.end = end;
       importMaze(mazePath);
    }

    public MazeImporter(String testResource) throws FileNotFoundException,InputMismatchException,ArrayIndexOutOfBoundsException {

        try{
            importMaze(testResource);
        }catch (InputMismatchException e){
            System.out.println("Please enter only numbers.");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter only numbers from the list above.");
        }

    }

    //public accessor to help with testing
    private void importMaze(String fileName) throws FileNotFoundException {
        //load text file into memory as an ArrayList
        loadTextFile(fileName);
        int[] dimensions = getMazeDimensions();
        //process the maze body into a map
        HashMap<Point, MazeTile> mazeMap = mapMazeBody();

        maze = new Maze(dimensions,mazeMap,start,end);
    }

    private int[] getMazeDimensions() {

        int[] dimensions = new int[2];

        int height = mazeArrayList.size();
        String line = mazeArrayList.get(0);
        int width = line.length();

        dimensions[0] = width;
        dimensions[1] = height;

        return dimensions;
    }

    private Point getCoordinatePoint(String endLine) {
        Scanner stringScanner;
        stringScanner = new Scanner(endLine);
        int endX = stringScanner.nextInt();
        int endY = stringScanner.nextInt();
        return new Point(endX,endY);
    }

    private HashMap<Point, MazeTile> mapMazeBody() {

        Iterator mazeIterator = mazeArrayList.listIterator();

        HashMap<Point,MazeTile> tileMap = new HashMap<Point, MazeTile>();
        int lineIndex = 0;
        while(mazeIterator.hasNext()){
            String currentString = (String) mazeIterator.next();

            char[] charArray = currentString.replaceAll(" ","").toCharArray();

            for(int i=0; i < charArray.length ;i++){
                char currentCharacter = charArray[i];

                Point tilePosition = new Point(i,lineIndex);
                String tileType;

                if(currentCharacter == 'x'){
                    //Todo change the wall and tunnel to constants when i get some time
                    tileType = MazeTile.TILE_TYPE_WALL;
                }
                else{
                    tileType = MazeTile.TILE_TYPE_TUNNEL;
                }

                MazeTile tile = new MazeTile(tilePosition,tileType);
                tileMap.put(tilePosition,tile);
            }
            lineIndex++;
        }
        return tileMap;
    }

    private void loadTextFile(String fileName) throws FileNotFoundException {
        //loading maze file from .txt to an array list
        try {
            File mazeFile = new File(fileName);
            BufferedReader lineReader = new BufferedReader(new FileReader(mazeFile));
            String readLine;
            while ((readLine = lineReader.readLine()) != null) {
                mazeArrayList.add(readLine);
            }
        } catch (IOException e) {
            System.out.println("IO ERROR > Something went wrong when trying to read the maze file, check the path and file name");
        }
    }

    public Maze getMaze() {
        return maze;
    }
}
