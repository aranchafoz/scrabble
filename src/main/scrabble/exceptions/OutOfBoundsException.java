package main.scrabble.exceptions;

/**
 * Created by enrique on 19/10/16.
 */
public class OutOfBoundsException extends Exception {
    public String getMessage() {
        return "Error, out of bounds";
    }
}
