public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        HashCollider collider = new HashCollider();
//        String answer = collider.bruteForce("code-quality",3);
//        System.out.println(answer);

//        CoordinateParser parser = new CoordinateParser();
//        parser.parseCoordinates("Coordinates/");

        MazeRunner mazeRunner = new MazeRunner();
        mazeRunner.solveMaze("ParserOutput/Maze.txt",1,2,1,28);
    }
}
