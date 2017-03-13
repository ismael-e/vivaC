import java.awt.*;

/**
 * Created by silver on 25/02/2017.
 */
@SuppressWarnings("ALL")
public class LogEntry {
    private int counter;
    private Point location;
    private boolean fork;

    public LogEntry(int counter, Point location, boolean fork) {
        this.counter = counter;
        this.location = location;
        this.fork = fork;
    }

    public int getCounter() {
        return counter;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isFork() {
        return fork;
    }

}
