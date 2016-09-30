package main.scrabble.exceptions;

/**
 * Created by Ivorra on 30/09/16.
 */
public class WrongCoordinateException extends Exception {
    private char x;
    private int y;

    public WrongCoordinateException(char x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage() {
        return "Error, the coordinates " + x + "," + y + " are incorrect";
    }
}