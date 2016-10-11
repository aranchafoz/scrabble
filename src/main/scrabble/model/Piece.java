package main.scrabble.model;

/**
 * Created by enrique on 30/09/16.
 */
public class Piece {
    private char letter;
    private int punctuation;

    public Piece(char c, int punctuation) {
        this.letter = c;
        this.punctuation = punctuation;
    }

    public char getLetter() {
        return letter;
    }

    public int getPunctuation() {
        return punctuation;
    }
}
