package main.scrabble.exceptions;

/**
 * Created by enrique on 27/09/16.
 */
public class WrongWordException extends Exception {
    private String word;

    public WrongWordException(String word) {
        this.word = word;
    }

    @Override
    public String getMessage() {
        return "Error, the word " + word + " does not exist";
    }
}
