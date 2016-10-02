package main.scrabble.exceptions;

/**
 * Created by Ivorra on 30/09/16.
 */
public class WrongCoordinateException extends Exception {
    private int x;
    private int y;

    public WrongCoordinateException(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage() {
        return "Error, the coordinate " + (char)(x + 'A') + (y + 1) + " are incorrect";
    }
}
