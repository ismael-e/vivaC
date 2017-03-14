import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by silver on 13/03/2017.
 */
public class CoordinateParser {
    public void parseCoordinates(String directoryPath,String outputPath){
        List<String> coordinateFileNames = loadFiles(directoryPath);
        ArrayList<Coordinate> Coordinates = processFiles(coordinateFileNames);
        generateMazeFile(Coordinates,outputPath);
    }

    private void generateMazeFile(ArrayList<Coordinate> coordinates, String outputPath) {

        int height = getMax("Y",coordinates);
        int width = getMax("X",coordinates);

        System.out.println("Creating a Maze of size: " + width + " BY " + height);
        FileWriter fileWriter;

        try {

            String destination = "Maze.txt";

            if (outputPath != null) {
                destination = outputPath + destination;
            }
            else {
                destination = "ParserOutput/" + destination;
            }

            fileWriter = new FileWriter(destination);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(int y=0; y<height;y++)
            {
                String line = "";
                for (int x=0; x<width;x++)
                {
                    String currentPosition;

                    if(coordinates.contains(new Coordinate(x,y)))
                    {
                        currentPosition = "x";
                    }
                    else
                    {
                        currentPosition = ".";
                    }
                    line = line + currentPosition;
                }

                printWriter.println(line);
            }
            printWriter.close();
            fileWriter.close();
            System.out.println("Maze has been saved: "+ destination);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private int getMax(String dimension, ArrayList<Coordinate> coordinates) {
        int result = 0;
        Iterator coordinatesIterator = coordinates.iterator();
        if(dimension.equals("X"))
        {
            while(coordinatesIterator.hasNext())
            {
                Coordinate currentCoordinate = (Coordinate) coordinatesIterator.next();
                if(result<currentCoordinate.getX())
                {
                    result = currentCoordinate.getX();
                }
            }
        }
        else
            {
            while(coordinatesIterator.hasNext())
            {
                Coordinate currentCoordinate = (Coordinate) coordinatesIterator.next();
                if(result<currentCoordinate.getY())
                {
                    result = currentCoordinate.getY();
                }
            }
        }
        //adding to account for 0 index
    return result+1;
    }

    private ArrayList<Coordinate> processFiles(List<String> coordinateFileNames) {
        ArrayList<Coordinate> result = new ArrayList<Coordinate>();

        //process each file in list
        Iterator fileIterator = coordinateFileNames.iterator();
        while(fileIterator.hasNext()){
            String currentFileName = (String) fileIterator.next();

            result.addAll(extractCoordinates(currentFileName));
        }
        return result;
    }

    private ArrayList<Coordinate> extractCoordinates(String currentFileName) {

        ArrayList<Coordinate> result = new ArrayList<Coordinate>();

        File coordinateFile = new File(currentFileName);
        BufferedReader lineReader = null;
        try {
            lineReader = new BufferedReader(new FileReader(coordinateFile));
            String readLine;
            while ((readLine = lineReader.readLine()) != null) {
                result.addAll(toCoordinate(readLine));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ArrayList<Coordinate> toCoordinate(String readLine) {
        String[] splitCoordinatePairs = readLine.split(",");
        ArrayList<Coordinate> result = new ArrayList<Coordinate>();
        //iterating through each text pair of coordinates to convert
        for(int i = 0; i<splitCoordinatePairs.length; i++){
            String currentPair = splitCoordinatePairs[i];
            int yPosition = currentPair.indexOf("y");
            int xCoordinate = Integer.parseInt(currentPair.substring(1,yPosition));
            int yCoordinate = Integer.parseInt(currentPair.substring(yPosition+1,currentPair.length()));
            Coordinate currentCoordinate = new Coordinate(xCoordinate,yCoordinate);
            result.add(currentCoordinate);
        }
        return result;
    }

    private List<String> loadFiles(String directoryPath) {
        List<String> result = new ArrayList<String>();

        File directory = new File(directoryPath);
        System.out.println("Parser Loading the following Files :");
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith((".txt"))) {
                String fileName = file.getName();
                result.add(directoryPath +file.getName());
                System.out.println("> " + fileName);
            }
        }
        return result;
    }

    public void parseCoordinates(String directoryPath) {
        parseCoordinates(directoryPath,null);
    }
}
