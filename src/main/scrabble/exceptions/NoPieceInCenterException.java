package main.scrabble.exceptions;

/**
 * Created by enrique on 19/10/16.
 */
public class NoPieceInCenterException extends Exception {
    public String getMessage() {
        return "Error, no piece placed ni the middle";
    }
}
