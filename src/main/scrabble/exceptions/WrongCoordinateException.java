package test.scrabble.model.scrabble.exceptions;

/**
 * Created by Ivorra on 30/09/16.
 */
public class WrongCoordinateException extends Exception {
    private int x;
    private int y;

    public WrongCoordinateException(int ax, int ay) {
        x = ax;
        y = ay;
    }

    @Override
    public String getMessage() {
        return "Error, the coordinates " + String.valueOf(x) + "," + String.valueOf(y) " are incorrect";
    }
}