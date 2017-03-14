/**
 * Created by silver on 13/03/2017.
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

   public int getX() {
        return x;
    }
   public int getY() {
        return y;
    }

    public boolean equals(Object obj) {
        //overriding default equals to allow searching through an arraylist of coordinates with .contains()
        Coordinate comparing = (Coordinate) obj;
        //could be inlined but left as such for clarity
        if( comparing.getX() == this.getX() && comparing.getY() == this.getY()){
            return true;
        }
        else {
            return false;
        }
    }
}
