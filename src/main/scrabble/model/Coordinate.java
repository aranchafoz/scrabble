package main.scrabble.model;

/**
 * Created by ivorra on 14/10/16.
 */
public class Coordinate {
    public int x;
    public int y;
    public Coordinate(){x = 0; y = 0;}
    public Coordinate getCoordinate() {
        Coordinate coord = new Coordinate();
        coord.x = x;
        coord.y = y;
        return coord;
    }
}
