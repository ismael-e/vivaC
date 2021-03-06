import java.awt.*;

/**
 * Created by Silver on 2/23/2017.
 */
@SuppressWarnings("DefaultFileTemplate")
public class MazeTile {
    public static final String TILE_TYPE_WALL = "wall";
    public static final String TILE_TYPE_TUNNEL = "tunnel";
    public static final String TILE_TYPE_SOLUTION = "solution";
    public static final String TILE_TYPE_START = "start";
    public static final String TILE_TYPE_END = "end";
    private final Point position;
    private String type;
    private boolean visited = false;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public MazeTile(Point position, String type) {
        this.position = position;
        this.type = type;
    }

    public Point getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String display() {
        String result = null;
        String type = getType();
        switch (type) {
            case TILE_TYPE_WALL:
                result = "x";
                break;
            case TILE_TYPE_TUNNEL:
                result = ".";
                break;
            case TILE_TYPE_SOLUTION:
                result = "O";
                break;
            case TILE_TYPE_START:
                result = "S";
                break;
            case TILE_TYPE_END:
                result = "E";
                break;
        }
        return result;
    }
}
