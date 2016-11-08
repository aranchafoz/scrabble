package main.scrabble.exceptions;

/**
 * Created by enrique on 8/11/16.
 */
public class AloneWordException extends Exception {
    public String getMessage() {
        return "Error, at least one inserted piece must be next to a previously inserted one";
    }
}
