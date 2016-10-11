package main.scrabble.exceptions;

/**
 * Created by enrique on 11/10/16.
 */
public class NoPiecesInBagException extends Exception {
    String message = "Error, no remaining pieces in the bag";

    public String getMessage() {
        return message;
    }
}
