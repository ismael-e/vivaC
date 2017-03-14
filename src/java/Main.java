public class Main {

    public static void main(String[] args) {
        System.out.println("Ismael's solution");

        int prefixedZeros = 2;
        int[] startCoordinates = new int[]{1,2};
        int[] endCoordinates = new int[]{1,28};
        String path = "Coordinates/";

        integrator(prefixedZeros,startCoordinates,endCoordinates,path);

    }

    public static void integrator(int prefixedZeros,int[] startCoordinates, int[] endCoordinates,String directoryPath ){

        System.out.println("-----------------Starting Coordinate Parser-----------------");
        CoordinateParser parser = new CoordinateParser();
        parser.parseCoordinates(directoryPath);

        System.out.println("--------------------Starting Maze Solver--------------------");
        MazeRunner mazeRunner = new MazeRunner();
        String pathOut = mazeRunner.solveMaze("ParserOutput/Maze.txt",startCoordinates,endCoordinates);
        System.out.println("Maze Solver Output : " + pathOut);

        System.out.println("-------------------Starting Hash Collider-------------------");
        HashCollider collider = new HashCollider();
        String answer = collider.bruteForce(pathOut,prefixedZeros);
        System.out.println("Hash Collider answer : " + answer);

    }

}
